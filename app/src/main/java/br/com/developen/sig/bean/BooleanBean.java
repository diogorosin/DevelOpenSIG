package br.com.developen.sig.bean;

public class BooleanBean{

	private Boolean value;
	
	public BooleanBean(){}

	public BooleanBean(Boolean value){

		this.setValue(value);

	}

	public Boolean getValue() {

		return value;

	}

	public void setValue(Boolean value) {

		this.value = value;

	}

}
