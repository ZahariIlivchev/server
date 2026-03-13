package diploma.server.model.type;

public class ApiResponce<T> {
    private String message;
    private T payload;

    public ApiResponce(String message) {
        this.message = message;
        this.payload = null;
    }

    public ApiResponce(String message, T payload) {
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
