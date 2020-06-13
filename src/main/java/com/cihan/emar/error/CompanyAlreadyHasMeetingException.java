package com.cihan.emar.error;

public class CompanyAlreadyHasMeetingException extends RuntimeException {

    public CompanyAlreadyHasMeetingException(){
        super("Company already has a meeting on this date.");
    }

}
