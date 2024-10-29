package kg.mega.delivery_service.exceptions;

public class ParcelNotFoundException extends Exception {

  public ParcelNotFoundException(Long id) {
    super(String.format("Parcel not found with id: %s", id));
  }

  public ParcelNotFoundException(String message) {
        super(message);
    }
}
