package mate.academy.springbootbookstore.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
