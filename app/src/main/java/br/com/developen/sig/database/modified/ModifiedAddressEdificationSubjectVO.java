package br.com.developen.sig.database.modified;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

import br.com.developen.sig.database.DateConverter;

@Entity(tableName = "ModifiedAddressEdificationSubject",
        primaryKeys = {"modifiedAddress", "edification", "sequence"},
        foreignKeys = {
                @ForeignKey(entity = ModifiedAddressEdificationVO.class,
                        parentColumns = {"modifiedAddress", "edification"},
                        childColumns = {"modifiedAddress", "edification"},
                        onDelete = ForeignKey.RESTRICT,
                        onUpdate = ForeignKey.CASCADE)},
        indices = {@Index(value={"modifiedAddress", "edification", "sequence"})})
public class ModifiedAddressEdificationSubjectVO {

    @NonNull
    @ColumnInfo(name="modifiedAddress")
    private Integer modifiedAddress;

    @NonNull
    @ColumnInfo(name="edification")
    private String edification;

    @NonNull
    @ColumnInfo(name="sequence")
    private Integer sequence;

    @ColumnInfo(name="subject")
    private Integer subject;

    @NonNull
    @ColumnInfo(name="nameOrDenomination")
    private String nameOrDenomination;

    @NonNull
    @ColumnInfo(name="type")
    private String type;

    @ColumnInfo(name="fancyName")
    private String fancyName;

    @ColumnInfo(name="motherName")
    private String motherName;

    @ColumnInfo(name="fatherName")
    private String fatherName;

    @ColumnInfo(name="cpf")
    private Long cpf;

    @ColumnInfo(name="rgNumber")
    private Long rgNumber;

    @ColumnInfo(name="rgAgency")
    private Integer rgAgency;

    @ColumnInfo(name="rgState")
    private Integer rgState;

    @ColumnInfo(name="birthPlace")
    private Integer birthPlace;

    @TypeConverters({DateConverter.class})
    @ColumnInfo(name="birthDate")
    private Date birthDate;

    @ColumnInfo(name="gender")
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


    public Integer getRgAgency() {

        return rgAgency;

    }


    public void setRgAgency(Integer rgAgency) {

        this.rgAgency = rgAgency;

    }


    public Integer getRgState() {

        return rgState;

    }


    public void setRgState(Integer rgState) {

        this.rgState = rgState;

    }


    public Integer getBirthPlace() {

        return birthPlace;

    }


    public void setBirthPlace(Integer birthPlace) {

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
        ModifiedAddressEdificationSubjectVO that = (ModifiedAddressEdificationSubjectVO) o;
        return Objects.equals(modifiedAddress, that.modifiedAddress) &&
                Objects.equals(edification, that.edification) &&
                Objects.equals(sequence, that.sequence);

    }


    public int hashCode() {

        return Objects.hash(modifiedAddress, edification, sequence);

    }


}