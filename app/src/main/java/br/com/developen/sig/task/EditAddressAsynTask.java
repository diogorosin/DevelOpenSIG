package br.com.developen.sig.task;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.Date;

import br.com.developen.sig.database.AddressVO;
import br.com.developen.sig.database.ModifiedAddressVO;
import br.com.developen.sig.exception.CannotInitializeDatabaseException;
import br.com.developen.sig.exception.InternalException;
import br.com.developen.sig.util.Constants;
import br.com.developen.sig.util.DB;
import br.com.developen.sig.util.Messaging;

public class EditAddressAsynTask<A extends Activity & EditAddressAsynTask.Listener >
        extends AsyncTask<Integer, Void, Object> {


    private WeakReference<A> activity;

    private SharedPreferences preferences;


    public EditAddressAsynTask(A activity) {

        this.activity = new WeakReference<>(activity);

        this.preferences = this.activity.get().
                getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, 0);

    }


    protected Object doInBackground(Integer... addresses) {

        Integer addressIdentifier = addresses[0];

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

            //if (addressIdentifier!=null || addressIdentifier > 0){

                AddressVO addressVO = database.
                        addressDAO().
                        retrieve(addressIdentifier);

                modifiedAddressVO.setAddress(addressVO.getIdentifier());

                modifiedAddressVO.setDenomination(addressVO.getDenomination());

                modifiedAddressVO.setNumber(addressVO.getNumber());

                modifiedAddressVO.setReference(addressVO.getReference());

                modifiedAddressVO.setDistrict(addressVO.getDistrict());

                modifiedAddressVO.setCity(addressVO.getCity());

                modifiedAddressVO.setPostalCode(addressVO.getPostalCode());

                modifiedAddressVO.setLatitude(addressVO.getLatitude());

                modifiedAddressVO.setLongitude(addressVO.getLongitude());

            //}

            Integer identifier = database.
                    modifiedAddressDAO().
                    create(modifiedAddressVO).intValue();

            database.setTransactionSuccessful();

            return identifier;

        } catch(Exception e) {

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

                listener.onEditAddressSuccess((Integer) callResult);

            } else {

                if (callResult instanceof Messaging){

                    listener.onEditAddressFailure((Messaging) callResult);

                }

            }

        }

    }


    public interface Listener {

        void onEditAddressSuccess(Integer identifier);

        void onEditAddressFailure(Messaging messaging);

    }


}