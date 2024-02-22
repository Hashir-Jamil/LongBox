package org.longbox.businesslogic.exception;

public class UserNameDoesNotExistException extends Exception{
    public UserNameDoesNotExistException(){
        super();
    }

    public UserNameDoesNotExistException(String msg){
        super(msg);
    }
}
