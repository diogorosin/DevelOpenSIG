package br.com.developen.sig.task;

import android.app.Activity;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.developen.sig.bean.CredentialBean;
import br.com.developen.sig.bean.TokenBean;
import br.com.developen.sig.exception.ExceptionBean;
import br.com.developen.sig.exception.HttpRequestException;
import br.com.developen.sig.util.Messaging;
import br.com.developen.sig.util.RequestBuilder;

public final class AuthenticateAsyncTask<
        A extends Activity & AuthenticateAsyncTask.Listener,
        B extends CredentialBean,
        C extends Void,
        D> extends AsyncTask<B, C, D> {

    private WeakReference<A> activity;

    public AuthenticateAsyncTask(A activity) {

        this.activity = new WeakReference<>(activity);

    }

    protected void onPreExecute(){

        A listener = this.activity.get();

        if (listener != null)

            listener.onAuthenticatePreExecute();

    }

    protected Object doInBackground(CredentialBean... parameters) {

        CredentialBean credentialBean = parameters[0];

        try {

            Response response = RequestBuilder.
                    build("account", "authenticate").
                    request(MediaType.APPLICATION_JSON).
                    post(Entity.entity(credentialBean, MediaType.APPLICATION_JSON));

            switch (response.getStatus()) {

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

                listener.onAuthenticateSuccess((TokenBean) callResult);

            } else {

                if (callResult instanceof Messaging){

                    listener.onAuthenticateFailure((Messaging) callResult);

                }

            }

        }

    }

    public interface Listener {

        void onAuthenticatePreExecute();

        void onAuthenticateSuccess(TokenBean tokenBean);

        void onAuthenticateFailure(Messaging messaging);

    }

}