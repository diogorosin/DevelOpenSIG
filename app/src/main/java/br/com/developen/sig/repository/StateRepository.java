package br.com.developen.sig.repository;

import android.app.Application;

import br.com.developen.sig.database.StateDAO;
import br.com.developen.sig.database.StateVO;
import br.com.developen.sig.task.InsertOrUpdateStateAsynTask;
import br.com.developen.sig.util.DB;

public class StateRepository {

    private Application application;

    private StateDAO dao;

    public StateRepository(Application application){

        this.application = application;

    }

    public void insertOrUpdate(StateVO... states){

        new InsertOrUpdateStateAsynTask(getDao()).execute(states);

    }

    public StateDAO getDao() {

        if (dao==null)

            dao = DB.getInstance(application).stateDAO();

        return dao;

    }

    public void setDao(StateDAO dao) {

        this.dao = dao;

    }

}