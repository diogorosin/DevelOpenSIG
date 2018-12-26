package br.com.developen.sig.database.modified;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ModifiedAddressEdificationDAO {

    @Insert
    Long create(ModifiedAddressEdificationVO modifiedAddressEdificationVO);

    @Query("SELECT MAE.* FROM ModifiedAddressEdification MAE WHERE MAE.modifiedAddress = :modifiedAddress AND MAE.edification = :edification")
    ModifiedAddressEdificationVO retrieve(Integer modifiedAddress, Integer edification);

    @Query("SELECT COUNT(*) > 0 FROM ModifiedAddressEdification MAE WHERE MAE.modifiedAddress = :modifiedAddress AND MAE.edification = :edification")
    Boolean exists(Integer modifiedAddress, Integer edification);

    @Query("SELECT COUNT(*) FROM ModifiedAddressEdification MAE")
    Integer count();

    @Update
    void update(ModifiedAddressEdificationVO modifiedAddressEdificationVO);

    @Delete
    void delete(ModifiedAddressEdificationVO modifiedAddressEdificationVO);

    @Query("SELECT " +
            " MA.identifier AS 'modifiedAddress_identifier', " +
            " MA.syncedAt AS 'modifiedAddress_syncedAt', " +
            " MA.modifiedAt AS 'modifiedAddress_modifiedAt', " +
            " MA.modifiedBy AS 'modifiedAddress_modifiedBy', " +
            " MA.address AS 'modifiedAddress_address', " +
            " MA.denomination AS 'modifiedAddress_denomination', " +
            " MA.number AS 'modifiedAddress_number', " +
            " MA.reference AS 'modifiedAddress_reference', " +
            " MA.district AS 'modifiedAddress_district', " +
            " MA.postalCode AS 'modifiedAddress_postalCode', " +
            " MA.latitude AS 'modifiedAddress_latitude', " +
            " MA.longitude AS 'modifiedAddress_longitude', " +
            " ModifiedAddressCity.identifier AS 'modifiedAddress_city_identifier', " +
            " ModifiedAddressCity.denomination AS 'modifiedAddress_city_denomination', " +
            " ModifiedAddressCityState.identifier AS 'modifiedAddress_city_state_identifier', " +
            " ModifiedAddressCityState.denomination AS 'modifiedAddress_city_state_denomination', " +
            " ModifiedAddressCityState.acronym AS 'modifiedAddress_city_state_acronym', " +
            " ModifiedAddressCityStateCountry.identifier AS 'modifiedAddress_city_state_country_identifier', " +
            " ModifiedAddressCityStateCountry.denomination AS 'modifiedAddress_city_state_country_denomination', " +
            " ModifiedAddressCityStateCountry.acronym AS 'modifiedAddress_city_state_country_acronym', " +
            " MAE.edification AS 'edification', " +
            " MAE.type AS 'type', " +
            " MAE.reference AS 'reference' " +
            "FROM ModifiedAddressEdification MAE " +
            "INNER JOIN ModifiedAddress MA ON MA.identifier = MAE.modifiedAddress " +
            "LEFT OUTER JOIN City ModifiedAddressCity ON ModifiedAddressCity.identifier = MA.city " +
            "LEFT OUTER JOIN State ModifiedAddressCityState ON ModifiedAddressCityState.identifier = ModifiedAddressCity.state " +
            "LEFT OUTER JOIN Country ModifiedAddressCityStateCountry ON ModifiedAddressCityStateCountry.identifier = ModifiedAddressCityState.country " +
            "WHERE MA.identifier = :modifiedAddress")
    LiveData<List<ModifiedAddressEdificationModel>> getEdificationsOfModifiedAddress(Integer modifiedAddress);


    @Query("SELECT " +
            " MA.identifier AS 'modifiedAddress_identifier', " +
            " MA.syncedAt AS 'modifiedAddress_syncedAt', " +
            " MA.modifiedAt AS 'modifiedAddress_modifiedAt', " +
            " MA.modifiedBy AS 'modifiedAddress_modifiedBy', " +
            " MA.address AS 'modifiedAddress_address', " +
            " MA.denomination AS 'modifiedAddress_denomination', " +
            " MA.number AS 'modifiedAddress_number', " +
            " MA.reference AS 'modifiedAddress_reference', " +
            " MA.district AS 'modifiedAddress_district', " +
            " MA.postalCode AS 'modifiedAddress_postalCode', " +
            " MA.latitude AS 'modifiedAddress_latitude', " +
            " MA.longitude AS 'modifiedAddress_longitude', " +
            " ModifiedAddressCity.identifier AS 'modifiedAddress_city_identifier', " +
            " ModifiedAddressCity.denomination AS 'modifiedAddress_city_denomination', " +
            " ModifiedAddressCityState.identifier AS 'modifiedAddress_city_state_identifier', " +
            " ModifiedAddressCityState.denomination AS 'modifiedAddress_city_state_denomination', " +
            " ModifiedAddressCityState.acronym AS 'modifiedAddress_city_state_acronym', " +
            " ModifiedAddressCityStateCountry.identifier AS 'modifiedAddress_city_state_country_identifier', " +
            " ModifiedAddressCityStateCountry.denomination AS 'modifiedAddress_city_state_country_denomination', " +
            " ModifiedAddressCityStateCountry.acronym AS 'modifiedAddress_city_state_country_acronym', " +
            " MAE.edification AS 'edification', " +
            " MAE.type AS 'type', " +
            " MAE.reference AS 'reference' " +
            "FROM ModifiedAddressEdification MAE " +
            "INNER JOIN ModifiedAddress MA ON MA.identifier = MAE.modifiedAddress " +
            "LEFT OUTER JOIN City ModifiedAddressCity ON ModifiedAddressCity.identifier = MA.city " +
            "LEFT OUTER JOIN State ModifiedAddressCityState ON ModifiedAddressCityState.identifier = ModifiedAddressCity.state " +
            "LEFT OUTER JOIN Country ModifiedAddressCityStateCountry ON ModifiedAddressCityStateCountry.identifier = ModifiedAddressCityState.country " +
            "WHERE MAE.modifiedAddress = :modifiedAddress AND MAE.edification = :edification")
    LiveData<ModifiedAddressEdificationModel> getModifiedAddressEdification(Integer modifiedAddress, Integer edification);


}