package com.example.demo.seleniumtest.corejava.enumstest;

public enum HttpMehods {

    GET("get", 200), POST("created", 201), PUT("update", 200), DELETE("delete", 200);

    private final String method;
    private final int status_Code;

    private HttpMehods(String method, int code) {
        this.method = method;
        this.status_Code = code;
    }

    public int getStatus_Code() {
        return status_Code;
    }

    public String getMethod() {
        return method;
    }

}
