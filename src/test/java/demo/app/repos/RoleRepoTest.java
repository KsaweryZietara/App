package demo.app.repos;

import demo.app.models.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoleRepoTest {

    @Autowired
    private RoleRepo roleRepo;

    @Test
    public void RoleRepo_Save_ReturnsSavedRole(){
        Role role = new Role();
        role.setName("testName");

        Role savedRole = roleRepo.save(role);

        Assertions.assertThat(savedRole).isNotNull();
        Assertions.assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void RoleRepo_FindByName_ReturnsRole(){
        Role role = new Role();
        role.setName("testName");
        roleRepo.save(role);

        Role returnedRole = roleRepo.findByName(role.getName());

        Assertions.assertThat(returnedRole).isNotNull();
        Assertions.assertThat(returnedRole.getName()).isEqualTo(role.getName());
    }
}
