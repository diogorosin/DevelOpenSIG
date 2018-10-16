package br.com.developen.sig.task;

import android.os.AsyncTask;

import br.com.developen.sig.database.CityDAO;
import br.com.developen.sig.database.CityVO;

public class InsertOrUpdateCityAsynTask extends AsyncTask<CityVO, Void, Void> {

    private CityDAO dao;

    public InsertOrUpdateCityAsynTask(CityDAO cityDAO){

        this.dao = cityDAO;

    }

    protected Void doInBackground(CityVO... cities) {

        for (CityVO cityVO: cities) {

            if (dao.exists(cityVO.getIdentifier()))

                dao.update(cityVO);

            else

                dao.create(cityVO);

        }

        return null;

    }

}