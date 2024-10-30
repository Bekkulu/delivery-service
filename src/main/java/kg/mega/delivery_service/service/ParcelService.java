package kg.mega.delivery_service.service;

import kg.mega.delivery_service.enums.ParcelStatus;
import kg.mega.delivery_service.exceptions.ParcelNotFoundException;
import kg.mega.delivery_service.model.entity.Parcel;
import kg.mega.delivery_service.repository.AddressRepository;
import kg.mega.delivery_service.repository.ParcelRepository;
import kg.mega.delivery_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ParcelService {
    public final ParcelRepository parcelRepository;
    public final UserRepository userRepository;
    private final AddressRepository addressRepository;


    public void createParcel(Parcel parcel) {
        try {
            parcelRepository.save(parcel);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateParcel(Parcel parcel, Long id) {
        try {
            if(!Objects.equals(parcel.getId(), id)) throw new BadRequestException();
            Parcel parcel1 = parcelRepository.findById(id).orElse(null);
            parcel1.setId(parcel.getId());
            parcel1.setStatus(parcel.getStatus());
            parcel1.setBeginDate(parcel.getBeginDate());
            parcel1.setPickupAddressId(parcel.getPickupAddressId());
            parcel1.setDeliveryAddressId(parcel.getDeliveryAddressId());
            parcel1.setWeight(parcel.getWeight());
            parcel1.setUser(parcel.getUser());
            parcel1.setCourier(parcel.getCourier());
            parcelRepository.save(parcel1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Parcel getParcel(Long id) throws ParcelNotFoundException {
        return parcelRepository.findById(id).orElseThrow(() -> new ParcelNotFoundException(id));

    }

    public void deleteParcel(Long id) {

        try {
            parcelRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(ParcelStatus parcelStatus, Long id) {
        try {
            Parcel parcel = getParcel(id);
            parcel.setStatus(parcelStatus);
            parcelRepository.save(parcel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ParcelStatus getParcelStatus(Long id) throws ParcelNotFoundException {
        Parcel parcel = getParcel(id);
        return parcel.getStatus();

    }


}




