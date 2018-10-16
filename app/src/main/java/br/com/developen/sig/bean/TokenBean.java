package br.com.developen.sig.bean;

import java.io.Serializable;

public class TokenBean implements Serializable{

    private String identifier;

    private GovernmentBean government;

    private UserBean user;

    private Integer level;

    public String getIdentifier() {

        return identifier;

    }

    public void setIdentifier(String identifier) {

        this.identifier = identifier;

    }

    public GovernmentBean getGovernment() {

        return government;

    }

    public void setGovernment(GovernmentBean government) {

        this.government = government;

    }

    public UserBean getUser() {

        return user;

    }

    public void setUser(UserBean user) {

        this.user = user;

    }

    public Integer getLevel() {

        return level;

    }

    public void setLevel(Integer level) {

        this.level = level;

    }

}