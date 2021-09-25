package dev.patika.creditscore.exceptions;

public class NoUserExistWithThisID extends RuntimeException {

    public NoUserExistWithThisID(String message) {
        super(message);
    }

}
