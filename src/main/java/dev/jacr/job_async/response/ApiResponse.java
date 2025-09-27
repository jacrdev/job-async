package dev.jacr.job_async.response;

public class ApiResponse<T> {

    private String status;
    private int code;
    private String message;
    private T data;
    private Object errors;

    public ApiResponse(String status, int code, String message, T data, Object errors) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public static <T> ApiResponse<T> success(int code, String message, T data) {
        return new ApiResponse<>("success", code, message, data,null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", 200, "Operación exitosa", data, null);
    }

    public static <T> ApiResponse<T> error(int code, String message, Object errors) {
        return new ApiResponse<>("error", code, message, null, errors);
    }

    public static <T> ApiResponse<T> error(int code, Object errors) {
        return new ApiResponse<>("error", code, null, null, errors);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
