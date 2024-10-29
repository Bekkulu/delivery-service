package kg.mega.delivery_service.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id) {
        super(String.format("User not found with id: %s", id));
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
