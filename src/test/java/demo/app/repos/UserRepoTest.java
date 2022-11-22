package demo.app.repos;

import demo.app.models.auth.User;
import demo.app.repos.auth.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void UserRepo_Save_ReturnsSavedUser(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        user.setEmail("testEmail");

        User savedUser = userRepo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void UserRepo_FindByUsername_ReturnsUser(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        user.setEmail("testEmail");
        userRepo.save(user);

        User returnedUser = userRepo.findByUsername(user.getUsername());

        Assertions.assertThat(returnedUser).isNotNull();
        Assertions.assertThat(returnedUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void UserRepo_ExistsByUsername_ReturnsTrue(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        user.setEmail("testEmail");
        userRepo.save(user);

        boolean exists = userRepo.existsByUsername(user.getUsername());

        Assertions.assertThat(exists).isTrue();
    }

    @Test
    public void UserRepo_ExistsByEmail_ReturnsTrue(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        user.setEmail("testEmail");
        userRepo.save(user);

        boolean exists = userRepo.existsByEmail(user.getEmail());

        Assertions.assertThat(exists).isTrue();
    }
}
