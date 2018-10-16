package br.com.developen.sig.repository;

import android.app.Application;

import br.com.developen.sig.database.CityDAO;
import br.com.developen.sig.database.CityVO;
import br.com.developen.sig.task.InsertOrUpdateCityAsynTask;
import br.com.developen.sig.util.DB;

public class CityRepository {

    private Application application;

    private CityDAO dao;

    public CityRepository(Application application){

        this.application = application;

    }

    public void insertOrUpdate(CityVO... cities){

        new InsertOrUpdateCityAsynTask(getDao()).execute(cities);

    }

    public CityDAO getDao() {

        if (dao==null)

            dao = DB.getInstance(application).cityDAO();

        return dao;

    }

    public void setDao(CityDAO dao) {

        this.dao = dao;

    }

}