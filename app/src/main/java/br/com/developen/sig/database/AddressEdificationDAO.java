package br.com.developen.sig.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface AddressEdificationDAO {

    @Insert
    void create(AddressEdificationVO addressEdificationVO);

    @Query("SELECT AE.* FROM AddressEdification AE WHERE AE.address = :address AND AE.edification = :edification")
    AddressEdificationVO retrieve(int address, Integer edification);

    @Query("SELECT COUNT(*) > 0 FROM AddressEdification AE WHERE AE.address = :address AND AE.edification = :edification")
    Boolean exists(int address, Integer edification);

    @Update
    void update(AddressEdificationVO addressEdificationVO);

    @Delete
    void delete(AddressEdificationVO addressEdificationVO);

}