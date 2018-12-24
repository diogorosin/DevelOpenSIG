package br.com.developen.sig.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AddressDAO {

    @Insert
    Long create(AddressVO addressVO);

    @Query("SELECT A.* FROM Address A WHERE A.identifier = :identifier")
    AddressVO retrieve(int identifier);

    @Query("SELECT COUNT(*) > 0 FROM Address A WHERE A.identifier = :identifier")
    Boolean exists(int identifier);

    @Query("SELECT COUNT(*) FROM Address A")
    Integer count();

    @Update
    void update(AddressVO addressVO);

    @Delete
    void delete(AddressVO addressVO);

    @Query("SELECT " +
            " A.identifier AS 'identifier', " +
            " A.denomination AS 'denomination', " +
            " A.number AS 'number', " +
            " A.reference AS 'reference', " +
            " A.district AS 'district', " +
            " A.postalCode AS 'postalCode', " +
            " A.latitude AS 'latitude', " +
            " A.longitude AS 'longitude', " +
            " City.identifier AS 'city_identifier', " +
            " City.denomination AS 'city_denomination', " +
            " CityState.identifier AS 'city_state_identifier', " +
            " CityState.denomination AS 'city_state_denomination', " +
            " CityState.acronym AS 'city_state_acronym', " +
            " CityStateCountry.identifier AS 'city_state_country_identifier', " +
            " CityStateCountry.denomination AS 'city_state_country_denomination', " +
            " CityStateCountry.acronym AS 'city_state_country_acronym' " +
            "FROM Address A " +
            "INNER JOIN City City ON City.identifier = A.city " +
            "INNER JOIN State CityState ON CityState.identifier = City.state " +
            "INNER JOIN Country CityStateCountry ON CityStateCountry.identifier = CityState.country")
    LiveData<List<AddressModel>> getAddresses();

    @Query("SELECT Sv.identifier, Sv.nameOrDenomination " +
            "FROM AddressEdificationDweller AED " +
            "INNER JOIN AddressEdification AE ON AE.address = AED.address AND AE.edification = AED.edification " +
            "INNER JOIN Address A ON A.identifier = AE.address " +
            "INNER JOIN SubjectView Sv ON Sv.identifier = AED.subject " +
            "WHERE A.identifier = :address AND AED.'to' IS NULL " +
            "GROUP BY 1, 2")
    List<SubjectView> getSubjectsOfAddress(int address);

}