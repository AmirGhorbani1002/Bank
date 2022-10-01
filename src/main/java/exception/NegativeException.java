package exception;

public class NegativeException extends RuntimeException{

    public NegativeException() {
        super("Amount can't be negative");
    }

    public NegativeException(String message) {
        super(message);
    }
}
