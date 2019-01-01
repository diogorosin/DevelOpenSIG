package br.com.developen.sig.task;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.Date;

import br.com.developen.sig.database.ModifiedAddressEdificationDwellerVO;
import br.com.developen.sig.database.ModifiedAddressEdificationVO;
import br.com.developen.sig.database.ModifiedAddressVO;
import br.com.developen.sig.exception.CannotInitializeDatabaseException;
import br.com.developen.sig.exception.InternalException;
import br.com.developen.sig.util.Constants;
import br.com.developen.sig.util.DB;
import br.com.developen.sig.util.Messaging;

public class CreateAddressAsynTask<A extends Activity & CreateAddressAsynTask.Listener >
        extends AsyncTask<Double, Void, Object> {


    private WeakReference<A> activity;

    private SharedPreferences preferences;


    public CreateAddressAsynTask(A activity) {

        this.activity = new WeakReference<>(activity);

        this.preferences = this.activity.get().
                getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, 0);

    }


    protected Object doInBackground(Double... params) {

        Double latitude = params[0];

        Double longitude = params[1];

        Integer modifiedBy = this.preferences.getInt(Constants.USER_IDENTIFIER_PROPERTY, 0);

        Date modifiedAt = new Date();

        DB database = null;

        if (activity.get() != null)

            database = DB.getInstance(activity.get().getBaseContext());

        if (database==null)

            return new CannotInitializeDatabaseException();

        try {

            database.beginTransaction();

            ModifiedAddressVO modifiedAddressVO = new ModifiedAddressVO();

            modifiedAddressVO.setModifiedBy(modifiedBy);

            modifiedAddressVO.setModifiedAt(modifiedAt);

            modifiedAddressVO.setLatitude(latitude);

            modifiedAddressVO.setLongitude(longitude);

            Integer identifier = database.
                    modifiedAddressDAO().
                    create(modifiedAddressVO).intValue();

            ModifiedAddressEdificationVO modifiedAddressEdificationVO = new ModifiedAddressEdificationVO();

            modifiedAddressEdificationVO.setModifiedAddress(identifier);

            modifiedAddressEdificationVO.setEdification(1);

            modifiedAddressEdificationVO.setType(1);

            database.modifiedAddressEdificationDAO().create(modifiedAddressEdificationVO);


            ModifiedAddressEdificationDwellerVO modifiedAddressEdificationDwellerVO = new ModifiedAddressEdificationDwellerVO();

            modifiedAddressEdificationDwellerVO.setModifiedAddress(modifiedAddressEdificationVO.getModifiedAddress());

            modifiedAddressEdificationDwellerVO.setEdification(modifiedAddressEdificationVO.getEdification());

            modifiedAddressEdificationDwellerVO.setDweller(1);

            modifiedAddressEdificationDwellerVO.setSubject(null);

            modifiedAddressEdificationDwellerVO.setNameOrDenomination("Patricia Ragazzon");

            modifiedAddressEdificationDwellerVO.setFancyName(null);

            modifiedAddressEdificationDwellerVO.setMotherName("Ivani Ragazzon");

            modifiedAddressEdificationDwellerVO.setFatherName("Walter Ragazzon");

            modifiedAddressEdificationDwellerVO.setType("F");

            modifiedAddressEdificationDwellerVO.setGender("F");

            modifiedAddressEdificationDwellerVO.setBirthDate(new Date());

            modifiedAddressEdificationDwellerVO.setBirthPlace(4569);

            modifiedAddressEdificationDwellerVO.setCpf(5369609926L);

            modifiedAddressEdificationDwellerVO.setRgNumber(3100191L);

            modifiedAddressEdificationDwellerVO.setRgAgency(1);

            modifiedAddressEdificationDwellerVO.setRgState(24);

            modifiedAddressEdificationDwellerVO.setFrom(new Date());

            database.modifiedAddressEdificationDwellerDAO().create(modifiedAddressEdificationDwellerVO);



            modifiedAddressEdificationDwellerVO = new ModifiedAddressEdificationDwellerVO();

            modifiedAddressEdificationDwellerVO.setModifiedAddress(modifiedAddressEdificationVO.getModifiedAddress());

            modifiedAddressEdificationDwellerVO.setEdification(modifiedAddressEdificationVO.getEdification());

            modifiedAddressEdificationDwellerVO.setDweller(2);

            modifiedAddressEdificationDwellerVO.setSubject(null);

            modifiedAddressEdificationDwellerVO.setNameOrDenomination("Diogo Rosin");

            modifiedAddressEdificationDwellerVO.setFancyName(null);

            modifiedAddressEdificationDwellerVO.setMotherName("Ilonir Rosin");

            modifiedAddressEdificationDwellerVO.setFatherName("Odilete Buzin");

            modifiedAddressEdificationDwellerVO.setType("F");

            modifiedAddressEdificationDwellerVO.setGender("M");

            modifiedAddressEdificationDwellerVO.setBirthDate(new Date());

            modifiedAddressEdificationDwellerVO.setBirthPlace(4569);

            modifiedAddressEdificationDwellerVO.setCpf(5369649926L);

            modifiedAddressEdificationDwellerVO.setRgNumber(3700191L);

            modifiedAddressEdificationDwellerVO.setRgAgency(1);

            modifiedAddressEdificationDwellerVO.setRgState(24);

            modifiedAddressEdificationDwellerVO.setFrom(new Date());

            database.modifiedAddressEdificationDwellerDAO().create(modifiedAddressEdificationDwellerVO);




            database.setTransactionSuccessful();

            return identifier;

        } catch(Exception e) {

            e.printStackTrace();

            return new InternalException();

        } finally {

            if (database.inTransaction())

                database.endTransaction();

        }

    }


    protected void onPostExecute(Object callResult) {

        A listener = this.activity.get();

        if (listener != null) {

            if (callResult instanceof Integer){

                listener.onCreateAddressSuccess((Integer) callResult);

            } else {

                if (callResult instanceof Messaging){

                    listener.onCreateAddressFailure((Messaging) callResult);

                }

            }

        }

    }


    public interface Listener {

        void onCreateAddressSuccess(Integer identifier);

        void onCreateAddressFailure(Messaging messaging);

    }


}