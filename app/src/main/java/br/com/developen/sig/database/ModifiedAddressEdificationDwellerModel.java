package br.com.developen.sig.database;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;
import java.util.Objects;


public class ModifiedAddressEdificationDwellerModel {

    @Embedded(prefix = "modifiedAddressEdification_")
    private ModifiedAddressEdificationModel modifiedAddressEdification;

    private Integer dweller;

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
    private Date birthDate;

    private String gender;

    @TypeConverters({TimestampConverter.class})
    private Date from;

    @TypeConverters({TimestampConverter.class})
    private Date to;


    public ModifiedAddressEdificationModel getModifiedAddressEdification() {

        return modifiedAddressEdification;

    }


    public void setModifiedAddressEdification(ModifiedAddressEdificationModel modifiedAddressEdification) {

        this.modifiedAddressEdification = modifiedAddressEdification;

    }


    public Integer getDweller() {

        return dweller;

    }


    public void setDweller(Integer dweller) {

        this.dweller = dweller;

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


    public Date getFrom() {

        return from;

    }


    public void setFrom(Date from) {

        this.from = from;

    }


    public Date getTo() {

        return to;

    }


    public void setTo(Date to) {

        this.to = to;

    }


    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModifiedAddressEdificationDwellerModel that = (ModifiedAddressEdificationDwellerModel) o;
        return Objects.equals(modifiedAddressEdification, that.modifiedAddressEdification) &&
                Objects.equals(dweller, that.dweller);

    }


    public int hashCode() {

        return Objects.hash(modifiedAddressEdification, dweller);

    }


}