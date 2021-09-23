package dev.patika.userservice.exceptions;

public class CustomerIsNotExist extends RuntimeException {

    public CustomerIsNotExist(String message) {
        super(message);
    }

}
