package demo.app.services;

import demo.app.models.Role;
import demo.app.models.User;
import demo.app.models.VerificationToken;

public interface IUserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    Role getRole(String name);
    boolean existByUsername(String username);
    boolean existByEmail(String email);
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String token);
}
