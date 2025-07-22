package com.nowakpawel.Atipera.helpers;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg) {
        super((msg));
    }
}
