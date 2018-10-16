package br.com.developen.sig.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Agency")
public class AgencyVO {

    @PrimaryKey
    @ColumnInfo(name="identifier")
    private Integer identifier;

    @NonNull
    @ColumnInfo(name="denomination")
    private String denomination;

    @NonNull
    @ColumnInfo(name="acronym")
    private String acronym;

    public Integer getIdentifier() {

        return identifier;

    }

    public void setIdentifier(Integer identifier) {

        this.identifier = identifier;

    }

    @NonNull
    public String getDenomination() {

        return denomination;

    }

    public void setDenomination(@NonNull String denomination) {

        this.denomination = denomination;

    }

    @NonNull
    public String getAcronym() {

        return acronym;

    }

    public void setAcronym(@NonNull String acronym) {

        this.acronym = acronym;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgencyVO countryVO = (AgencyVO) o;
        return identifier.equals(countryVO.identifier);

    }

    public int hashCode() {

        return identifier.hashCode();

    }

}