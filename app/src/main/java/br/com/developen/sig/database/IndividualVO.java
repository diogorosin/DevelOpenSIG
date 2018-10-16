package br.com.developen.sig.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "Individual",
        foreignKeys = {
                @ForeignKey(entity = AgencyVO.class,
                        parentColumns = "identifier",
                        childColumns = "rgAgency",
                        onDelete = ForeignKey.RESTRICT,
                        onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = StateVO.class,
                        parentColumns = "identifier",
                        childColumns = "rgState",
                        onDelete = ForeignKey.RESTRICT,
                        onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = CityVO.class,
                        parentColumns = "identifier",
                        childColumns = "birthPlace",
                        onDelete = ForeignKey.RESTRICT,
                        onUpdate = ForeignKey.CASCADE)},
        indices = {@Index("identifier")})
public class IndividualVO {

    @PrimaryKey
    @ColumnInfo(name="identifier")
    private Integer identifier;

    @NonNull
    @ColumnInfo(name="active")
    private Boolean active;

    @NonNull
    @ColumnInfo(name="name")
    private String name;

    @NonNull
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

    @NonNull
    @ColumnInfo(name="birthPlace")
    private Integer birthPlace;

    @NonNull
    @TypeConverters({DateConverter.class})
    @ColumnInfo(name="birthDate")
    private Date birthDate;

    @NonNull
    @ColumnInfo(name="gender")
    private String gender;

    public Integer getIdentifier() {

        return identifier;

    }

    public void setIdentifier(Integer identifier) {

        this.identifier = identifier;

    }

    public Boolean getActive() {

        return active;

    }

    public void setActive(Boolean active) {

        this.active = active;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

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
        IndividualVO that = (IndividualVO) o;
        return identifier.equals(that.identifier);

    }

    public int hashCode() {

        return identifier.hashCode();

    }

}