package exception;

public class ChangeStatementException extends Exception{
    public ChangeStatementException() {
        super("Error changing statement!");
    }
    public ChangeStatementException(String message) {
        super(message);
    }
}
