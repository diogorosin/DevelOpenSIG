package br.com.developen.sig.util;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import br.com.developen.sig.database.AddressDAO;
import br.com.developen.sig.database.AddressEdificationDAO;
import br.com.developen.sig.database.AddressEdificationDwellerVO;
import br.com.developen.sig.database.AddressEdificationDwellerDAO;
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
import br.com.developen.sig.database.SubjectView;
import br.com.developen.sig.database.ModifiedAddressDAO;
import br.com.developen.sig.database.ModifiedAddressEdificationDAO;
import br.com.developen.sig.database.ModifiedAddressEdificationDwellerDAO;
import br.com.developen.sig.database.ModifiedAddressEdificationDwellerVO;
import br.com.developen.sig.database.ModifiedAddressEdificationVO;
import br.com.developen.sig.database.ModifiedAddressVO;


@Database(entities = {
        AddressVO.class,
        AddressEdificationVO.class,
        AddressEdificationDwellerVO.class,
        AgencyVO.class,
        CityVO.class,
        CountryVO.class,
        IndividualVO.class,
        OrganizationVO.class,
        StateVO.class,
        SubjectVO.class,
        SubjectView.class,
        ModifiedAddressVO.class,
        ModifiedAddressEdificationVO.class,
        ModifiedAddressEdificationDwellerVO.class},
        version = 001, exportSchema = false)
public abstract class DB extends RoomDatabase {

    private static DB INSTANCE;

    public static DB getInstance(Context context) {

        if (INSTANCE == null) {

            INSTANCE = Room
                    .databaseBuilder(context.getApplicationContext(), DB.class, "sig-database")
                    .allowMainThreadQueries()
                    .addCallback(new RoomDatabase.Callback() {
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            db.execSQL("DROP TABLE IF EXISTS SubjectView");
                            db.execSQL("CREATE VIEW IF NOT EXISTS SubjectView " +
                                    "AS SELECT I.identifier, I.name AS nameOrDenomination, 'I' AS type " +
                                    "FROM Subject S1 " +
                                    "INNER JOIN Individual I ON I.identifier = S1.identifier " +
                                    "UNION ALL " +
                                    "SELECT O.identifier, O.denomination AS nameOrDenomination, 'O' AS type " +
                                    "FROM Subject S2 " +
                                    "INNER JOIN Organization O ON O.identifier = S2.identifier"
                            );
                        }
                    }).build();

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

    public abstract AddressEdificationDwellerDAO addressEdificationDwellerDAO();

    public abstract ModifiedAddressDAO modifiedAddressDAO();

    public abstract ModifiedAddressEdificationDAO modifiedAddressEdificationDAO();

    public abstract ModifiedAddressEdificationDwellerDAO modifiedAddressEdificationDwellerDAO();

}