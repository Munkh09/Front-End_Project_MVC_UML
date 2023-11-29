package LibraryAppView;

public class AtSignMissingException extends EmailException{
    private static final String MESSAGE = "Password must have an '@' sign";

    public AtSignMissingException() {
        super(MESSAGE);
    }

    public AtSignMissingException(String message){
        super(message);
    }
}
