package com.cihan.emar.error;

public class RoomNotAvailableException extends RuntimeException {

    public RoomNotAvailableException(){
        super("Meeting room has been reserved. Please try for other times");
    }

}
