package exception;

public class NotEnoughException extends RuntimeException{

    public NotEnoughException() {
        super("Amount is not enough!!");
    }

    public NotEnoughException(String message) {
        super(message);
    }
}
