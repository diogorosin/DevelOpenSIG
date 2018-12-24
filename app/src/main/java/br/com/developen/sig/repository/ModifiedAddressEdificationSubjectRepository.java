package br.com.developen.sig.repository;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import br.com.developen.sig.database.modified.ModifiedAddressEdificationSubjectDAO;
import br.com.developen.sig.database.modified.ModifiedAddressEdificationSubjectModel;
import br.com.developen.sig.util.DB;

public class ModifiedAddressEdificationSubjectRepository extends AndroidViewModel {

    private Application application;

    private ModifiedAddressEdificationSubjectDAO dao;

    private LiveData<List<ModifiedAddressEdificationSubjectModel>> modifiedAddressEdificationSubjects;

    public ModifiedAddressEdificationSubjectRepository(Application application){

        super(application);

        this.application = application;

    }

    public ModifiedAddressEdificationSubjectDAO getDao() {

        if (dao==null)

            dao = DB.getInstance(application).modifiedAddressEdificationSubjectDAO();

        return dao;

    }

    public void setDao(ModifiedAddressEdificationSubjectDAO dao) {

        this.dao = dao;

    }

    public LiveData<List<ModifiedAddressEdificationSubjectModel>> getSubjectsOfModifiedAddressEdification(Integer modifiedAddress, String edification){

        if (modifiedAddressEdificationSubjects==null)

            modifiedAddressEdificationSubjects = getDao().getSubjectsOfModifiedAddressEdification(modifiedAddress, edification);

        return modifiedAddressEdificationSubjects;

    }

}