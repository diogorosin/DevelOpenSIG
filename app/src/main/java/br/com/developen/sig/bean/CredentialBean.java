package br.com.developen.sig.bean;

import java.io.Serializable;

public class CredentialBean implements Serializable {

    private String login;

    private String password;

    private Integer government;

    public String getLogin() {

        return login;

    }

    public void setLogin(String login) {

        this.login = login;

    }

    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

    public Integer getGovernment() {

        return government;

    }

    public void setGovernment(Integer government) {

        this.government = government;

    }

}