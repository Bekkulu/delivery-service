package kg.mega.delivery_service.exceptions;

public class CourierNotFoudException extends Exception {

    public CourierNotFoudException(Long id) {
        super(String.format("Courier not found with id: %s", id));
    }

    public CourierNotFoudException(String message) {
        super(message);
    }
}
