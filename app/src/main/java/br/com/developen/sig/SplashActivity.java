package br.com.developen.sig;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import br.com.developen.sig.util.Constants;


public class SplashActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Handler handle = new Handler();

        handle.postDelayed(new Runnable() {

            public void run() {

                Intent intent;

                SharedPreferences preferences = getSharedPreferences(
                        Constants.SHARED_PREFERENCES_NAME, 0);

                if (preferences.getBoolean(Constants.DEVICE_CONFIGURED_PROPERTY, false)) {

                    if (preferences.getInt(Constants.USER_IDENTIFIER_PROPERTY, 0) == 0) {

                        intent = new Intent(SplashActivity.this, LoginActivity.class);

                    } else {

                        intent = new Intent(SplashActivity.this, MapActivity.class);

                    }

                } else {

                    intent = new Intent(SplashActivity.this, AccountActivity.class);

                }

                startActivity(intent);

                finish();

            }

        }, 2000);

    }

}