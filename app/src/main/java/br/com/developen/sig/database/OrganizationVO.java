package br.com.developen.sig.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Organization")
public class OrganizationVO {

    @PrimaryKey
    @ColumnInfo(name="identifier")
    private Integer identifier;

    @NonNull
    @ColumnInfo(name="active")
    private Boolean active;

    @NonNull
    @ColumnInfo(name="denomination")
    private String denomination;

    @ColumnInfo(name="fancyName")
    private String fancyName;

    public Integer getIdentifier() {

        return identifier;

    }

    public void setIdentifier(Integer identifier) {

        this.identifier = identifier;

    }

    @NonNull
    public Boolean getActive() {

        return active;

    }

    public void setActive(@NonNull Boolean active) {

        this.active = active;

    }

    @NonNull
    public String getDenomination() {

        return denomination;

    }

    public void setDenomination(@NonNull String denomination) {

        this.denomination = denomination;

    }

    public String getFancyName() {

        return fancyName;

    }

    public void setFancyName(String fancyName) {

        this.fancyName = fancyName;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationVO that = (OrganizationVO) o;
        return identifier.equals(that.identifier);

    }

    @Override
    public int hashCode() {

        return identifier.hashCode();

    }

}