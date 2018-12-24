package br.com.developen.sig.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

@Entity(tableName = "AddressEdification",
        primaryKeys = {"address", "edification"},
        foreignKeys = {
                @ForeignKey(entity = AddressVO.class,
                        parentColumns = "identifier",
                        childColumns = "address",
                        onDelete = ForeignKey.RESTRICT,
                        onUpdate = ForeignKey.CASCADE)},
        indices = {@Index(value={"address", "edification"})})
public class AddressEdificationVO {

    @NonNull
    @ColumnInfo(name="address")
    private Integer address;

    @NonNull
    @ColumnInfo(name="edification")
    private Integer edification;

    @NonNull
    @ColumnInfo(name="type")
    private Integer type;

    @ColumnInfo(name="reference")
    private String reference;

    public Integer getAddress() {

        return address;

    }

    public void setAddress(Integer address) {

        this.address = address;

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
        AddressEdificationVO that = (AddressEdificationVO) o;
        if (!address.equals(that.address)) return false;
        return edification.equals(that.edification);

    }

    public int hashCode() {

        int result = address.hashCode();
        result = 31 * result + edification.hashCode();
        return result;

    }

}