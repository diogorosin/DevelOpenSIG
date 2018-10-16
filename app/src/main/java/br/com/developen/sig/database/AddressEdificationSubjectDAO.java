package br.com.developen.sig.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

}