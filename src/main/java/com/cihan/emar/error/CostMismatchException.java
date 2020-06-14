package com.cihan.emar.error;

public class CostMismatchException extends RuntimeException {

    public CostMismatchException(){
        super("Actual cost and expected cost mismatch.");
    }

}
