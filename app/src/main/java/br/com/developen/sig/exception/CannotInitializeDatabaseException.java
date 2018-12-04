package br.com.developen.sig.exception;

import br.com.developen.sig.util.Messaging;


public class CannotInitializeDatabaseException extends Exception implements Messaging {

    public CannotInitializeDatabaseException(){

        super("Não foi possível iniciar o banco de dados.");

    }

    public String[] getMessages(){

        return new String[]{ getMessage() };

    }

}