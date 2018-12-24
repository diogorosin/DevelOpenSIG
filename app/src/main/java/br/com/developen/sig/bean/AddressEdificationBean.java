package br.com.developen.sig.bean;

public class AddressEdificationBean {

	private Integer address;

	private Integer edification;

	private Integer type;

	private String reference;

	public Integer getAddress() {

		return address;

	}

	public void setAddress(Integer address) {
		
		this.address = address;
		
	}

	public Integer getEdification() {

		return edification;

	}

	public void setEdification(Integer edification) {

		this.edification = edification;

	}

	public Integer getType() {

		return type;

	}

	public void setType(Integer type) {

		this.type = type;

	}

	public String getReference() {

		return reference;

	}

	public void setReference(String reference) {

		this.reference = reference;

	}

	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((edification == null) ? 0 : edification.hashCode());
		return result;
		
	}

	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressEdificationBean other = (AddressEdificationBean) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (edification == null) {
			if (other.edification != null)
				return false;
		} else if (!edification.equals(other.edification))
			return false;
		return true;

	}

}