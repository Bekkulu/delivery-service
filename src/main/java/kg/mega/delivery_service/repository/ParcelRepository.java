package kg.mega.delivery_service.repository;

import kg.mega.delivery_service.model.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelRepository extends JpaRepository<Parcel,Long> {
}
