package kg.mega.delivery_service.repository;

import kg.mega.delivery_service.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
