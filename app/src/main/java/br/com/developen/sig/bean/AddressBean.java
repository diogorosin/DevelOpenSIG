package br.com.developen.sig.bean;

import java.io.Serializable;

public class AddressBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer identifier;

	private String denomination;

	private String number;

	private String reference;

	private String district;

	private Integer postalCode;

	private Integer city;
	
	private Double latitude;

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

	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;
		
	}

	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressBean other = (AddressBean) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}