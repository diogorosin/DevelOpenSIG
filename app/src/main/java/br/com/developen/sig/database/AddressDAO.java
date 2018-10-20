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

    @Update
    void update(AddressVO addressVO);

    @Delete
    void delete(AddressVO addressVO);

    @Query("SELECT * FROM Address")
    LiveData<List<AddressVO>> getAddresses();

}