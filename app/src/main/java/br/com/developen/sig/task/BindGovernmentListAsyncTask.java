package br.com.developen.sig.task;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.developen.sig.bean.GovernmentBean;
import br.com.developen.sig.exception.ExceptionBean;
import br.com.developen.sig.exception.HttpRequestException;
import br.com.developen.sig.util.Constants;
import br.com.developen.sig.util.Messaging;
import br.com.developen.sig.util.RequestBuilder;

public final class BindGovernmentListAsyncTask<
        A extends Activity & BindGovernmentListAsyncTask.Listener,
        B extends Spinner,
        C extends ArrayAdapter<GovernmentBean>>
        extends AsyncTask<Object, Void, Object> {

    private WeakReference<A> activity;

    private WeakReference<B> governmentSpinner;

    private WeakReference<C> governmentArrayAdapter;

    private SharedPreferences preferences;

    public BindGovernmentListAsyncTask(A activity, B governmentSpinner, C governmentArrayAdapter) {

        this.activity = new WeakReference<>(activity);

        this.governmentSpinner = new WeakReference<>(governmentSpinner);

        this.governmentArrayAdapter = new WeakReference<>(governmentArrayAdapter);

        this.preferences = this.activity.get().
                getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, 0);

    }

    protected void onPreExecute(){

        A listener = this.activity.get();

        if (listener != null)

            listener.onBindGovernmentListPreExecute();

    }

    protected Object doInBackground(Object... parameters) {

        try {

            Response response = RequestBuilder.
                    build("account", "government").
                    request(MediaType.APPLICATION_JSON).
                    header(HttpHeaders.AUTHORIZATION, "Bearer " + preferences.getString(Constants.TOKEN_IDENTIFIER_PROPERTY, null)).
                    get();

            switch (response.getStatus()) {

                case HttpURLConnection.HTTP_PARTIAL:
                case HttpURLConnection.HTTP_OK:

                    return response.readEntity(new GenericType<List<GovernmentBean>>(){});

                default:

                    return response.readEntity(ExceptionBean.class);

            }

        } catch (Exception e){

            return new HttpRequestException();

        }

    }

    protected void onPostExecute(Object callResult) {

        if (activity.get() == null ||
                governmentSpinner.get() == null ||
                governmentArrayAdapter.get() == null)

            return;

        if (callResult == null)

            return;

        if (callResult instanceof List<?>) {

            List<GovernmentBean> governmentBeanList = (List<GovernmentBean>) callResult;

            governmentArrayAdapter.get().clear();

            for (GovernmentBean governmentBean : governmentBeanList)

                governmentArrayAdapter.get().add(governmentBean);

            int selected = preferences.getInt(Constants.GOVERNMENT_IDENTIFIER_PROPERTY,-1);

            if (selected != -1){

                GovernmentBean governmentBean = new GovernmentBean();

                governmentBean.setIdentifier(selected);

                int position = governmentArrayAdapter.get().getPosition(governmentBean);

                governmentSpinner.get().setSelection(position, false);

            }

            activity.get().onBindGovernmentListSuccess();

        } else {

            if (callResult instanceof Messaging) {

                activity.get().onBindGovernmentListFailure((Messaging) callResult);

            }

        }

    }

    public interface Listener {

        void onBindGovernmentListPreExecute();

        void onBindGovernmentListSuccess();

        void onBindGovernmentListFailure(Messaging messaging);

    }

}