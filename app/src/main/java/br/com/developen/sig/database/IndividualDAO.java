package br.com.developen.sig.database;

import android.arch.persistence.room.Dao;
        import android.arch.persistence.room.Delete;
        import android.arch.persistence.room.Insert;
        import android.arch.persistence.room.Query;
        import android.arch.persistence.room.Update;

@Dao
public interface IndividualDAO {

    @Insert
    Long create(IndividualVO individualVO);

    @Query("SELECT I.* FROM Individual I WHERE I.identifier = :identifier")
    IndividualVO retrieve(int identifier);

    @Query("SELECT COUNT(*) > 0 FROM Individual I WHERE I.identifier = :identifier")
    Boolean exists(int identifier);

    @Update
    void update(IndividualVO individualVO);

    @Delete
    void delete(IndividualVO individualVO);

}