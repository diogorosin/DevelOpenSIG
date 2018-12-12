package br.com.developen.sig.database.modified;


import java.util.Objects;

public class ModifiedAddressEdificationModel {

    private Integer modifiedAddress;

    private String edification;

    public Integer getModifiedAddress() {

        return modifiedAddress;

    }

    public void setModifiedAddress(Integer modifiedAddress) {

        this.modifiedAddress = modifiedAddress;

    }

    public String getEdification() {

        return edification;

    }

    public void setEdification(String edification) {

        this.edification = edification;

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