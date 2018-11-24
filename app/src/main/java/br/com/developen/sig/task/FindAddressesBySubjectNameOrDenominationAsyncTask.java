package br.com.developen.sig.task;

import android.app.Activity;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.List;

import br.com.developen.sig.database.AddressEdificationSubjectModel;
import br.com.developen.sig.exception.CannotLoadPlacesException;
import br.com.developen.sig.util.DB;
import br.com.developen.sig.util.Messaging;

public class FindAddressesBySubjectNameOrDenominationAsyncTask<
        A extends Activity & FindAddressesBySubjectNameOrDenominationAsyncTask.Listener,
        B extends String,
        C extends Void,
        D> extends AsyncTask<B, C, D> {


    private WeakReference<A> activity;


    public FindAddressesBySubjectNameOrDenominationAsyncTask(A activity) {

        this.activity = new WeakReference<>(activity);

    }


    protected Object doInBackground(String... parameters) {

        try {

            DB database = DB.getInstance(activity.get());

            return database.
                    addressEdificationSubjectDAO().
                    findBySubjectNameOrDenomination( "%" + parameters[0].toUpperCase() + "%");

        } catch (Exception e) {

            return new CannotLoadPlacesException();

        }

    }


    protected void onPostExecute(Object callResult) {

        A activity = this.activity.get();

        if (activity != null) {

            if (callResult != null) {

                if (callResult instanceof List) {

                    activity.onSuccess((List<AddressEdificationSubjectModel>) callResult);

                } else {

                    if (callResult instanceof Messaging){

                        activity.onFailure((Messaging) callResult);

                    }

                }

            }

        }

    }

    public interface Listener {

        void onSuccess(List<AddressEdificationSubjectModel> list);

        void onFailure(Messaging messaging);

    }

}