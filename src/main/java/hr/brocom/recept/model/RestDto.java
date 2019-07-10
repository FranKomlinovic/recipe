package hr.brocom.recept.model;

public class RestDto<T> {

    private T       data;
    private boolean success;
    private String  message;

    public static RestDto<Void> success(Enum<?> message) {
        return RestDto.success(message.toString());
    }

    public static RestDto<Void> success(String message) {
        return success(null, message);
    }

    public static <T> RestDto<T> success(T data, String message) {
        RestDto<T> model = new RestDto<>();
        model.setSuccess(true);
        model.setMessage(message);
        model.setData(data);
        return model;
    }

    public static RestDto<Void> success() {
        return success(null, "");
    }

    public static <T> RestDto<T> success(T data) {
        return success(data, null);
    }

    public static <T> RestDto<T> fail(String message) {
        RestDto<T> model = new RestDto<>();
        model.setSuccess(false);
        model.setMessage(message);
        return model;
    }

    public static <T> RestDto<T> fail(T data) {
        RestDto<T> model = new RestDto<>();
        model.setSuccess(false);
        model.setData(data);
        return model;
    }

    public static <T> RestDto<T> fail(T data, String message) {
        RestDto<T> model = new RestDto<>();
        model.setSuccess(false);
        model.setData(data);
        model.setMessage(message);
        return model;
    }

    public T getData() {
        return data;
    }

    private void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RestDto{" + "data=" + data + ", success=" + success + ", message='" + message + '\'' + '}';
    }
}
