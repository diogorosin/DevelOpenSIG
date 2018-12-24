package br.com.developen.sig.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AddressEdificationDwellerDAO {

    @Insert
    void create(AddressEdificationDwellerVO addressEdificationDwellerVO);

    @Query("SELECT AED.* " +
            "FROM AddressEdificationDweller AED " +
            "WHERE AED.address = :address AND AED.edification = :edification AND AED.dweller = :dweller")
    AddressEdificationDwellerVO retrieve(int address, Integer edification, Integer dweller);

    @Query("SELECT COUNT(*) > 0 " +
            "FROM AddressEdificationDweller AED " +
            "WHERE AED.address = :address AND AED.edification = :edification AND AED.dweller = :dweller")
    Boolean exists(int address, Integer edification, Integer dweller);

    @Update
    void update(AddressEdificationDwellerVO addressEdificationDwellerVO);

    @Delete
    void delete(AddressEdificationDwellerVO addressEdificationDwellerVO);

    @Query("SELECT " +
            " A.identifier AS 'addressEdification_address_identifier', " +
            " A.denomination AS 'addressEdification_address_denomination', " +
            " A.number AS 'addressEdification_address_number', " +
            " A.reference AS 'addressEdification_address_reference', " +
            " A.district AS 'addressEdification_address_district', " +
            " A.postalCode AS 'addressEdification_address_postalCode', " +
            " A.latitude AS 'addressEdification_address_latitude', " +
            " A.longitude AS 'addressEdification_address_longitude', " +
            " AddressCity.identifier AS 'addressEdification_address_city_identifier', " +
            " AddressCity.denomination AS 'addressEdification_address_city_denomination', " +
            " AddressCityState.identifier AS 'addressEdification_address_city_state_identifier', " +
            " AddressCityState.denomination AS 'addressEdification_address_city_state_denomination', " +
            " AddressCityState.acronym AS 'addressEdification_address_city_state_acronym', " +
            " AddressCityStateCountry.identifier AS 'addressEdification_address_city_state_country_identifier', " +
            " AddressCityStateCountry.denomination AS 'addressEdification_address_city_state_country_denomination', " +
            " AddressCityStateCountry.acronym AS 'addressEdification_address_city_state_country_acronym', " +
            " AE.edification AS 'addressEdification_edification', " +
            " AE.type AS 'addressEdification_type', " +
            " AE.reference AS 'addressEdification_reference', " +
            " AED.dweller AS 'dweller', " +
            " SV.identifier AS 'subject_identifier', " +
            " SV.nameOrDenomination AS 'subject_nameOrDenomination', " +
            " SV.type AS 'subject_type' " +
            "FROM AddressEdificationDweller AED " +
            "INNER JOIN AddressEdification AE ON AE.address = AED.address AND AE.edification = AED.edification " +
            "INNER JOIN Address A ON A.identifier = AE.address " +
            "INNER JOIN City AddressCity ON AddressCity.identifier = A.city " +
            "INNER JOIN State AddressCityState ON AddressCityState.identifier = AddressCity.state " +
            "INNER JOIN Country AddressCityStateCountry ON AddressCityStateCountry.identifier = AddressCityState.country " +
            "INNER JOIN SubjectView SV ON SV.identifier = AED.subject " +
            "WHERE SV.nameOrDenomination LIKE :nameOrDenomination AND AED.'to' IS NULL")
    List<AddressEdificationDwellerModel> findBySubjectNameOrDenomination(String nameOrDenomination);

}