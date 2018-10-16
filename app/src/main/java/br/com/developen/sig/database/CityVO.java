package br.com.developen.sig.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "City",
        foreignKeys = {
                @ForeignKey(entity = StateVO.class,
                        parentColumns = "identifier",
                        childColumns = "state",
                        onDelete = ForeignKey.RESTRICT,
                        onUpdate = ForeignKey.CASCADE)},
        indices = {@Index("identifier")})
public class CityVO {

    @PrimaryKey
    @ColumnInfo(name="identifier")
    private Integer identifier;

    @NonNull
    @ColumnInfo(name="denomination")
    private String denomination;

    @NonNull
    @ColumnInfo(name="state")
    private Integer state;

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
    public Integer getState() {

        return state;

    }

    public void setState(@NonNull Integer state) {

        this.state = state;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityVO countryVO = (CityVO) o;
        return identifier.equals(countryVO.identifier);

    }

    public int hashCode() {

        return identifier.hashCode();

    }

}