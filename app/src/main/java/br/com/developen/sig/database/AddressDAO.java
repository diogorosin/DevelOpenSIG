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
            " C.identifier AS 'city_identifier', " +
            " C.denomination AS 'city_denomination', " +
            " S.identifier AS 'city_state_identifier', " +
            " S.denomination AS 'city_state_denomination', " +
            " S.acronym AS 'city_state_acronym' " +
            "FROM Address A " +
            "INNER JOIN City C ON C.identifier = A.city " +
            "INNER JOIN State S ON S.identifier = C.state " +
            "INNER JOIN Country Co ON Co.identifier = S.country")
    LiveData<List<AddressModel>> getAddresses();

    @Query("SELECT Sv.identifier, Sv.nameOrDenomination " +
            "FROM AddressEdificationSubject AES " +
            "INNER JOIN AddressEdification AE ON AE.address = AES.address AND AE.edification = AES.edification " +
            "INNER JOIN Address A ON A.identifier = AE.address " +
            "INNER JOIN SubjectView Sv ON Sv.identifier = AES.subject " +
            "WHERE A.identifier = :address AND AES.'to' IS NULL " +
            "GROUP BY 1, 2")
    List<SubjectView> getSubjectsOfAddress(int address);

}