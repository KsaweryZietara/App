package demo.app.controllers;

import demo.app.dtos.AddRoleToUserDto;
import demo.app.dtos.AuthResponseDto;
import demo.app.dtos.LoginDto;
import demo.app.dtos.RegisterDto;
import demo.app.events.OnRegistrationCompleteEvent;
import demo.app.models.Role;
import demo.app.models.User;
import demo.app.models.VerificationToken;
import demo.app.security.JWTGenerator;
import demo.app.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Calendar;
import java.util.Collections;

@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public AuthController(IUserService userService, AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.username(),
                        loginDto.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.ok().body(new AuthResponseDto(token));
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto){
        if(userService.existByUsername(registerDto.username())){
            return ResponseEntity.badRequest().body("Username is taken");
        }

        if(userService.existByEmail(registerDto.email())){
            return ResponseEntity.badRequest().body("Email is taken");
        }

        User user = new User();
        user.setUsername(registerDto.username());
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        user.setEmail(registerDto.email());
        Role roles = userService.getRole("USER");
        user.setRoles(Collections.singletonList(roles));
        userService.saveUser(user);

        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user,
                ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/auth").toUriString()));

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/auth/register").toUriString());
        return ResponseEntity.created(uri).body("User registered");
    }

    @GetMapping("registrationConfirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token){
        VerificationToken verificationToken = userService.getVerificationToken(token);

        if(verificationToken == null){
            return ResponseEntity.badRequest().body("Invalid token");
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if(verificationToken.getExpiryDate().getTime() - cal.getTime().getTime() <= 0){
            return ResponseEntity.badRequest().body("Token has expired");
        }

        user.setEnable(true);
        userService.saveUser(user);
        return ResponseEntity.ok().body("User has been enabled");
    }

    @PostMapping("role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/auth/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("role/addtouser")
    public void addRoleToUser(@RequestBody AddRoleToUserDto dto){
        userService.addRoleToUser(dto.username(), dto.roleName());
    }
}
