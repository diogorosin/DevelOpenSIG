package br.com.developen.sig.database.modified;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;
import java.util.Objects;

import br.com.developen.sig.database.CityVO;
import br.com.developen.sig.database.DateConverter;

@Entity(tableName = "ModifiedAddress",
        foreignKeys = {
                @ForeignKey(entity = CityVO.class,
                        parentColumns = "identifier",
                        childColumns = "city",
                        onDelete = ForeignKey.RESTRICT,
                        onUpdate = ForeignKey.CASCADE)},
        indices = {@Index("identifier")})
public class ModifiedAddressVO {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="identifier")
    private Integer identifier;

    @TypeConverters({DateConverter.class})
    @ColumnInfo(name="syncedAt")
    private Date syncedAt;

    @TypeConverters({DateConverter.class})
    @ColumnInfo(name="modifiedAt")
    private Date modifiedAt;

    @ColumnInfo(name="modifiedBy")
    private Integer modifiedBy;

    @ColumnInfo(name="address")
    private Integer address;

    @ColumnInfo(name="denomination")
    private String denomination;

    @ColumnInfo(name="number")
    private String number;

    @ColumnInfo(name="reference")
    private String reference;

    @ColumnInfo(name="district")
    private String district;

    @ColumnInfo(name="postalCode")
    private Integer postalCode;

    @ColumnInfo(name="city")
    private Integer city;

    @ColumnInfo(name="latitude")
    private Double latitude;

    @ColumnInfo(name="longitude")
    private Double longitude;


    public Integer getIdentifier() {

        return identifier;

    }

    public void setIdentifier(Integer identifier) {

        this.identifier = identifier;

    }

    public Date getSyncedAt() {

        return syncedAt;

    }

    public void setSyncedAt(Date syncedAt) {

        this.syncedAt = syncedAt;

    }

    public Date getModifiedAt() {

        return modifiedAt;

    }

    public void setModifiedAt(Date modifiedAt) {

        this.modifiedAt = modifiedAt;

    }

    public Integer getModifiedBy() {

        return modifiedBy;

    }

    public void setModifiedBy(Integer modifiedBy) {

        this.modifiedBy = modifiedBy;

    }

    public Integer getAddress() {

        return address;

    }

    public void setAddress(Integer address) {

        this.address = address;

    }

    public String getDenomination() {

        return denomination;

    }

    public void setDenomination(String denomination) {

        this.denomination = denomination;

    }

    public String getNumber() {

        return number;

    }

    public void setNumber(String number) {

        this.number = number;

    }

    public String getReference() {

        return reference;

    }

    public void setReference(String reference) {

        this.reference = reference;

    }

    public String getDistrict() {

        return district;

    }

    public void setDistrict(String district) {

        this.district = district;

    }

    public Integer getPostalCode() {

        return postalCode;

    }

    public void setPostalCode(Integer postalCode) {

        this.postalCode = postalCode;

    }

    public Integer getCity() {

        return city;

    }

    public void setCity(Integer city) {

        this.city = city;

    }

    public Double getLatitude() {

        return latitude;

    }

    public void setLatitude(Double latitude) {

        this.latitude = latitude;

    }

    public Double getLongitude() {

        return longitude;

    }

    public void setLongitude(Double longitude) {

        this.longitude = longitude;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModifiedAddressVO that = (ModifiedAddressVO) o;
        return Objects.equals(identifier, that.identifier);

    }

    public int hashCode() {

        return Objects.hash(identifier);

    }

}