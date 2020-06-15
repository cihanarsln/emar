package com.cihan.emar.error;

public class UsernameAlreadyUsedException extends RuntimeException {

    public UsernameAlreadyUsedException(){
        super("This username alread used.");
    }

}
