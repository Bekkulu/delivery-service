package kg.mega.delivery_service.service;

import kg.mega.delivery_service.model.entity.Address;
import kg.mega.delivery_service.repository.AddressRepository;
import kg.mega.delivery_service.repository.CourierRepository;
import kg.mega.delivery_service.repository.ParcelRepository;
import kg.mega.delivery_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final ParcelRepository parcelRepository;
    private final CourierRepository courierRepository;

    public void createAddress(Address address) {
        try{
            addressRepository.save(address);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public Address getAddress(Long id) {
        try {
            addressRepository.findById(id).orElseThrow();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return addressRepository.getById(id);
    }

    public void deleteAddress(Long id) {
        try {
            addressRepository.deleteById(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }






}
