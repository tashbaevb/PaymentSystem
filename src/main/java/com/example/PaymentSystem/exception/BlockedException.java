package com.example.PaymentSystem.exception;

public class BlockedException extends RuntimeException {
    public BlockedException() {
        super();
    }

    public BlockedException(String message) {
        super(message);
    }

}