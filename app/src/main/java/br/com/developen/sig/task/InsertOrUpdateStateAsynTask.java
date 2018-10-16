package br.com.developen.sig.task;

import android.os.AsyncTask;

import br.com.developen.sig.database.CountryDAO;
import br.com.developen.sig.database.CountryVO;
import br.com.developen.sig.database.StateDAO;
import br.com.developen.sig.database.StateVO;

public class InsertOrUpdateStateAsynTask extends AsyncTask<StateVO, Void, Void> {

    private StateDAO dao;

    public InsertOrUpdateStateAsynTask(StateDAO stateDAO){

        this.dao = stateDAO;

    }

    protected Void doInBackground(StateVO... states) {

        for (StateVO stateVO: states) {

            if (dao.exists(stateVO.getIdentifier()))

                dao.update(stateVO);

            else

                dao.create(stateVO);

        }

        return null;

    }

}