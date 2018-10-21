package br.com.developen.sig.repository;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import br.com.developen.sig.database.AddressDAO;
import br.com.developen.sig.database.AddressModel;
import br.com.developen.sig.database.AddressVO;
import br.com.developen.sig.util.DB;

public class AddressRepository extends AndroidViewModel {

    private Application application;

    private AddressDAO dao;

    private LiveData<List<AddressModel>> addresses;

    public AddressRepository(Application application){

        super(application);

        this.application = application;

    }

    public AddressDAO getDao() {

        if (dao==null)

            dao = DB.getInstance(application).addressDAO();

        return dao;

    }

    public void setDao(AddressDAO dao) {

        this.dao = dao;

    }

    public LiveData<List<AddressModel>> getAddresses(){

        if (addresses==null)

            addresses = getDao().getAddresses();

        return addresses;

    }

}