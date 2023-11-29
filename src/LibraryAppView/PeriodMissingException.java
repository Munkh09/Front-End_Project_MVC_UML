package LibraryAppView;
public class PeriodMissingException extends EmailException{
    private static final String MESSAGE = "Password must have a period.";

    public PeriodMissingException() {
        super(MESSAGE);
    }

    public PeriodMissingException(String message){
        super(message);
    }
}

