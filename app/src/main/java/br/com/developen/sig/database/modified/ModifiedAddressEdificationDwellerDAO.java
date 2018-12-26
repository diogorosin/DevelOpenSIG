package br.com.developen.sig.database.modified;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ModifiedAddressEdificationDwellerDAO {

    @Insert
    Long create(ModifiedAddressEdificationDwellerVO modifiedlAddressEdificationDwellerVO);

    @Query("SELECT MAED.* " +
            "FROM ModifiedAddressEdificationDweller MAED " +
            "WHERE MAED.modifiedAddress = :modifiedAddress AND MAED.edification = :edification AND MAED.dweller = :dweller")
    ModifiedAddressEdificationDwellerVO retrieve(Integer modifiedAddress, Integer edification, Integer dweller);

    @Query("SELECT COUNT(*) > 0 " +
            "FROM ModifiedAddressEdificationDweller MAED " +
            "WHERE MAED.modifiedAddress = :modifiedAddress AND MAED.edification = :edification AND MAED.dweller = :dweller")
    Boolean exists(Integer modifiedAddress, Integer edification, Integer dweller);

    @Query("SELECT COUNT(*) FROM ModifiedAddressEdificationDweller MAED")
    Integer count();

    @Update
    void update(ModifiedAddressEdificationDwellerVO modifiedAddressEdificationDwellerVO);

    @Delete
    void delete(ModifiedAddressEdificationDwellerVO modifiedAddressEdificationDwellerVO);

    @Query("SELECT " +
            " MA.identifier AS 'modifiedAddressEdification_modifiedAddress_identifier', " +
            " MA.syncedAt AS 'modifiedAddressEdification_modifiedAddress_syncedAt', " +
            " MA.modifiedAt AS 'modifiedAddressEdification_modifiedAddress_modifiedAt', " +
            " MA.modifiedBy AS 'modifiedAddressEdification_modifiedAddress_modifiedBy', " +
            " MA.address AS 'modifiedAddressEdification_modifiedAddress_address', " +
            " MA.denomination AS 'modifiedAddressEdification_modifiedAddress_denomination', " +
            " MA.number AS 'modifiedAddressEdification_modifiedAddress_number', " +
            " MA.reference AS 'modifiedAddressEdification_modifiedAddress_reference', " +
            " MA.district AS 'modifiedAddressEdification_modifiedAddress_district', " +
            " MA.postalCode AS 'modifiedAddressEdification_modifiedAddress_postalCode', " +
            " MA.latitude AS 'modifiedAddressEdification_modifiedAddress_latitude', " +
            " MA.longitude AS 'modifiedAddressEdification_modifiedAddress_longitude', " +
            " ModifiedAddressCity.identifier AS 'modifiedAddressEdification_modifiedAddress_city_identifier', " +
            " ModifiedAddressCity.denomination AS 'modifiedAddressEdification_modifiedAddress_city_denomination', " +
            " ModifiedAddressCityState.identifier AS 'modifiedAddressEdification_modifiedAddress_city_state_identifier', " +
            " ModifiedAddressCityState.denomination AS 'modifiedAddressEdification_modifiedAddress_city_state_denomination', " +
            " ModifiedAddressCityState.acronym AS 'modifiedAddressEdification_modifiedAddress_city_state_acronym', " +
            " ModifiedAddressCityStateCountry.identifier AS 'modifiedAddressEdification_modifiedAddress_city_state_country_identifier', " +
            " ModifiedAddressCityStateCountry.denomination AS 'modifiedAddressEdification_modifiedAddress_city_state_country_denomination', " +
            " ModifiedAddressCityStateCountry.acronym AS 'modifiedAddressEdification_modifiedAddress_city_state_country_acronym', " +
            " MAE.edification AS 'modifiedAddressEdification_edification', " +
            " MAE.type AS 'modifiedAddressEdification_type', " +
            " MAE.reference AS 'modifiedAddressEdification_reference', " +
            " MAED.dweller AS 'dweller', " +
            " MAED.subject AS 'subject', " +
            " MAED.nameOrDenomination AS 'nameOrDenomination', " +
            " MAED.type AS 'type', " +
            " MAED.fancyName AS 'fancyName', " +
            " MAED.motherName AS 'motherName', " +
            " MAED.fatherName AS 'fatherName', " +
            " MAED.cpf AS 'cpf', " +
            " MAED.rgNumber AS 'rgNumber', " +
            " RgAgency.identifier AS 'rgAgency_identifier', " +
            " RgAgency.denomination AS 'rgAgency_denomination', " +
            " RgAgency.acronym AS 'rgAgency_acronym', " +
            " RgState.identifier AS 'rgState_identifier', " +
            " RgState.denomination AS 'rgState_denomination', " +
            " RgState.acronym AS 'rgState_acronym', " +
            " RgStateCountry.identifier AS 'rgState_country_identifier', " +
            " RgStateCountry.denomination AS 'rgState_country_denomination', " +
            " RgStateCountry.acronym AS 'rgState_country_acronym', " +
            " BirthPlace.identifier AS 'birthPlace_identifier', " +
            " BirthPlace.denomination AS 'birthPlace_denomination', " +
            " BirthPlaceState.identifier AS 'birthPlace_state_identifier', " +
            " BirthPlaceState.denomination AS 'birthPlace_state_denomination', " +
            " BirthPlaceState.acronym AS 'birthPlace_state_acronym', " +
            " BirthPlaceStateCountry.identifier AS 'birthPlace_state__country_identifier', " +
            " BirthPlaceStateCountry.denomination AS 'birthPlace_state_country_denomination', " +
            " BirthPlaceStateCountry.acronym AS 'birthPlace_state_country_acronym', " +
            " MAED.birthDate AS 'birthDate', " +
            " MAED.gender AS 'gender' " +
            "FROM ModifiedAddressEdificationDweller MAED " +
            "INNER JOIN ModifiedAddressEdification MAE ON MAE.modifiedAddress = MAED.modifiedAddress AND MAE.edification = MAED.edification " +
            "INNER JOIN ModifiedAddress MA ON MA.identifier = MAE.modifiedAddress " +
            "LEFT OUTER JOIN City ModifiedAddressCity ON ModifiedAddressCity.identifier = MA.city " +
            "LEFT OUTER JOIN State ModifiedAddressCityState ON ModifiedAddressCityState.identifier = ModifiedAddressCity.state " +
            "LEFT OUTER JOIN Country ModifiedAddressCityStateCountry ON ModifiedAddressCityStateCountry.identifier = ModifiedAddressCityState.country " +
            "LEFT OUTER JOIN Agency RgAgency ON RgAgency.identifier = MAED.rgAgency " +
            "LEFT OUTER JOIN State RgState ON RgState.identifier = MAED.rgState " +
            "LEFT OUTER JOIN Country RgStateCountry ON RgStateCountry.identifier = RgState.country " +
            "LEFT OUTER JOIN City BirthPlace ON BirthPlace.identifier = MAED.birthPlace " +
            "LEFT OUTER JOIN State BirthPlaceState ON BirthPlaceState.identifier = BirthPlace.state " +
            "LEFT OUTER JOIN Country BirthPlaceStateCountry ON BirthPlaceStateCountry.identifier = BirthPlaceState.country " +
            "WHERE MAE.modifiedAddress = :modifiedAddress AND MAE.edification = :edification")
    LiveData<List<ModifiedAddressEdificationDwellerModel>> getDwellersOfModifiedAddressEdification(Integer modifiedAddress, Integer edification);

}