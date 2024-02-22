package org.longbox.businesslogic.exception;

public class UsernameExistsException extends Exception{
    public UsernameExistsException(){
        super();
    }

    public UsernameExistsException(String msg){
        super(msg);
    }
}
