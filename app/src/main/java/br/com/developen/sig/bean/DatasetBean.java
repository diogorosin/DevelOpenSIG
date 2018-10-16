package br.com.developen.sig.bean;

import java.util.ArrayList;
import java.util.List;

public class DatasetBean {

	private List<AgencyBean> agencies;
	
	private List<IndividualBean> individuals;
	
	private List<OrganizationBean> organizations;
	
	private List<CountryBean> countries;
	
	private List<StateBean> states;
	
	private List<CityBean> cities;
	
	private List<AddressBean> addresses;

	private List<AddressEdificationBean> addressesEdifications;

	private List<AddressEdificationSubjectBean> addressesEdificationsSubjects;

	public List<AgencyBean> getAgencies() {

		if (agencies == null)

			agencies = new ArrayList<AgencyBean>();

		return agencies;

	}

	public void setAgencies(List<AgencyBean> agencies) {

		this.agencies = agencies;

	}

	public List<IndividualBean> getIndividuals() {

		if (individuals == null)

			individuals = new ArrayList<IndividualBean>();

		return individuals;

	}

	public void setIndividuals(List<IndividualBean> individuals) {

		this.individuals = individuals;

	}

	public List<OrganizationBean> getOrganizations() {

		if (organizations == null)

			organizations = new ArrayList<OrganizationBean>();

		return organizations;

	}

	public void setOrganizations(List<OrganizationBean> organizations) {
		
		this.organizations = organizations;

	}

	public List<CountryBean> getCountries() {
		
		if (countries == null)
			
			countries = new ArrayList<CountryBean>();

		return countries;

	}

	public void setCountries(List<CountryBean> countries) {

		this.countries = countries;

	}

	public List<StateBean> getStates() {

		if (states == null)

			states = new ArrayList<StateBean>();

		return states;
		
	}

	public void setStates(List<StateBean> states) {

		this.states = states;

	}

	public List<CityBean> getCities() {

		if (cities == null)

			cities = new ArrayList<CityBean>();

		return cities;
		
	}

	public void setCities(List<CityBean> cities) {

		this.cities = cities;

	}

	public List<AddressBean> getAddresses() {

		if (addresses == null)

			addresses = new ArrayList<AddressBean>();

		return addresses;
		
	}

	public void setAddresses(List<AddressBean> addresses) {

		this.addresses = addresses;

	}

	public List<AddressEdificationBean> getAddressesEdifications() {

		if (addressesEdifications == null)

			addressesEdifications = new ArrayList<AddressEdificationBean>();

		return addressesEdifications;
		
	}

	public void setAddressesEdifications(List<AddressEdificationBean> addressesEdifications) {

		this.addressesEdifications = addressesEdifications;

	}

	public List<AddressEdificationSubjectBean> getAddressesEdificationsSubjects() {

		if (addressesEdificationsSubjects == null)

			addressesEdificationsSubjects = new ArrayList<AddressEdificationSubjectBean>();

		return addressesEdificationsSubjects;

	}

	public void setAddressesEdificationsSubjects(List<AddressEdificationSubjectBean> addressesEdificationsSubjects) {

		this.addressesEdificationsSubjects = addressesEdificationsSubjects;

	}

}