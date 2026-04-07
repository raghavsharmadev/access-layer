package com.rs.accesslayer.model;

import lombok.Getter;

@Getter
public class ResponseModel {
    private boolean success;
    private String message;
    private Object data;

    ResponseModel(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    ResponseModel(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public static ResponseModel success(final String message) {
        return new ResponseModel(true, message);
    }

    public static ResponseModel success(final Object data) {
        return new ResponseModel(true, data);
    }

    public static ResponseModel failure(final String message) {
        return new ResponseModel(false, message);
    }

    public static ResponseModel failure(final Object data) {
        return new ResponseModel(false, data);
    }
}
