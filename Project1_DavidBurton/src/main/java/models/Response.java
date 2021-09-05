package models;

public class Response {

    String message;
    Boolean success;
    Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Response(String message, Boolean success, Object data){
        this.message = message;
        this.success = success;
        this.data = data;
    }
}