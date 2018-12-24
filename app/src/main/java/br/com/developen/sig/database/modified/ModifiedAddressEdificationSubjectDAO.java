package br.com.developen.sig.database.modified;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ModifiedAddressEdificationSubjectDAO {

    @Insert
    Long create(ModifiedAddressEdificationSubjectVO modifiedlAddressEdificationSubjectVO);

    @Query("SELECT MAES.* " +
            "FROM ModifiedAddressEdificationSubject MAES " +
            "WHERE MAES.modifiedAddress = :modifiedAddress AND MAES.edification = :edification AND MAES.sequence = :sequence")
    ModifiedAddressEdificationSubjectVO retrieve(int modifiedAddress, String edification, String sequence);

    @Query("SELECT COUNT(*) > 0 " +
            "FROM ModifiedAddressEdificationSubject MAES " +
            "WHERE MAES.modifiedAddress = :modifiedAddress AND MAES.edification = :edification AND MAES.sequence = :sequence")
    Boolean exists(int modifiedAddress, String edification, String sequence);

    @Query("SELECT COUNT(*) FROM ModifiedAddressEdificationSubject MAES")
    Integer count();

    @Update
    void update(ModifiedAddressEdificationSubjectVO modifiedAddressEdificationSubjectVO);

    @Delete
    void delete(ModifiedAddressEdificationSubjectVO modifiedAddressEdificationSubjectVO);

    @Query("SELECT " +
            " MAES.modifiedAddress AS 'modifiedAddress', " +
            " MAES.edification AS 'edification', " +
            " MAES.sequence AS 'sequence', " +
            " MAES.subject AS 'subject', " +
            " MAES.nameOrDenomination AS 'nameOrDenomination', " +
            " MAES.type AS 'type', " +
            " MAES.fancyName AS 'fancyName', " +
            " MAES.motherName AS 'motherName', " +
            " MAES.fatherName AS 'fatherName', " +
            " MAES.cpf AS 'cpf', " +
            " MAES.rgNumber AS 'rgNumber', " +
            " RgAgency.identifier AS 'rgAgency_identifier', " +
            " RgAgency.denomination AS 'rgAgency_denomination', " +
            " RgAgency.acronym AS 'rgAgency_acronym', " +
            " RgState.identifier AS 'rgState_identifier', " +
            " RgState.denomination AS 'rgState_denomination', " +
            " RgState.acronym AS 'rgState_acronym', " +
            " RgStateCountry.identifier AS 'rgState_country_identifier', " +
            " RgStateCountry.denomination AS 'rgState_country_denomination', " +
            " RgStateCountry.acronym AS 'rgState_country_acronym', " +
            " BirthPlace.identifier AS 'birthPlace_identifier', " +
            " BirthPlace.denomination AS 'birthPlace_denomination', " +
            " BirthPlaceState.identifier AS 'birthPlace_state_identifier', " +
            " BirthPlaceState.denomination AS 'birthPlace_state_denomination', " +
            " BirthPlaceState.acronym AS 'birthPlace_state_acronym', " +
            " BirthPlaceStateCountry.identifier AS 'birthPlace_state__country_identifier', " +
            " BirthPlaceStateCountry.denomination AS 'birthPlace_state_country_denomination', " +
            " BirthPlaceStateCountry.acronym AS 'birthPlace_state_country_acronym', " +
            " MAES.birthDate AS 'birthDate', " +
            " MAES.gender AS 'gender' " +
            "FROM ModifiedAddressEdificationSubject MAES " +
            "INNER JOIN ModifiedAddressEdification MAE ON MAE.modifiedAddress = MAES.modifiedAddress AND MAE.edification = MAES.edification " +
            "INNER JOIN ModifiedAddress MA ON MA.identifier = MAE.modifiedAddress " +
            "LEFT OUTER JOIN Agency RgAgency ON RgAgency.identifier = MAES.rgAgency " +
            "LEFT OUTER JOIN State RgState ON RgState.identifier = MAES.rgState " +
            "LEFT OUTER JOIN Country RgStateCountry ON RgStateCountry.identifier = RgState.country " +
            "LEFT OUTER JOIN City BirthPlace ON BirthPlace.identifier = MAES.birthPlace " +
            "LEFT OUTER JOIN State BirthPlaceState ON BirthPlaceState.identifier = BirthPlace.state " +
            "LEFT OUTER JOIN Country BirthPlaceStateCountry ON BirthPlaceStateCountry.identifier = BirthPlaceState.country " +
            "WHERE MAE.modifiedAddress = :modifiedAddress AND MAE.edification = :edification")
    LiveData<List<ModifiedAddressEdificationSubjectModel>> getSubjectsOfModifiedAddressEdification(Integer modifiedAddress, String edification);

}