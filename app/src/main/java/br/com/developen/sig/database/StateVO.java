package br.com.developen.sig.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "State",
        foreignKeys = {
                @ForeignKey(entity = CountryVO.class,
                        parentColumns = "identifier",
                        childColumns = "country",
                        onDelete = ForeignKey.RESTRICT,
                        onUpdate = ForeignKey.CASCADE)},
        indices = {@Index("identifier")})
public class StateVO {

    @PrimaryKey
    @ColumnInfo(name="identifier")
    private Integer identifier;

    @NonNull
    @ColumnInfo(name="denomination")
    private String denomination;

    @NonNull
    @ColumnInfo(name="acronym")
    private String acronym;

    @NonNull
    @ColumnInfo(name="country")
    private Integer country;

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

    @NonNull
    public Integer getCountry() {

        return country;

    }

    public void setCountry(@NonNull Integer country) {

        this.country = country;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateVO countryVO = (StateVO) o;
        return identifier.equals(countryVO.identifier);

    }

    public int hashCode() {

        return identifier.hashCode();

    }

}