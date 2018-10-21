package br.com.developen.sig.database;


import android.arch.persistence.room.Embedded;

import java.util.Objects;

public class CityModel {

    private Integer identifier;

    private String denomination;

    @Embedded(prefix = "state_")
    private StateModel state;

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

    public StateModel getState() {

        return state;

    }

    public void setState(StateModel state) {

        this.state = state;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityModel cityModel = (CityModel) o;
        return Objects.equals(identifier, cityModel.identifier);

    }

    public int hashCode() {

        return Objects.hash(identifier);
    }

    public String toString(){

        return getDenomination() + "-" + getState().getAcronym();

    }

}