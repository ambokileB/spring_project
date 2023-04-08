package com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private boolean error;
    private int code;
    private T data;
    private List<T> dataList;
    private String message;


    public Response(boolean error, int code, String message) {
        this.error = error;
        this.code = code;
        this.message = message;
    }

    public Response(boolean error, int code, T data) {
        this.error = error;
        this.code = code;
        this.data = data;
    }

    public Response(boolean error, int code, List<T> dataList) {
        this.error = error;
        this.code = code;
        this.dataList = dataList;
    }

    public Response(boolean error, int code, T data, String message) {
        this.error = error;
        this.code = code;
        this.data = data;
        this.message = message;
    }
}