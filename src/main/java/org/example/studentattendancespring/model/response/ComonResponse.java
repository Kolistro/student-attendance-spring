package org.example.studentattendancespring.model.response;

public class ComonResponse<T> {
    private final T data;
    private final int status;
    private final String messageError;

    public ComonResponse(int status, String messageError) {
        this.data = null;
        this.status = status;
        this.messageError = messageError;
    }


    public ComonResponse(T data, int status) {
        this.data = data;
        this.status = status;
        this.messageError = null;
    }

    public T getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessageError() {
        return messageError;
    }

    @Override
    public String toString() {
        return "ComonResponse{" +
                "data=" + data +
                ", status=" + status +
                ", messageError='" + messageError + '\'' +
                '}';
    }
}
