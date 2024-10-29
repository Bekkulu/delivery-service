package kg.mega.delivery_service.repository;

import kg.mega.delivery_service.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
