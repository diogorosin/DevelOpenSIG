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
    private String edification;

    @NonNull
    public Integer getAddress() {

        return address;

    }

    public void setAddress(@NonNull Integer address) {

        this.address = address;

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