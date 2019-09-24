package abandoned.common;

public class InvalidInstructionException extends Exception {
    private String message;

    public InvalidInstructionException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
