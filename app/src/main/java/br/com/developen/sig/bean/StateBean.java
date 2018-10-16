package br.com.developen.sig.bean;

public class StateBean {

	private Integer identifier;

	private String denomination;
	
	private String acronym;
	
	private Integer country;

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

	public String getAcronym() {

		return acronym;

	}

	public void setAcronym(String acronym) {

		this.acronym = acronym;

	}

	public Integer getCountry() {

		return country;

	}

	public void setCountry(Integer country) {

		this.country = country;

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
		StateBean other = (StateBean) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}