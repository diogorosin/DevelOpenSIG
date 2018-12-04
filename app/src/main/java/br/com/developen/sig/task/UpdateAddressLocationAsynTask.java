package br.com.developen.sig.task;

import android.app.Activity;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.List;

import br.com.developen.sig.database.CityVO;
import br.com.developen.sig.database.modified.ModifiedAddressVO;
import br.com.developen.sig.exception.CannotInitializeDatabaseException;
import br.com.developen.sig.exception.InternalException;
import br.com.developen.sig.util.Constants;
import br.com.developen.sig.util.DB;
import br.com.developen.sig.util.Messaging;

public class UpdateAddressLocationAsynTask<A extends Activity & UpdateAddressLocationAsynTask.Listener >
        extends AsyncTask<Object, Void, Object> {


    private WeakReference<A> activity;

    private SharedPreferences preferences;


    public UpdateAddressLocationAsynTask(A activity) {

        this.activity = new WeakReference<>(activity);

        this.preferences = this.activity.get().
                getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, 0);

    }


    protected Object doInBackground(Object... params) {

        Integer modifiedAddressIdentifier = (Integer) params[0];

        Double latitude = (Double) params[1];

        Double longitude = (Double) params[2];

        String thoroughfare = "";

        String number = "";

        String district = "";

        String postalCode = "";

        String country = "";

        String state = "";

        String city = "";

        DB database = null;

        A activity = this.activity.get();

        if (activity != null)

            database = DB.getInstance(activity.getBaseContext());

        if (database==null)

            return new CannotInitializeDatabaseException();

        try {

            Geocoder geocoder = new Geocoder(activity.getBaseContext());

            List<Address> addresses = geocoder.
                    getFromLocation(latitude, longitude,1);

            if(addresses != null && addresses.size() > 0 ){

                Address address = addresses.get(0);

                thoroughfare = address.getThoroughfare();

                number = address.getFeatureName();

                district = address.getSubLocality();

                postalCode = address.getPostalCode();

                city = address.getLocality();

                state = address.getAdminArea();

                country = address.getCountryName();

                /*Log.d("DIOGO", String.format("%s, %s, %s, %s, %s, %s, %s, %s",
                        address,
                        thoroughfare,
                        district,
                        number,
                        postalCode,
                        state,
                        city,
                        country));*/

            }

            database.beginTransaction();

            ModifiedAddressVO modifiedAddressVO = database.
                    modifiedAddressDAO().
                    retrieve(modifiedAddressIdentifier);

            modifiedAddressVO.setLatitude(latitude);

            modifiedAddressVO.setLongitude(longitude);

            modifiedAddressVO.setDenomination(thoroughfare);

            modifiedAddressVO.setPostalCode(Integer.valueOf(postalCode.replace("-", "")));

            if (city!=null && !city.isEmpty()){

                CityVO cityVO = database.cityDAO().
                        findByCityStateCountry(city, state, country);

                if (cityVO != null) {

                    modifiedAddressVO.setDenomination(thoroughfare);

                    modifiedAddressVO.setNumber(number);

                    modifiedAddressVO.setDistrict(district);

                    modifiedAddressVO.setCity(cityVO.getIdentifier());

                    //Log.d("DIOGO", "Cidade: " + cityVO.getIdentifier().toString());

                }

            }

            database.modifiedAddressDAO().update(modifiedAddressVO);

            database.setTransactionSuccessful();

            return true;

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

            if (callResult instanceof Boolean){

                listener.onUpdateAddressLocationSuccess();

            } else {

                if (callResult instanceof Messaging){

                    listener.onUpdateAddressLocationFailure((Messaging) callResult);

                }

            }

        }

    }


    public interface Listener {

        void onUpdateAddressLocationSuccess();

        void onUpdateAddressLocationFailure(Messaging messaging);

    }


}