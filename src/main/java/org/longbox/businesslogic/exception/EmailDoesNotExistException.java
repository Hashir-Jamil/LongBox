package org.longbox.businesslogic.exception;

public class EmailDoesNotExistException extends Exception {

	public EmailDoesNotExistException(){
        super();
    }

    public EmailDoesNotExistException(String msg){
        super(msg);
    }

}
