package mate.academy.springbootbookstore.repository.role;

import mate.academy.springbootbookstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(Role.RoleName name);
}
