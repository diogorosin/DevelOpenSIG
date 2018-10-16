package br.com.developen.sig.exception;


import br.com.developen.sig.util.Messaging;

public class HttpRequestException extends Exception implements Messaging {

    public HttpRequestException(){

        super("Sem conexão com a internet ou servidor fora de operação.");

    }

    public String[] getMessages(){

        return new String[]{getMessage()};

    }

}