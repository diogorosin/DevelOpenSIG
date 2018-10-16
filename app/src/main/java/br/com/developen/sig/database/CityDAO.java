package br.com.developen.sig.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface CityDAO {

    @Insert
    Long create(CityVO cityVO);

    @Query("SELECT C.* FROM City C WHERE C.identifier = :identifier")
    CityVO retrieve(int identifier);

    @Query("SELECT COUNT(*) > 0 FROM City C WHERE C.identifier = :identifier")
    Boolean exists(int identifier);

    @Update
    void update(CityVO cityVO);

    @Delete
    void delete(CityVO cityVO);

}