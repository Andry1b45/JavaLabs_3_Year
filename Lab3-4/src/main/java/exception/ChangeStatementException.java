package exception;

public class ChangeStatementException extends RuntimeException{
    public ChangeStatementException() {
        super("Error changing statement!");
    }
    public ChangeStatementException(String message) {
        super(message);
    }
}
