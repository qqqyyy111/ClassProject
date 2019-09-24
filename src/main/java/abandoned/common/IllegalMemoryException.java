package abandoned.common;

public class IllegalMemoryException extends Exception {
    private String message;

    public IllegalMemoryException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
