package br.com.developen.sig.widget;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class AddressClusterItem implements ClusterItem {

    private Integer identifier;

    private String denomination;

    private String number;

    private String reference;

    private String district;

    private Integer postalCode;

    private String city;

    private LatLng position;

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

    public String getCity() {

        return city;

    }

    public void setCity(String city) {

        this.city = city;

    }

    public void setPosition(LatLng position) {

        this.position = position;

    }

    public LatLng getPosition() {

        return position;

    }

    public String getTitle() {return null;}

    public String getSnippet() {return null;}

}