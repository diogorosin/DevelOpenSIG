package br.com.developen.sig.task;

import android.os.AsyncTask;

import br.com.developen.sig.database.CountryDAO;
import br.com.developen.sig.database.CountryVO;

public class InsertOrUpdateCountryAsynTask extends AsyncTask<CountryVO, Void, Void> {

    private CountryDAO dao;

    public InsertOrUpdateCountryAsynTask(CountryDAO countryDAO){

        this.dao = countryDAO;

    }

    protected Void doInBackground(CountryVO... countries) {

        for (CountryVO countryVO: countries) {

            if (dao.exists(countryVO.getIdentifier()))

                dao.update(countryVO);

            else

                dao.create(countryVO);

        }

        return null;

    }

}