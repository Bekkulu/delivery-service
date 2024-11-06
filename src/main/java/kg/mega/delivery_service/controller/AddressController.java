package kg.mega.delivery_service.controller;

import kg.mega.delivery_service.exceptions.AddressCreateException;
import kg.mega.delivery_service.exceptions.AddressDeleteException;
import kg.mega.delivery_service.exceptions.AddressNotFoundException;
import kg.mega.delivery_service.model.entity.Address;
import kg.mega.delivery_service.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Addresses")
@AllArgsConstructor

public class AddressController {
    private final AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<?> createAddress(@Validated @RequestBody Address address) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Address created successfully");
        } catch (AddressCreateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Unable to create address. Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error.: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllAddresses(@PathVariable long id) {
        try {
            List<Address> addresses = addressService.getAddressesByUserId(id);
            if (addresses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No addresses found for id: " + id);
            }
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Unexpected error. Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete_{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable long id) {
        try {
            addressService.deleteAddress(id);
            return ResponseEntity.ok().body("Address with id " + id + "deleted successfully");
        } catch (AddressDeleteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Unexpected error occured: " + e.getMessage());
        }
    }

}
