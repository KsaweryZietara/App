package demo.app.repos;

import demo.app.models.auth.User;
import demo.app.models.auth.VerificationToken;
import demo.app.repos.auth.UserRepo;
import demo.app.repos.auth.VerificationTokenRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class VerificationTokenRepoTest {

    @Autowired
    private VerificationTokenRepo tokenRepo;

    @Autowired
    private UserRepo userRepo;

    @Test
    public void VerificationTokenRepo_Save_ReturnsSavedToken(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        user.setEmail("testEmail");
        userRepo.save(user);

        VerificationToken token = new VerificationToken();
        token.setToken("testToken");
        token.setUser(user);

        VerificationToken savedToken = tokenRepo.save(token);

        Assertions.assertThat(savedToken).isNotNull();
        Assertions.assertThat(savedToken.getId()).isGreaterThan(0);
    }

    @Test
    public void VerificationTokenRepo_FindByToken_ReturnsToken(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        user.setEmail("testEmail");
        userRepo.save(user);

        VerificationToken token = new VerificationToken();
        token.setToken("testToken");
        token.setUser(user);
        token.setExpiryDate(new Date());
        tokenRepo.save(token);

        VerificationToken returnedToken = tokenRepo.findByToken(token.getToken());

        Assertions.assertThat(returnedToken).isNotNull();
        Assertions.assertThat(returnedToken.getToken()).isEqualTo(token.getToken());
    }
}
