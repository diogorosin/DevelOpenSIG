package br.com.developen.sig.util;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.com.developen.sig.database.AddressDAO;
import br.com.developen.sig.database.AddressEdificationDAO;
import br.com.developen.sig.database.AddressEdificationSubjectDAO;
import br.com.developen.sig.database.AddressEdificationSubjectVO;
import br.com.developen.sig.database.AddressEdificationVO;
import br.com.developen.sig.database.AddressVO;
import br.com.developen.sig.database.AgencyDAO;
import br.com.developen.sig.database.AgencyVO;
import br.com.developen.sig.database.CityDAO;
import br.com.developen.sig.database.CityVO;
import br.com.developen.sig.database.CountryDAO;
import br.com.developen.sig.database.CountryVO;
import br.com.developen.sig.database.IndividualDAO;
import br.com.developen.sig.database.IndividualVO;
import br.com.developen.sig.database.OrganizationDAO;
import br.com.developen.sig.database.OrganizationVO;
import br.com.developen.sig.database.StateDAO;
import br.com.developen.sig.database.StateVO;
import br.com.developen.sig.database.SubjectDAO;
import br.com.developen.sig.database.SubjectVO;


@Database(entities = {
        AddressVO.class,
        AddressEdificationVO.class,
        AddressEdificationSubjectVO.class,
        AgencyVO.class,
        CityVO.class,
        CountryVO.class,
        IndividualVO.class,
        OrganizationVO.class,
        StateVO.class,
        SubjectVO.class},
        version = 001, exportSchema = false)
public abstract class DB extends RoomDatabase {

    private static DB INSTANCE;

    public static DB getInstance(Context context) {

        if (INSTANCE == null) {

            INSTANCE = Room
                    .databaseBuilder(context.getApplicationContext(), DB.class, "sig-database")
                    .allowMainThreadQueries()
                    .build();

        }

        return INSTANCE;

    }

    public abstract SubjectDAO subjectDAO();

    public abstract IndividualDAO individualDAO();

    public abstract OrganizationDAO organizationDAO();

    public abstract AgencyDAO agencyDAO();

    public abstract CountryDAO countryDAO();

    public abstract StateDAO stateDAO();

    public abstract CityDAO cityDAO();

    public abstract AddressDAO addressDAO();

    public abstract AddressEdificationDAO addressEdificationDAO();

    public abstract AddressEdificationSubjectDAO addressEdificationSubjectDAO();

}