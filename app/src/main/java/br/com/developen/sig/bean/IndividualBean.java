package br.com.developen.sig.bean;


import java.util.Date;

public class IndividualBean extends SubjectBean{

    private String name;

    private String motherName;

    private String fatherName;

    private Long cpf;

    private Long rgNumber;

    private Integer rgAgency;

    private Integer rgState;

    private Integer birthPlace;

    private Date birthDate;

    private String gender;

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

}