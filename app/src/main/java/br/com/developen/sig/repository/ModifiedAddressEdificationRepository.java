package br.com.developen.sig.repository;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import br.com.developen.sig.database.modified.ModifiedAddressEdificationDAO;
import br.com.developen.sig.database.modified.ModifiedAddressEdificationModel;
import br.com.developen.sig.util.DB;

public class ModifiedAddressEdificationRepository extends AndroidViewModel {

    private Application application;

    private ModifiedAddressEdificationDAO dao;

    private LiveData<List<ModifiedAddressEdificationModel>> modifiedAddressEdifications;

    public ModifiedAddressEdificationRepository(Application application){

        super(application);

        this.application = application;

    }

    public ModifiedAddressEdificationDAO getDao() {

        if (dao==null)

            dao = DB.getInstance(application).modifiedAddressEdificationDAO();

        return dao;

    }

    public void setDao(ModifiedAddressEdificationDAO dao) {

        this.dao = dao;

    }

    public LiveData<List<ModifiedAddressEdificationModel>> getEdificationsOfModifiedAddress(Integer modifiedAddress){

        if (modifiedAddressEdifications==null)

            modifiedAddressEdifications = getDao().getEdificationsOfModifiedAddress(modifiedAddress);

        return modifiedAddressEdifications;

    }

}