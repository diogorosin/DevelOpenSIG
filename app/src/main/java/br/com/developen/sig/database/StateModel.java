package br.com.developen.sig.database;


import android.arch.persistence.room.Embedded;

import java.util.Objects;

public class StateModel {

    private Integer identifier;

    private String denomination;

    private String acronym;

    @Embedded(prefix = "country_")
    private CountryModel country;

    public Integer getIdentifier() {

        return identifier;

    }

    public void setIdentifier(Integer identifier) {

        this.identifier = identifier;

    }

    public String getDenomination() {

        return denomination;

    }

    public void setDenomination(String denomination) {

        this.denomination = denomination;

    }

    public String getAcronym() {

        return acronym;

    }

    public void setAcronym(String acronym) {

        this.acronym = acronym;

    }

    public CountryModel getCountry() {

        return country;

    }

    public void setCountry(CountryModel country) {

        this.country = country;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateModel that = (StateModel) o;
        return Objects.equals(identifier, that.identifier);

    }

    public int hashCode() {

        return Objects.hash(identifier);
    }

}