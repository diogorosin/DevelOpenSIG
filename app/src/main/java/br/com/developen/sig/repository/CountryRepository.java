package br.com.developen.sig.repository;

import android.app.Application;

import br.com.developen.sig.database.CountryDAO;
import br.com.developen.sig.database.CountryVO;
import br.com.developen.sig.task.InsertOrUpdateCountryAsynTask;
import br.com.developen.sig.util.DB;

public class CountryRepository {

    private Application application;

    private CountryDAO dao;

    public CountryRepository(Application application){

        this.application = application;

    }

    public void insertOrUpdate(CountryVO... countries){

        new InsertOrUpdateCountryAsynTask(getDao()).execute(countries);

    }

    public CountryDAO getDao() {

        if (dao==null)

            dao = DB.getInstance(application).countryDAO();

        return dao;

    }

    public void setDao(CountryDAO dao) {

        this.dao = dao;

    }

}