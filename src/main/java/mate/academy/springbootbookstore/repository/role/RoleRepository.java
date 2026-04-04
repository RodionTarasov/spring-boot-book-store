package mate.academy.springbootbookstore.repository.role;

import mate.academy.springbootbookstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
