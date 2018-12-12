package br.com.developen.sig.database.modified;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(tableName = "ModifiedAddressEdification",
        primaryKeys = {"modifiedAddress", "edification"},
        foreignKeys = {
                @ForeignKey(entity = ModifiedAddressVO.class,
                        parentColumns = "identifier",
                        childColumns = "modifiedAddress",
                        onDelete = ForeignKey.RESTRICT,
                        onUpdate = ForeignKey.CASCADE)},
        indices = {@Index(value={"modifiedAddress", "edification"})})
public class ModifiedAddressEdificationVO {

    @NonNull
    @ColumnInfo(name="modifiedAddress")
    private Integer modifiedAddress;

    @NonNull
    @ColumnInfo(name="edification")
    private String edification;

    @NonNull
    public Integer getModifiedAddress() {

        return modifiedAddress;

    }

    public void setModifiedAddress(@NonNull Integer modifiedAddress) {

        this.modifiedAddress = modifiedAddress;

    }

    @NonNull
    public String getEdification() {

        return edification;

    }

    public void setEdification(@NonNull String edification) {

        this.edification = edification;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModifiedAddressEdificationVO that = (ModifiedAddressEdificationVO) o;
        return Objects.equals(modifiedAddress, that.modifiedAddress) &&
                Objects.equals(edification, that.edification);

    }

    public int hashCode() {

        return Objects.hash(modifiedAddress, edification);

    }

}