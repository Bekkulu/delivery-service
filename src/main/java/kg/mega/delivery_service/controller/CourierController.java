package kg.mega.delivery_service.controller;

import kg.mega.delivery_service.exceptions.AddressCreateException;
import kg.mega.delivery_service.exceptions.AddressDeleteException;
import kg.mega.delivery_service.exceptions.CourierCreateException;
import kg.mega.delivery_service.exceptions.CourierDeleteException;
import kg.mega.delivery_service.model.entity.Address;
import kg.mega.delivery_service.model.entity.Courier;
import kg.mega.delivery_service.service.CourierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/couriers")
public class CourierController {

    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAddress(@Validated @RequestBody Courier courier) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Address created successfully");
        } catch (CourierCreateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Unable to create address. Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error.: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourierByid(@PathVariable long id) {
        try {
            Courier courier = courierService.getCourier(id);
            if (courier == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No courier found for id: " + id);
            }
            return ResponseEntity.ok(courier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Unexpected error. Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete_{id}")
    public ResponseEntity<?> deleteCourier(@PathVariable long id) {
        try {
            courierService.deleteCourier(id);
            return ResponseEntity.ok().body("Courier with id " + id + "deleted successfully");
        } catch (CourierDeleteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Unexpected error occured: " + e.getMessage());
        }
    }
}
