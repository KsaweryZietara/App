package demo.app.services;

import demo.app.models.Role;
import demo.app.models.User;

public interface IUserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    Role getRole(String name);
    boolean existByUsername(String username);
}
