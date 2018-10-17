package br.com.developen.sig.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SubjectDAO {

    @Insert
    Long create(SubjectVO subjectVO);

    @Query("SELECT S.* FROM Subject S WHERE S.identifier = :identifier")
    SubjectVO retrieve(int identifier);

    @Query("SELECT COUNT(*) > 0 FROM Subject S WHERE S.identifier = :identifier")
    Boolean exists(int identifier);

    @Update
    void update(SubjectVO subjectVO);

    @Delete
    void delete(SubjectVO subjectVO);

}