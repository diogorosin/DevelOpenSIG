package br.com.developen.sig.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface CountryDAO {

    @Insert
    Long create(CountryVO countryVO);

    @Query("SELECT C.* FROM Country C WHERE C.identifier = :identifier")
    CountryVO retrieve(int identifier);

    @Query("SELECT COUNT(*) > 0 FROM Country C WHERE C.identifier = :identifier")
    Boolean exists(int identifier);

    @Update
    void update(CountryVO countryVO);

    @Delete
    void delete(CountryVO countryVO);

}