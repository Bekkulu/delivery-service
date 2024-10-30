package kg.mega.delivery_service.controller;

import kg.mega.delivery_service.exceptions.ParcelCreatedException;
import kg.mega.delivery_service.exceptions.ParcelNotFoundException;
import kg.mega.delivery_service.exceptions.UpdateParcelException;
import kg.mega.delivery_service.model.entity.Parcel;
import kg.mega.delivery_service.service.ParcelService;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class ParcelController {
    private final ParcelService parcelService;

    @PostMapping("/create")
    public ResponseEntity<?> createParcel(@RequestBody @Validated Parcel parcel) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Parcel created successfully");
        } catch (ParcelCreatedException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Unable to create parcel.Error " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected Error " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getParcel(@PathVariable long id) {
        try {
            return ResponseEntity.ok(parcelService.getParcelStatus(id));
        } catch (ParcelNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Parcel with id " + id + " not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred " + e.getMessage());
        }
    }

    @PutMapping("/{id}/udpate_parcel")
    public ResponseEntity<?> updateParcel(@RequestBody Parcel parcel, @PathVariable Long id) {
        try {
            parcelService.updateParcel(parcel, id);
            return ResponseEntity.ok().build();
        } catch (UpdateParcelException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Unable to update. Error: " + e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Unexpected Error Occured: " + e.getMessage());
        }
    }
}
