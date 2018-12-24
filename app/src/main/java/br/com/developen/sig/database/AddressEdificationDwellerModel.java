package br.com.developen.sig.database;

import android.arch.persistence.room.Embedded;

import java.util.Objects;

public class AddressEdificationDwellerModel {

    @Embedded(prefix = "addressEdification_")
    private AddressEdificationModel addressEdification;

    private Integer dweller;

    @Embedded(prefix = "subject_")
    private SubjectView subject;

    public AddressEdificationModel getAddressEdification() {

        return addressEdification;

    }

    public void setAddressEdification(AddressEdificationModel addressEdification) {

        this.addressEdification = addressEdification;

    }

    public Integer getDweller() {

        return dweller;

    }

    public void setDweller(Integer dweller) {

        this.dweller = dweller;

    }

    public SubjectView getSubject() {

        return subject;

    }

    public void setSubject(SubjectView subject) {

        this.subject = subject;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEdificationDwellerModel that = (AddressEdificationDwellerModel) o;
        return Objects.equals(addressEdification, that.addressEdification) &&
                Objects.equals(dweller, that.dweller);

    }

    public int hashCode() {

        return Objects.hash(addressEdification, dweller);

    }

}