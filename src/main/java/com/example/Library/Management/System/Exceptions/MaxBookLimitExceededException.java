package com.example.Library.Management.System.Exceptions;

public class MaxBookLimitExceededException extends Exception {
    public MaxBookLimitExceededException(String message) {
        super(message);
    }
}
