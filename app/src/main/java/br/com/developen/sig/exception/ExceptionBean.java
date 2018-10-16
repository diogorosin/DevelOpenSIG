package br.com.developen.sig.exception;

import br.com.developen.sig.util.Messaging;

public class ExceptionBean implements Messaging {

    private String[] messages;

    public ExceptionBean(){

        this.messages = new String[]{};

    }

    public ExceptionBean(String message){

        this.messages = new String[]{ message };

    }

    public ExceptionBean(String[] messages){

        this.messages = messages;

    }

    public String[] getMessages() {

        return this.messages;

    }

}