package br.com.developen.sig.database;


import android.arch.persistence.room.Embedded;

import java.util.Objects;

public class ModifiedAddressEdificationModel {

    @Embedded(prefix = "modifiedAddress_")
    private ModifiedAddressModel modifiedAddress;

    private Integer edification;

    private Integer type;

    private String reference;


    public ModifiedAddressModel getModifiedAddress() {

        return modifiedAddress;

    }

    public void setModifiedAddress(ModifiedAddressModel modifiedAddress) {

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
        ModifiedAddressEdificationModel that = (ModifiedAddressEdificationModel) o;
        return Objects.equals(modifiedAddress, that.modifiedAddress) &&
                Objects.equals(edification, that.edification);

    }

    public int hashCode() {

        return Objects.hash(modifiedAddress, edification);

    }

}