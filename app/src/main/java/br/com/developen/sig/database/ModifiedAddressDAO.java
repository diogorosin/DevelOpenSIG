package br.com.developen.sig.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface ModifiedAddressDAO {

    @Insert
    Long create(ModifiedAddressVO modifiedlAddressVO);

    @Query("SELECT MA.* FROM ModifiedAddress MA WHERE MA.identifier = :identifier")
    ModifiedAddressVO retrieve(int identifier);

    @Query("SELECT COUNT(*) > 0 FROM ModifiedAddress MA WHERE MA.identifier = :identifier")
    Boolean exists(int identifier);

    @Query("SELECT COUNT(*) FROM ModifiedAddress MA")
    Integer count();

    @Update
    void update(ModifiedAddressVO modifiedAddressVO);

    @Delete
    void delete(ModifiedAddressVO modifiedAddressVO);

    @Query("SELECT " +
            " MA.identifier AS 'identifier', " +
            " MA.syncedAt AS 'syncedAt', " +
            " MA.modifiedAt AS 'modifiedAt', " +
            " MA.modifiedBy AS 'modifiedBy', " +
            " MA.address AS 'address', " +
            " MA.denomination AS 'denomination', " +
            " MA.number AS 'number', " +
            " MA.reference AS 'reference', " +
            " MA.district AS 'district', " +
            " MA.postalCode AS 'postalCode', " +
            " MA.latitude AS 'latitude', " +
            " MA.longitude AS 'longitude', " +
            " C.identifier AS 'city_identifier', " +
            " C.denomination AS 'city_denomination', " +
            " S.identifier AS 'city_state_identifier', " +
            " S.denomination AS 'city_state_denomination', " +
            " S.acronym AS 'city_state_acronym', " +
            " Co.identifier AS 'city_state_country_identifier', " +
            " Co.denomination AS 'city_state_country_denomination', " +
            " Co.acronym AS 'city_state_country_acronym' " +
            "FROM ModifiedAddress MA " +
            "LEFT OUTER JOIN City C ON C.identifier = MA.city " +
            "LEFT OUTER JOIN State S ON S.identifier = C.state " +
            "LEFT OUTER JOIN Country Co ON Co.identifier = S.country " +
            "WHERE MA.identifier = :identifier")
    LiveData<ModifiedAddressModel> getModifiedAddressByIdentifier(Integer identifier);

}