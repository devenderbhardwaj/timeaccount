package com.myblog.helper;

public class WrongPassword extends Exception{
    public WrongPassword() {
        super("Wrong password");
    }
}
