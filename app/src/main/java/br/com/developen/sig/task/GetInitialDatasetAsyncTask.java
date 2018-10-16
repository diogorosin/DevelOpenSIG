package br.com.developen.sig.task;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.developen.sig.bean.DatasetBean;
import br.com.developen.sig.exception.ExceptionBean;
import br.com.developen.sig.exception.HttpRequestException;
import br.com.developen.sig.util.Constants;
import br.com.developen.sig.util.DB;
import br.com.developen.sig.util.Messaging;
import br.com.developen.sig.util.RequestBuilder;
import br.com.developen.sig.util.Sync;

public final class GetInitialDatasetAsyncTask<A extends Activity & GetInitialDatasetAsyncTask.Listener>
        extends AsyncTask<Object, Void, Object> {

    private WeakReference<A> activity;

    private SharedPreferences preferences;

    public GetInitialDatasetAsyncTask(A activity) {

        this.activity = new WeakReference<>(activity);

        this.preferences = this.activity.get().
                getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, 0);

    }

    protected void onPreExecute(){

        A listener = this.activity.get();

        if (listener != null)

            listener.onGetInitialDatasetPreExecute();

    }

    protected Object doInBackground(Object... parameters) {

        try {

            Response response = RequestBuilder.
                    build("dataset", "initial").
                    request(MediaType.APPLICATION_JSON).
                    header(HttpHeaders.AUTHORIZATION, "Bearer " + preferences.getString(Constants.TOKEN_IDENTIFIER_PROPERTY, null)).
                    get();

            switch (response.getStatus()) {

                case HttpURLConnection.HTTP_PARTIAL:
                case HttpURLConnection.HTTP_OK:

                    DatasetBean datasetBean = response.readEntity(DatasetBean.class);

                    Activity a = activity.get();

                    if (a != null){

                        return new Sync(DB.getInstance(a)).dataset(datasetBean);

                    } else {

                        return false;

                    }

                default:

                    return response.readEntity(ExceptionBean.class);

            }

        } catch (Exception e){

            return new HttpRequestException();

        }

    }

    protected void onPostExecute(Object callResult) {

        A listener = this.activity.get();

        if (listener != null) {

            if (callResult instanceof Boolean){

                if ((Boolean) callResult)

                    listener.onGetInitialDatasetSuccess();

                else

                    listener.onGetInitialDatasetFailure(
                            new ExceptionBean("Não foi possível sincronizar os dados."));

            } else {

                if (callResult instanceof Messaging){

                    listener.onGetInitialDatasetFailure((Messaging) callResult);

                }

            }

        }

    }

    public interface Listener {

        void onGetInitialDatasetPreExecute();

        void onGetInitialDatasetSuccess();

        void onGetInitialDatasetFailure(Messaging messaging);

    }

}