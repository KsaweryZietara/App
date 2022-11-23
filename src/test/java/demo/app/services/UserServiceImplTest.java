package demo.app.services;

import demo.app.dtos.auth.CreateRoleDto;
import demo.app.models.auth.Role;
import demo.app.models.auth.User;
import demo.app.models.auth.VerificationToken;
import demo.app.repos.auth.RoleRepo;
import demo.app.repos.auth.UserRepo;
import demo.app.repos.auth.VerificationTokenRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private RoleRepo roleRepo;

    @Mock
    private VerificationTokenRepo tokenRepo;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void UserService_LoadUserByUsername_ReturnsUserDetails(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        user.setEnable(true);

        when(userRepo.findByUsername(user.getUsername())).thenReturn(user);

        UserDetails details = userServiceImpl.loadUserByUsername(user.getUsername());

        Assertions.assertThat(details).isNotNull();
        Assertions.assertThat(details.getUsername()).isEqualTo(user.getUsername());
        Assertions.assertThat(details.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    public void UserService_SaveUser_ReturnsUser(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");

        when(userRepo.save(user)).thenReturn(user);

        User returnedUser = userServiceImpl.saveUser(user);

        Assertions.assertThat(returnedUser).isNotNull();
        Assertions.assertThat(returnedUser.getUsername()).isEqualTo(user.getUsername());
        Assertions.assertThat(returnedUser.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    public void UserService_SaveRole_ReturnsRole(){
        CreateRoleDto roleDto = new CreateRoleDto("testName");
        Role role = new Role();
        role.setId(1L);
        role.setName("testName");

        when(roleRepo.save(any())).thenReturn(role);

        Role returnedRole = userServiceImpl.saveRole(roleDto);

        Assertions.assertThat(returnedRole).isNotNull();
        Assertions.assertThat(returnedRole.getId()).isEqualTo(role.getId());
        Assertions.assertThat(returnedRole.getName()).isEqualTo(role.getName());
    }

    @Test
    public void UserService_AddRoleToUser_ReturnsNothing(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");

        Role role = new Role();
        role.setId(1L);
        role.setName("testName");

        when(userRepo.findByUsername(user.getUsername())).thenReturn(user);
        when(roleRepo.findByName(role.getName())).thenReturn(role);

        assertAll(() -> userServiceImpl.addRoleToUser(user.getUsername(), role.getName()));
    }

    @Test
    public void UserService_GetUser_ReturnsUser(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");

        when(userRepo.findByUsername(user.getUsername())).thenReturn(user);

        User returnedUser = userServiceImpl.getUser(user.getUsername());

        Assertions.assertThat(returnedUser).isNotNull();
        Assertions.assertThat(returnedUser.getUsername()).isEqualTo(user.getUsername());
        Assertions.assertThat(returnedUser.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    public void UserService_GetRole_ReturnsRole(){
        Role role = new Role();
        role.setId(1L);
        role.setName("testName");

        when(roleRepo.findByName(role.getName())).thenReturn(role);

        Role returnedRole = userServiceImpl.getRole(role.getName());

        Assertions.assertThat(returnedRole).isNotNull();
        Assertions.assertThat(returnedRole.getId()).isEqualTo(role.getId());
        Assertions.assertThat(returnedRole.getName()).isEqualTo(role.getName());
    }

    @Test
    public void UserService_ExistByUsername_ReturnsTrue(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");

        when(userRepo.existsByUsername(user.getUsername())).thenReturn(true);

        boolean exist = userServiceImpl.existByUsername(user.getUsername());

        Assertions.assertThat(exist).isTrue();
    }

    @Test
    public void UserService_ExistByEmail_ReturnsTrue(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        user.setEmail("testEmail");

        when(userRepo.existsByEmail(user.getEmail())).thenReturn(true);

        boolean exist = userServiceImpl.existByEmail(user.getEmail());

        Assertions.assertThat(exist).isTrue();
    }

    @Test
    public void UserService_CreateVerificationToken_ReturnsNothing(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");

        assertAll(() -> userServiceImpl.createVerificationToken(user, "testToken"));
    }

    @Test
    public void UserService_GetVerificationToken_ReturnsVerificationToken(){
        VerificationToken token = new VerificationToken();
        token.setId(1L);
        token.setToken("testToken");

        when(tokenRepo.findByToken(token.getToken())).thenReturn(token);

        VerificationToken returnedToken = userServiceImpl.getVerificationToken(token.getToken());

        Assertions.assertThat(returnedToken).isNotNull();
        Assertions.assertThat(returnedToken.getId()).isEqualTo(token.getId());
        Assertions.assertThat(returnedToken.getToken()).isEqualTo(token.getToken());
    }
}
