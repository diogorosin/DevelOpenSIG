package br.com.developen.sig.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Address",
        foreignKeys = {
                @ForeignKey(entity = CityVO.class,
                        parentColumns = "identifier",
                        childColumns = "city",
                        onDelete = ForeignKey.RESTRICT,
                        onUpdate = ForeignKey.CASCADE)},
        indices = {@Index("identifier"), @Index("city")})
public class AddressVO {

    @PrimaryKey
    @ColumnInfo(name="identifier")
    private Integer identifier;

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

    @NonNull
    @ColumnInfo(name="city")
    private Integer city;

    @NonNull
    @ColumnInfo(name="latitude")
    private Double latitude;

    @NonNull
    @ColumnInfo(name="longitude")
    private Double longitude;

    public Integer getIdentifier() {

        return identifier;

    }

    public void setIdentifier(Integer identifier) {

        this.identifier = identifier;

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

    @NonNull
    public Integer getCity() {

        return city;

    }

    public void setCity(@NonNull Integer city) {

        this.city = city;

    }

    @NonNull
    public Double getLatitude() {

        return latitude;

    }

    public void setLatitude(@NonNull Double latitude) {

        this.latitude = latitude;

    }

    @NonNull
    public Double getLongitude() {

        return longitude;

    }

    public void setLongitude(@NonNull Double longitude) {

        this.longitude = longitude;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressVO addressVO = (AddressVO) o;
        return identifier.equals(addressVO.identifier);

    }

    public int hashCode() {

        return identifier.hashCode();

    }

}