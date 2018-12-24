package br.com.developen.sig.database.modified;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;
import java.util.Objects;

import br.com.developen.sig.database.AgencyModel;
import br.com.developen.sig.database.CityModel;
import br.com.developen.sig.database.DateConverter;
import br.com.developen.sig.database.StateModel;


public class ModifiedAddressEdificationSubjectModel {


    private Integer modifiedAddress;

    private String edification;

    private Integer sequence;

    private Integer subject;

    private String nameOrDenomination;

    private String type;

    private String fancyName;

    private String motherName;

    private String fatherName;

    private Long cpf;

    private Long rgNumber;

    @Embedded(prefix = "rgAgency_")
    private AgencyModel rgAgency;

    @Embedded(prefix = "rgState_")
    private StateModel rgState;

    @Embedded(prefix = "birthPlace_")
    private CityModel birthPlace;

    @TypeConverters({DateConverter.class})
    @ColumnInfo(name="birthDate")
    private Date birthDate;

    private String gender;


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


    public Integer getSequence() {

        return sequence;

    }


    public void setSequence(Integer sequence) {

        this.sequence = sequence;

    }


    public Integer getSubject() {

        return subject;

    }


    public void setSubject(Integer subject) {

        this.subject = subject;

    }


    public String getNameOrDenomination() {

        return nameOrDenomination;

    }


    public void setNameOrDenomination(String nameOrDenomination) {

        this.nameOrDenomination = nameOrDenomination;

    }


    public String getType() {

        return type;

    }


    public void setType(String type) {

        this.type = type;

    }


    public String getFancyName() {

        return fancyName;

    }


    public void setFancyName(String fancyName) {

        this.fancyName = fancyName;

    }


    public String getMotherName() {

        return motherName;

    }


    public void setMotherName(String motherName) {

        this.motherName = motherName;

    }


    public String getFatherName() {

        return fatherName;

    }


    public void setFatherName(String fatherName) {

        this.fatherName = fatherName;

    }


    public Long getCpf() {

        return cpf;

    }


    public void setCpf(Long cpf) {

        this.cpf = cpf;

    }


    public Long getRgNumber() {

        return rgNumber;

    }


    public void setRgNumber(Long rgNumber) {

        this.rgNumber = rgNumber;

    }


    public AgencyModel getRgAgency() {

        return rgAgency;

    }


    public void setRgAgency(AgencyModel rgAgency) {

        this.rgAgency = rgAgency;

    }


    public StateModel getRgState() {

        return rgState;

    }


    public void setRgState(StateModel rgState) {

        this.rgState = rgState;

    }


    public CityModel getBirthPlace() {

        return birthPlace;

    }


    public void setBirthPlace(CityModel birthPlace) {

        this.birthPlace = birthPlace;

    }


    public Date getBirthDate() {

        return birthDate;

    }


    public void setBirthDate(Date birthDate) {

        this.birthDate = birthDate;

    }


    public String getGender() {

        return gender;

    }


    public void setGender(String gender) {

        this.gender = gender;

    }


    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModifiedAddressEdificationSubjectModel that = (ModifiedAddressEdificationSubjectModel) o;
        return Objects.equals(modifiedAddress, that.modifiedAddress) &&
                Objects.equals(edification, that.edification) &&
                Objects.equals(sequence, that.sequence);

    }


    public int hashCode() {

        return Objects.hash(modifiedAddress, edification, sequence);

    }


}