package com.afdevelopment.biblioteca.exception.auth;

public class WrongPasswordException extends RuntimeException{
    private final static String code = "EX-015";
    public static String getCode(){
        return code;
    }
    public WrongPasswordException(String message) {
        super(message);
    }
}
