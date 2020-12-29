package exception;

public class DeleteFacultyException extends RuntimeException{
    public DeleteFacultyException() {
        super("Error deleting faculty!");
    }
    public DeleteFacultyException(String message) {
        super(message);
    }
}
