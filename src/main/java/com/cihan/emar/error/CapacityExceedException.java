package com.cihan.emar.error;

public class CapacityExceedException extends RuntimeException {

    public CapacityExceedException(int capacity){
        super("This meeting room’s capacity restricted " + capacity + " people.");
    }

}
