package br.com.developen.sig.repository;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import br.com.developen.sig.database.modified.ModifiedAddressEdificationDwellerModel;
import br.com.developen.sig.database.modified.ModifiedAddressEdificationDwellerDAO;
import br.com.developen.sig.util.DB;

public class ModifiedAddressEdificationDwellerRepository extends AndroidViewModel {

    private Application application;

    private ModifiedAddressEdificationDwellerDAO dao;

    private LiveData<List<ModifiedAddressEdificationDwellerModel>> modifiedAddressEdificationDwellers;

    public ModifiedAddressEdificationDwellerRepository(Application application){

        super(application);

        this.application = application;

    }

    public ModifiedAddressEdificationDwellerDAO getDao() {

        if (dao==null)

            dao = DB.getInstance(application).modifiedAddressEdificationDwellerDAO();

        return dao;

    }

    public void setDao(ModifiedAddressEdificationDwellerDAO dao) {

        this.dao = dao;

    }

    public LiveData<List<ModifiedAddressEdificationDwellerModel>> getDwellersOfModifiedAddressEdification(Integer modifiedAddress, Integer edification){

        if (modifiedAddressEdificationDwellers ==null)

            modifiedAddressEdificationDwellers = getDao().getDwellersOfModifiedAddressEdification(modifiedAddress, edification);

        return modifiedAddressEdificationDwellers;

    }

}