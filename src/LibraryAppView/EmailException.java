package LibraryAppView;

public class EmailException extends Exception{
    private static final String MESSAGE = "Email is of wrong format";

    public EmailException() {
        super(MESSAGE);
    }

    public EmailException(String message){
        super(message);
    }
}
