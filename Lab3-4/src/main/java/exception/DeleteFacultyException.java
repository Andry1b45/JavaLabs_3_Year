package exception;

public class DeleteFacultyException extends Exception{
    public DeleteFacultyException() {
        super("Error deleting faculty!");
    }
    public DeleteFacultyException(String message) {
        super(message);
    }
}
