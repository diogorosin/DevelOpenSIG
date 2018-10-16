package br.com.developen.sig.bean;


public class OrganizationBean extends SubjectBean{

    private String denomination;

    private String fancyName;

    public String getDenomination() {

        return denomination;

    }

    public void setDenomination(String denomination) {

        this.denomination = denomination;

    }

    public String getFancyName() {

        return fancyName;

    }

    public void setFancyName(String fancyName) {

        this.fancyName = fancyName;

    }

    public String toString(){

        return getDenomination();

    }

}