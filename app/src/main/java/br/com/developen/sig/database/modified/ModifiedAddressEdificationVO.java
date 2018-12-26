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
    private Integer edification;

    @NonNull
    @ColumnInfo(name="type")
    private Integer type;

    @ColumnInfo(name="reference")
    private String reference;

    public Integer getModifiedAddress() {

        return modifiedAddress;

    }

    public void setModifiedAddress(Integer modifiedAddress) {

        this.modifiedAddress = modifiedAddress;

    }

    public Integer getEdification() {

        return edification;

    }

    public void setEdification(Integer edification) {

        this.edification = edification;

    }

    public Integer getType() {

        return type;

    }

    public void setType(Integer type) {

        this.type = type;

    }

    public String getReference() {

        return reference;

    }

    public void setReference(String reference) {

        this.reference = reference;

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