package kg.mega.delivery_service.repository;

import kg.mega.delivery_service.model.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier, Long> {
}
