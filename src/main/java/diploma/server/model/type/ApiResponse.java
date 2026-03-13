package diploma.server.model.type;

public class ApiResponse<T> {
    private String message;
    private T payload;

    public ApiResponse(String message) {
        this.message = message;
        this.payload = null;
    }

    public ApiResponse(String message, T payload) {
        this.message = message;
        this.payload = payload;
    }

    public String getMessage() {
        return message;
    }

    public T getPayload() {
        return payload;
    }
}
