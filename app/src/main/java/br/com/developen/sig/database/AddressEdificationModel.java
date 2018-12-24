package br.com.developen.sig.database;

import android.arch.persistence.room.Embedded;

public class AddressEdificationModel {

    @Embedded(prefix = "address_")
    private AddressModel address;

    private Integer edification;

    private Integer type;

    private String reference;

    public AddressModel getAddress() {

        return address;

    }

    public void setAddress(AddressModel address) {

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
        AddressEdificationModel that = (AddressEdificationModel) o;
        if (!address.equals(that.address)) return false;
        return edification.equals(that.edification);

    }

    public int hashCode() {

        int result = address.hashCode();
        result = 31 * result + edification.hashCode();
        return result;

    }

}