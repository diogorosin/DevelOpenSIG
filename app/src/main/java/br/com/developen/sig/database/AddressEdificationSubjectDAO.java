package br.com.developen.sig.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AddressEdificationSubjectDAO {

    @Insert
    void create(AddressEdificationSubjectVO addressEdificationSubjectVO);

    @Query("SELECT AES.* " +
            "FROM AddressEdificationSubject AES " +
            "WHERE AES.address = :address AND AES.edification = :edification AND AES.subject = :subject")
    AddressEdificationVO retrieve(int address, String edification, Integer subject);

    @Query("SELECT COUNT(*) > 0 " +
            "FROM AddressEdificationSubject AES " +
            "WHERE AES.address = :address AND AES.edification = :edification AND AES.subject = :subject")
    Boolean exists(int address, String edification, Integer subject);

    @Update
    void update(AddressEdificationSubjectVO addressEdificationSubjectVO);

    @Delete
    void delete(AddressEdificationSubjectVO addressEdificationSubjectVO);

    @Query("SELECT " +
            " A.identifier AS 'address_identifier', " +
            " A.denomination AS 'address_denomination', " +
            " A.number AS 'address_number', " +
            " A.reference AS 'address_reference', " +
            " A.district AS 'address_district', " +
            " A.postalCode AS 'address_postalCode', " +
            " A.latitude AS 'address_latitude', " +
            " A.longitude AS 'address_longitude', " +
            " C.identifier AS 'address_city_identifier', " +
            " C.denomination AS 'address_city_denomination', " +
            " S.identifier AS 'address_city_state_identifier', " +
            " S.denomination AS 'address_city_state_denomination', " +
            " S.acronym AS 'address_city_state_acronym', " +
            " AE.edification AS 'edification', " +
            " SV.identifier AS 'subject_identifier', " +
            " SV.nameOrDenomination AS 'subject_nameOrDenomination', " +
            " SV.type AS 'subject_type' " +
            "FROM AddressEdificationSubject AES " +
            "INNER JOIN AddressEdification AE ON AE.address = AES.address AND AE.edification = AES.edification " +
            "INNER JOIN Address A ON A.identifier = AE.address " +
            "INNER JOIN City C ON C.identifier = A.city " +
            "INNER JOIN State S ON S.identifier = C.state " +
            "INNER JOIN Country Co ON Co.identifier = S.country " +
            "INNER JOIN SubjectView SV ON SV.identifier = AES.subject " +
            "WHERE SV.nameOrDenomination LIKE :nameOrDenomination")
    List<AddressEdificationSubjectModel> findBySubjectNameOrDenomination(String nameOrDenomination);

}