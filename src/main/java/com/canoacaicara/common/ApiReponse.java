package com.canoacaicara.common;

import java.util.List;

public class ApiReponse<T> {
    private Object message;
    private T data;

    public ApiReponse(Object message, T data) {
        this.message = message;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
