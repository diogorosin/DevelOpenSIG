package br.com.developen.sig.database.modified;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ModifiedAddressEdificationDAO {

    @Insert
    Long create(ModifiedAddressEdificationVO modifiedlAddressEdificationVO);

    @Query("SELECT MAE.* FROM ModifiedAddressEdification MAE WHERE MAE.modifiedAddress = :modifiedAddress AND MAE.edification = :edification")
    ModifiedAddressEdificationVO retrieve(int modifiedAddress, String edification);

    @Query("SELECT COUNT(*) > 0 FROM ModifiedAddressEdification MAE WHERE MAE.modifiedAddress = :modifiedAddress AND MAE.edification = :edification")
    Boolean exists(int modifiedAddress, String edification);

    @Query("SELECT COUNT(*) FROM ModifiedAddressEdification MAE")
    Integer count();

    @Update
    void update(ModifiedAddressEdificationVO modifiedAddressEdificationVO);

    @Delete
    void delete(ModifiedAddressEdificationVO modifiedAddressEdificationVO);

    @Query("SELECT " +
            " MAE.modifiedAddress AS 'modifiedAddress', " +
            " MAE.edification AS 'edification' " +
            "FROM ModifiedAddressEdification MAE " +
            "INNER JOIN ModifiedAddress MA ON MA.identifier = MAE.modifiedAddress " +
            "WHERE MA.identifier = :modifiedAddress")
    LiveData<List<ModifiedAddressEdificationModel>> getEdificationsOfModifiedAddress(Integer modifiedAddress);

}