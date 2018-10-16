package br.com.developen.sig.bean;

public class AddressEdificationBean {

	private Integer address;

	private String edification;

	public Integer getAddress() {

		return address;

	}

	public void setAddress(Integer address) {
		
		this.address = address;
		
	}

	public String getEdification() {
		
		return edification;
		
	}

	public void setEdification(String edification) {
		
		this.edification = edification;
		
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