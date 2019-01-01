package br.com.developen.sig.repository;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import br.com.developen.sig.database.ModifiedAddressDAO;
import br.com.developen.sig.database.ModifiedAddressModel;
import br.com.developen.sig.util.DB;

public class ModifiedAddressRepository extends AndroidViewModel {

    private Application application;

    private ModifiedAddressDAO dao;

    private LiveData<ModifiedAddressModel> modifiedAddress;

    public ModifiedAddressRepository(Application application){

        super(application);

        this.application = application;

    }

    public ModifiedAddressDAO getDao() {

        if (dao==null)

            dao = DB.getInstance(application).modifiedAddressDAO();

        return dao;

    }

    public void setDao(ModifiedAddressDAO dao) {

        this.dao = dao;

    }

    public LiveData<ModifiedAddressModel> getModifiedAddress(Integer identifier){

        if (modifiedAddress==null)

            modifiedAddress = getDao().getModifiedAddressByIdentifier(identifier);

        return modifiedAddress;

    }

}