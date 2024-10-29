package kg.mega.delivery_service.repository;

import kg.mega.delivery_service.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
