package demo.app.services;

import demo.app.dtos.auth.CreateRoleDto;
import demo.app.models.auth.Role;
import demo.app.models.auth.User;
import demo.app.models.auth.VerificationToken;

public interface UserService {
    User saveUser(User user);
    Role saveRole(CreateRoleDto roleDto);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    Role getRole(String name);
    boolean existByUsername(String username);
    boolean existByEmail(String email);
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String token);
}
