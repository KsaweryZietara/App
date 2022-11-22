package demo.app.services;

import demo.app.exceptions.UserNotEnabledException;
import demo.app.models.auth.Role;
import demo.app.models.auth.User;
import demo.app.models.auth.VerificationToken;
import demo.app.repos.auth.RoleRepo;
import demo.app.repos.auth.UserRepo;
import demo.app.repos.auth.VerificationTokenRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final VerificationTokenRepo tokenRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, VerificationTokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.tokenRepo = tokenRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if(!user.isEnable()){
            throw new UserNotEnabledException(user.getUsername());
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getUsername());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);

        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public Role getRole(String name){
        log.info("Fetching role {}", name);
        return roleRepo.findByName(name);
    }

    @Override
    public boolean existByUsername(String username) {
        log.info("Checking if user {} exists", username);
        return userRepo.existsByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        log.info("Checking if user with email {} exist", email);
        return userRepo.existsByEmail(email);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        log.info("Creating verification token for user {}", user.getUsername());
        VerificationToken verificationToken = new VerificationToken(user, token);
        tokenRepo.save(verificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        log.info("Fetching token {}", token);
        return tokenRepo.findByToken(token);
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
