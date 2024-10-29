package kg.mega.delivery_service.service;

import kg.mega.delivery_service.exceptions.CourierNotFoudException;
import kg.mega.delivery_service.model.entity.Courier;
import kg.mega.delivery_service.model.entity.Parcel;
import kg.mega.delivery_service.repository.CourierRepository;
import kg.mega.delivery_service.repository.ParcelRepository;
import kg.mega.delivery_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CourierService {

    public final CourierRepository courierRepository;
    public final UserRepository userRepository;
    public final AddressService addressService;
    public final ParcelRepository parcelRepository;

    public void createCourier (Courier courier) {
        try{
            courierRepository.save(courier);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public  void updateCourier (Courier courier, Long id) {
        try {
            Courier courier1 = courierRepository.findById(id).orElse(null);
            courier1.setEmail(courier.getEmail());
            courier1.setParcels(courier.getParcels());
            courier1.setPhone(courier.getPhone());
            courier1.setUserName(courier.getUserName());
            courierRepository.save(courier1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void deleteCourier (Long id) {
        try{
            courierRepository.deleteById(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void setCourierParcels(Set<Parcel> parcels,Long id) throws CourierNotFoudException {
        Courier courier = courierRepository.findById(id).orElseThrow(()->new CourierNotFoudException(id));
        courier.setParcels(parcels);
        courierRepository.save(courier);
    }
    public Courier getCourier (Long id){
        try{
            courierRepository.findById(id).orElseThrow();
        }catch (Exception e){
            e.printStackTrace();
        }
        return courierRepository.getById(id);
    }
}

