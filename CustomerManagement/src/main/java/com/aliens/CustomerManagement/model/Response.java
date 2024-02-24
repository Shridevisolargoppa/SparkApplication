package com.aliens.CustomerManagement.model;

public class Response<T>
{
    private T data;
    private String errorMessage;

    private Response(T data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
    }



    public T getData() {
        return data;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(data, null);
    }

    public static <T> Response<T> error(String errorMessage) {
        return new Response<>(null, errorMessage);
    }


    public String getErrorMessage() {
        return errorMessage;
    }
}
