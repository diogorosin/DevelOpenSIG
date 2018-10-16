package br.com.developen.sig.bean;


import java.io.Serializable;

public class IntegerBean implements Serializable {

    private Integer value;

    public IntegerBean(){

        this.value = 0;

    }

    public IntegerBean(Integer value){

        this.value = value;

    }

    public Integer getValue() {

        return value;

    }

    public void setValue(Integer value) {

        this.value = value;

    }

}