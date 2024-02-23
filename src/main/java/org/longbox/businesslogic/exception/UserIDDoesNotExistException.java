package org.longbox.businesslogic.exception;

public class UserIDDoesNotExistException extends Exception{
    public UserIDDoesNotExistException(){
        super();
    }

    public UserIDDoesNotExistException(String msg){
        super(msg);
    }
}
