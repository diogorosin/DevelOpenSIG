package br.com.developen.sig.exception;


import br.com.developen.sig.util.Messaging;

public class CannotLoadPlacesException extends Exception implements Messaging {

    public CannotLoadPlacesException(){

        super("Não foi possível carregar os endereços.");

    }

    public String[] getMessages(){

        return new String[]{getMessage()};

    }

}