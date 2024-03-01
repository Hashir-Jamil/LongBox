package org.longbox.businesslogic.exception;

public class UsernameOrEmailExistsException extends Exception{
    public UsernameOrEmailExistsException(){
        super();
    }

    public UsernameOrEmailExistsException(String msg){
        super(msg);
    }
}
