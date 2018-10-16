package br.com.developen.sig.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Subject")
public class SubjectVO {

    @PrimaryKey
    @ColumnInfo(name="identifier")
    private Integer identifier;

    public Integer getIdentifier() {

        return identifier;

    }

    public void setIdentifier(Integer identifier) {

        this.identifier = identifier;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectVO countryVO = (SubjectVO) o;
        return identifier.equals(countryVO.identifier);

    }

    public int hashCode() {

        return identifier.hashCode();

    }

}