package br.com.developen.sig.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface StateDAO {

    @Insert
    Long create(StateVO stateVO);

    @Query("SELECT S.* FROM State S WHERE S.identifier = :identifier")
    StateVO retrieve(int identifier);

    @Query("SELECT COUNT(*) > 0 FROM State S WHERE S.identifier = :identifier")
    Boolean exists(int identifier);

    @Query("SELECT COUNT(*) FROM State S")
    Integer count();

    @Update
    void update(StateVO stateVO);

    @Delete
    void delete(StateVO stateVO);

}