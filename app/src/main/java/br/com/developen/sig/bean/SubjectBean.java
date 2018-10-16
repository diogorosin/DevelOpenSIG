package br.com.developen.sig.bean;

import java.io.Serializable;

public class SubjectBean implements Serializable {

    private Integer identifier;

    private Boolean active;

    public Integer getIdentifier() {

        return identifier;

    }

    public void setIdentifier(Integer identifier) {

        this.identifier = identifier;

    }

    public Boolean getActive() {

        return active;

    }

    public void setActive(Boolean active) {

        this.active = active;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectBean that = (SubjectBean) o;
        return identifier.equals(that.identifier);

    }

    public int hashCode() {

        return identifier.hashCode();

    }

}