package br.com.developen.sig.exception;

import br.com.developen.sig.util.Messaging;

public class InternalException extends Exception implements Messaging {

    public InternalException(){

        super("Erro interno de processamento.");

    }

    public String[] getMessages(){

        return new String[]{ getMessage() };

    }

}