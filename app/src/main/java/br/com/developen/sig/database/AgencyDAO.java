package br.com.developen.sig.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface AgencyDAO {

    @Insert
    Long create(AgencyVO agencyVO);

    @Query("SELECT A.* FROM Agency A WHERE A.identifier = :identifier")
    AgencyVO retrieve(int identifier);

    @Query("SELECT COUNT(*) > 0 FROM Agency A WHERE A.identifier = :identifier")
    Boolean exists(int identifier);

    @Update
    void update(AgencyVO agencyVO);

    @Delete
    void delete(AgencyVO agencyVO);

}