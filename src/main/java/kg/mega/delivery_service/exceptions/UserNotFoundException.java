package kg.mega.delivery_service.exceptions;

public class UserNotFoundException extends RuntimeException{


    public UserNotFoundException(String message) {
        super(message);
    }
}
