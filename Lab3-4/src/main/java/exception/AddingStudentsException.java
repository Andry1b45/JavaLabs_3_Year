package exception;

public class AddingStudentsException extends RuntimeException{
    public AddingStudentsException() {
        super("Error adding applied students!");
    }
    public AddingStudentsException(String message) {
        super(message);
    }
}
