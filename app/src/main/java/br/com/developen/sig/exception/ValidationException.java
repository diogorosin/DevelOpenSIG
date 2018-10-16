package br.com.developen.sig.exception;


import br.com.developen.sig.util.Messaging;

public class ValidationException extends Exception implements Messaging {

    public ValidationException(String message){

        super(message);

    }

    public String[] getMessages(){

        return new String[]{ getMessage() };

    }

}