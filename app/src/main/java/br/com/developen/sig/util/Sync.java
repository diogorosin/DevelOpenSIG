package br.com.developen.sig.util;

import android.util.Log;

import br.com.developen.sig.bean.AddressBean;
import br.com.developen.sig.bean.AddressEdificationBean;
import br.com.developen.sig.bean.AddressEdificationSubjectBean;
import br.com.developen.sig.bean.AgencyBean;
import br.com.developen.sig.bean.CityBean;
import br.com.developen.sig.bean.CountryBean;
import br.com.developen.sig.bean.DatasetBean;
import br.com.developen.sig.bean.IndividualBean;
import br.com.developen.sig.bean.OrganizationBean;
import br.com.developen.sig.bean.StateBean;
import br.com.developen.sig.database.AddressDAO;
import br.com.developen.sig.database.AddressEdificationDAO;
import br.com.developen.sig.database.AddressEdificationSubjectDAO;
import br.com.developen.sig.database.AddressEdificationSubjectVO;
import br.com.developen.sig.database.AddressEdificationVO;
import br.com.developen.sig.database.AddressVO;
import br.com.developen.sig.database.AgencyDAO;
import br.com.developen.sig.database.AgencyVO;
import br.com.developen.sig.database.CityDAO;
import br.com.developen.sig.database.CityVO;
import br.com.developen.sig.database.CountryDAO;
import br.com.developen.sig.database.CountryVO;
import br.com.developen.sig.database.IndividualDAO;
import br.com.developen.sig.database.IndividualVO;
import br.com.developen.sig.database.OrganizationDAO;
import br.com.developen.sig.database.OrganizationVO;
import br.com.developen.sig.database.StateDAO;
import br.com.developen.sig.database.StateVO;
import br.com.developen.sig.database.SubjectDAO;
import br.com.developen.sig.database.SubjectVO;

public class Sync {

    private DB database;

    public Sync(DB database) {

        this.database = database;

    }

    public boolean dataset(DatasetBean datasetBean) {

        boolean success;

        try {

            database.beginTransaction();

            if (datasetBean.getAgencies() != null &&
                    !datasetBean.getAgencies().isEmpty()){

                AgencyDAO agencyDAO = database.agencyDAO();

                for (AgencyBean agencyBean: datasetBean.getAgencies()) {

                    AgencyVO agencyVO = new AgencyVO();

                    agencyVO.setIdentifier(agencyBean.getIdentifier());

                    agencyVO.setDenomination(agencyBean.getDenomination());

                    agencyVO.setAcronym(agencyBean.getAcronym());

                    if (agencyDAO.exists(agencyVO.getIdentifier()))

                        agencyDAO.update(agencyVO);

                    else

                        agencyDAO.create(agencyVO);

                }

            }

            if (datasetBean.getCountries() != null &&
                    !datasetBean.getCountries().isEmpty()){

                CountryDAO countryDAO = database.countryDAO();

                for (CountryBean countryBean: datasetBean.getCountries()) {

                    CountryVO countryVO = new CountryVO();

                    countryVO.setIdentifier(countryBean.getIdentifier());

                    countryVO.setDenomination(countryBean.getDenomination());

                    countryVO.setAcronym(countryBean.getAcronym());

                    if (countryDAO.exists(countryVO.getIdentifier()))

                        countryDAO.update(countryVO);

                    else

                        countryDAO.create(countryVO);

                }

            }

            if (datasetBean.getStates() != null &&
                    !datasetBean.getStates().isEmpty()){

                StateDAO stateDAO = database.stateDAO();

                for (StateBean stateBean: datasetBean.getStates()) {

                    StateVO stateVO = new StateVO();

                    stateVO.setIdentifier(stateBean.getIdentifier());

                    stateVO.setDenomination(stateBean.getDenomination());

                    stateVO.setAcronym(stateBean.getAcronym());

                    stateVO.setCountry(stateBean.getCountry());

                    if (stateDAO.exists(stateVO.getIdentifier()))

                        stateDAO.update(stateVO);

                    else

                        stateDAO.create(stateVO);

                }

            }

            if (datasetBean.getCities() != null &&
                    !datasetBean.getCities().isEmpty()){

                CityDAO cityDAO = database.cityDAO();

                for (CityBean cityBean: datasetBean.getCities()) {

                    CityVO cityVO = new CityVO();

                    cityVO.setIdentifier(cityBean.getIdentifier());

                    cityVO.setDenomination(cityBean.getDenomination());

                    cityVO.setState(cityBean.getState());

                    if (cityDAO.exists(cityVO.getIdentifier()))

                        cityDAO.update(cityVO);

                    else

                        cityDAO.create(cityVO);

                }

            }

            if (datasetBean.getIndividuals() != null &&
                    !datasetBean.getIndividuals().isEmpty()){

                SubjectDAO subjectDAO = database.subjectDAO();

                IndividualDAO individualDAO = database.individualDAO();

                for (IndividualBean individualBean: datasetBean.getIndividuals()) {

                    SubjectVO subjectVO = new SubjectVO();

                    subjectVO.setIdentifier(individualBean.getIdentifier());

                    if (subjectDAO.exists(subjectVO.getIdentifier()))

                        subjectDAO.update(subjectVO);

                    else

                        subjectDAO.create(subjectVO);

                    IndividualVO individualVO = new IndividualVO();

                    individualVO.setIdentifier(individualBean.getIdentifier());

                    individualVO.setActive(individualBean.getActive());

                    individualVO.setName(individualBean.getName());

                    individualVO.setMotherName(individualBean.getMotherName());

                    individualVO.setFatherName(individualBean.getFatherName());

                    individualVO.setCpf(individualBean.getCpf());

                    individualVO.setRgNumber(individualBean.getRgNumber());

                    individualVO.setRgAgency(individualBean.getRgAgency());

                    individualVO.setRgState(individualBean.getRgState());

                    individualVO.setBirthDate(individualBean.getBirthDate());

                    individualVO.setBirthPlace(individualBean.getBirthPlace());

                    individualVO.setGender(individualBean.getGender());

                    if (individualDAO.exists(individualVO.getIdentifier()))

                        individualDAO.update(individualVO);

                    else

                        individualDAO.create(individualVO);

                }

            }

            if (datasetBean.getOrganizations() != null &&
                    !datasetBean.getOrganizations().isEmpty()){

                SubjectDAO subjectDAO = database.subjectDAO();

                OrganizationDAO organizationDAO = database.organizationDAO();

                for (OrganizationBean organizationBean: datasetBean.getOrganizations()) {

                    SubjectVO subjectVO = new SubjectVO();

                    subjectVO.setIdentifier(organizationBean.getIdentifier());

                    if (subjectDAO.exists(subjectVO.getIdentifier()))

                        subjectDAO.update(subjectVO);

                    else

                        subjectDAO.create(subjectVO);

                    OrganizationVO organizationVO = new OrganizationVO();

                    organizationVO.setIdentifier(organizationBean.getIdentifier());

                    organizationVO.setActive(organizationBean.getActive());

                    organizationVO.setDenomination(organizationBean.getDenomination());

                    organizationVO.setFancyName(organizationBean.getFancyName());

                    if (organizationDAO.exists(organizationVO.getIdentifier()))

                        organizationDAO.update(organizationVO);

                    else

                        organizationDAO.create(organizationVO);

                }

            }

            if (datasetBean.getAddresses() != null &&
                    !datasetBean.getAddresses().isEmpty()){

                AddressDAO addressDAO = database.addressDAO();

                for (AddressBean addressBean: datasetBean.getAddresses()) {

                    AddressVO addressVO = new AddressVO();

                    addressVO.setIdentifier(addressBean.getIdentifier());

                    addressVO.setDenomination(addressBean.getDenomination());

                    addressVO.setNumber(addressBean.getNumber());

                    addressVO.setReference(addressBean.getReference());

                    addressVO.setDistrict(addressBean.getDistrict());

                    addressVO.setPostalCode(addressBean.getPostalCode());

                    addressVO.setCity(addressBean.getCity());

                    addressVO.setLatitude(addressBean.getLatitude());

                    addressVO.setLongitude(addressBean.getLongitude());

                    if (addressDAO.exists(addressVO.getIdentifier()))

                        addressDAO.update(addressVO);

                    else

                        addressDAO.create(addressVO);

                }

            }

            if (datasetBean.getAddressesEdifications() != null &&
                    !datasetBean.getAddressesEdifications().isEmpty()){

                AddressEdificationDAO addressEdificationDAO = database.addressEdificationDAO();

                for (AddressEdificationBean addressEdificationBean: datasetBean.getAddressesEdifications()) {

                    AddressEdificationVO addressEdificationVO = new AddressEdificationVO();

                    addressEdificationVO.setAddress(addressEdificationBean.getAddress());

                    addressEdificationVO.setEdification(addressEdificationBean.getEdification());

                    if (addressEdificationDAO.exists(
                            addressEdificationVO.getAddress(),
                            addressEdificationVO.getEdification()))

                        addressEdificationDAO.update(addressEdificationVO);

                    else

                        addressEdificationDAO.create(addressEdificationVO);

                }

            }

            if (datasetBean.getAddressesEdificationsSubjects() != null &&
                    !datasetBean.getAddressesEdificationsSubjects().isEmpty()){

                AddressEdificationSubjectDAO addressEdificationSubjectDAO = database.addressEdificationSubjectDAO();

                for (AddressEdificationSubjectBean addressEdificationSubjectBean: datasetBean.getAddressesEdificationsSubjects()) {

                    AddressEdificationSubjectVO addressEdificationSubjectVO = new AddressEdificationSubjectVO();

                    addressEdificationSubjectVO.setAddress(addressEdificationSubjectBean.getAddress());

                    addressEdificationSubjectVO.setEdification(addressEdificationSubjectBean.getEdification());

                    addressEdificationSubjectVO.setSubject(addressEdificationSubjectBean.getSubject());

                    addressEdificationSubjectVO.setFrom(addressEdificationSubjectBean.getFrom());

                    addressEdificationSubjectVO.setTo(addressEdificationSubjectBean.getTo());

                    addressEdificationSubjectVO.setVerifiedAt(addressEdificationSubjectBean.getVerifiedAt());

                    addressEdificationSubjectVO.setVerifiedBy(addressEdificationSubjectBean.getVerifiedBy());

                    if (addressEdificationSubjectDAO.exists(
                            addressEdificationSubjectVO.getAddress(),
                            addressEdificationSubjectVO.getEdification(),
                            addressEdificationSubjectVO.getSubject()))

                        addressEdificationSubjectDAO.update(addressEdificationSubjectVO);

                    else

                        addressEdificationSubjectDAO.create(addressEdificationSubjectVO);

                }

            }

            database.setTransactionSuccessful();

            success = true;

        } catch (Exception e){

            e.printStackTrace();

            Log.d("passou", e.getMessage());

            success = false;

        } finally {

            if (database.inTransaction())

                database.endTransaction();

        }

        return success;

    }

}