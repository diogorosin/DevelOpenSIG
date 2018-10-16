package br.com.developen.sig.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface OrganizationDAO {

    @Insert
    Long create(OrganizationVO organizationVO);

    @Query("SELECT O.* FROM Organization O WHERE O.identifier = :identifier")
    OrganizationVO retrieve(int identifier);

    @Query("SELECT COUNT(*) > 0 FROM Organization O WHERE O.identifier = :identifier")
    Boolean exists(int identifier);

    @Update
    void update(OrganizationVO organizationVO);

    @Delete
    void delete(OrganizationVO organizationVO);

}