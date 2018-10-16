package br.com.developen.sig.bean;

import java.util.Date;

public class AddressEdificationSubjectBean {

	private Integer address;

	private String edification;

	private Integer subject;

	private Date from;

	private Date to;

	private Integer verifiedBy;

	private Date verifiedAt;

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

	public Integer getSubject() {

		return subject;

	}

	public void setSubject(Integer subject) {

		this.subject = subject;

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

	public Integer getVerifiedBy() {
		
		return verifiedBy;
		
	}

	public void setVerifiedBy(Integer verifiedBy) {
		
		this.verifiedBy = verifiedBy;

	}

	public Date getVerifiedAt() {

		return verifiedAt;

	}

	public void setVerifiedAt(Date verifiedAt) {

		this.verifiedAt = verifiedAt;

	}

	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((edification == null) ? 0 : edification.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
		
	}

	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressEdificationSubjectBean other = (AddressEdificationSubjectBean) obj;
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
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;

	}

}