package com.myblog.helper;

public class DoesNotExistException extends Exception {
    public DoesNotExistException(String msg) {
        super(msg);
    }
}