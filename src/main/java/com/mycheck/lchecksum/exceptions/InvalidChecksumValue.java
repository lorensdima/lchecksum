package com.mycheck.lchecksum.exceptions;

public class InvalidChecksumValue extends RuntimeException {
    public InvalidChecksumValue(String message) {
        super(message);
    }
}
