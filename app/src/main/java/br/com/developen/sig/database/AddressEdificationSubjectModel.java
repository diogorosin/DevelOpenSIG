package br.com.developen.sig.database;

import android.arch.persistence.room.Embedded;

public class AddressEdificationSubjectModel {

    @Embedded(prefix = "address_")
    private AddressModel address;

    private String edification;

    @Embedded(prefix = "subject_")
    private SubjectView subject;

    public AddressModel getAddress() {

        return address;

    }

    public void setAddress(AddressModel address) {

        this.address = address;

    }

    public String getEdification() {

        return edification;

    }

    public void setEdification(String edification) {

        this.edification = edification;

    }

    public SubjectView getSubject() {

        return subject;

    }

    public void setSubject(SubjectView subject) {

        this.subject = subject;

    }

}