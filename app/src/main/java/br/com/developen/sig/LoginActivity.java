package br.com.developen.sig;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.developen.sig.bean.CredentialBean;
import br.com.developen.sig.bean.TokenBean;
import br.com.developen.sig.task.AuthenticateAsyncTask;
import br.com.developen.sig.util.Constants;
import br.com.developen.sig.util.Messaging;

public class LoginActivity extends AppCompatActivity
        implements AuthenticateAsyncTask.Listener{


    private EditText governmentEditText;

    private EditText loginEditText;

    private EditText passwordEditText;

    private View loginFormView;

    private View progressView;

    private SharedPreferences preferences;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        governmentEditText = findViewById(R.id.activity_login_company_edittext);

        loginEditText = findViewById(R.id.activity_login_login_edittext);

        passwordEditText = findViewById(R.id.activity_login_password_edittext);

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {

                boolean handled = false;

                if (id == EditorInfo.IME_ACTION_GO) {

                    InputMethodManager imm = (InputMethodManager) textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                    assert imm != null;

                    imm.hideSoftInputFromWindow(textView.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

                    validateFieldsAndAttemptLogin();

                    handled = true;

                }

                return handled;

            }

        });

        Button signInButton = findViewById(R.id.activity_login_sign_in_button);

        signInButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                validateFieldsAndAttemptLogin();

            }

        });

        loginFormView = findViewById(R.id.activity_login_form);

        progressView = findViewById(R.id.activity_login_progress);

        preferences = getSharedPreferences(
                Constants.SHARED_PREFERENCES_NAME, 0);

        governmentEditText.setText(preferences.getString(Constants.GOVERNMENT_DENOMINATION_PROPERTY,""));

    }


    private void validateFieldsAndAttemptLogin() {

        loginEditText.setError(null);

        passwordEditText.setError(null);

        String login = loginEditText.getText().toString();

        String password = passwordEditText.getText().toString();

        boolean cancel = false;

        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {

            passwordEditText.setError(getString(R.string.error_invalid_password));

            focusView = passwordEditText;

            cancel = true;

        }

        if (TextUtils.isEmpty(login)) {

            loginEditText.setError(getString(R.string.error_field_required));

            focusView = loginEditText;

            cancel = true;

        } else if (!isEmailValid(login)) {

            loginEditText.setError(getString(R.string.error_invalid_email));

            focusView = loginEditText;

            cancel = true;

        }

        if (cancel) {

            focusView.requestFocus();

        } else {

            CredentialBean credentialBean = new CredentialBean();

            credentialBean.setLogin(login);

            credentialBean.setPassword(password);

            credentialBean.setGovernment(preferences.getInt(Constants.GOVERNMENT_IDENTIFIER_PROPERTY, 0));

            new AuthenticateAsyncTask<>(this).execute(credentialBean);

        }

    }


    public void onAuthenticatePreExecute() {

        showProgress(true);

    }


    public void onAuthenticateSuccess(TokenBean tokenBean) {

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(Constants.TOKEN_IDENTIFIER_PROPERTY, tokenBean.getIdentifier());

        editor.putInt(Constants.TOKEN_LEVEL_PROPERTY, tokenBean.getLevel());

        editor.putInt(Constants.GOVERNMENT_IDENTIFIER_PROPERTY, tokenBean.getGovernment().getIdentifier());

        editor.putString(Constants.GOVERNMENT_DENOMINATION_PROPERTY, tokenBean.getGovernment().getDenomination());

        editor.putString(Constants.GOVERNMENT_FANCYNAME_PROPERTY, tokenBean.getGovernment().getFancyName());

        editor.putInt(Constants.USER_IDENTIFIER_PROPERTY, tokenBean.getUser().getIdentifier());

        editor.putString(Constants.USER_NAME_PROPERTY, tokenBean.getUser().getName());

        editor.putString(Constants.USER_LOGIN_PROPERTY, tokenBean.getUser().getLogin());

        editor.apply();

        Intent intent = new Intent(LoginActivity.this, MapActivity.class);

        startActivity(intent);

        finish();

    }


    public void onAuthenticateFailure(Messaging messaging) {

        showProgress(false);

        AlertDialog.Builder builder = new AlertDialog.Builder(loginFormView.getContext());

        builder.setMessage(TextUtils.join("\n", messaging.getMessages()));

        builder.setTitle(R.string.dlg_title_login_failure);

        builder.setPositiveButton(R.string.button_try_again,

                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();

                    }

                });

        AlertDialog alert = builder.create();

        alert.show();

    }


    private boolean isEmailValid(String email) {

        return email.contains("@");

    }


    private boolean isPasswordValid(String password) {

        return password.length() > 4;

    }


    private void showProgress(final boolean show) {

        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);

        loginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animation) {

                loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);

            }

        });

        progressView.setVisibility(show ? View.VISIBLE : View.GONE);

        progressView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animation) {

                progressView.setVisibility(show ? View.VISIBLE : View.GONE);

            }

        });

    }


}