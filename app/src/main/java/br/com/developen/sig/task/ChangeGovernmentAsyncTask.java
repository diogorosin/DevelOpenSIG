package br.com.developen.sig.task;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.developen.sig.bean.IntegerBean;
import br.com.developen.sig.bean.TokenBean;
import br.com.developen.sig.exception.ExceptionBean;
import br.com.developen.sig.exception.HttpRequestException;
import br.com.developen.sig.util.Constants;
import br.com.developen.sig.util.Messaging;
import br.com.developen.sig.util.RequestBuilder;

public final class ChangeGovernmentAsyncTask<A extends Activity & ChangeGovernmentAsyncTask.Listener>
        extends AsyncTask<Integer, Void, Object> {

    private WeakReference<A> activity;

    private SharedPreferences preferences;

    public ChangeGovernmentAsyncTask(A activity) {

        this.activity = new WeakReference<>(activity);

        this.preferences = this.activity.get().
                getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, 0);

    }

    protected void onPreExecute(){

        A listener = this.activity.get();

        if (listener != null)

            listener.onChangeGovernmentPreExecute();

    }

    protected Object doInBackground(Integer... parameters) {

        try {

            Response response = RequestBuilder.
                    build("account", "government").
                    request(MediaType.APPLICATION_JSON).
                    header(HttpHeaders.AUTHORIZATION, "Bearer " + preferences.getString(Constants.TOKEN_IDENTIFIER_PROPERTY, null)).
                    post(Entity.entity(new IntegerBean(parameters[0]), MediaType.APPLICATION_JSON));

            switch (response.getStatus()) {

                case HttpURLConnection.HTTP_PARTIAL:
                case HttpURLConnection.HTTP_OK:

                    return response.readEntity(TokenBean.class);

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

            if (callResult instanceof TokenBean){

                listener.onChangeGovernmentSuccess((TokenBean) callResult);

            } else {

                if (callResult instanceof Messaging){

                    listener.onChangeGovernmentFailure((Messaging) callResult);

                }

            }

        }

    }

    public interface Listener {

        void onChangeGovernmentPreExecute();

        void onChangeGovernmentSuccess(TokenBean tokenBean);

        void onChangeGovernmentFailure(Messaging messaging);

    }

}