package exception;

public class AddingStudentsException extends Exception{
    public AddingStudentsException() {
        super("Error adding applied students!");
    }
    public AddingStudentsException(String message) {
        super(message);
    }
}
