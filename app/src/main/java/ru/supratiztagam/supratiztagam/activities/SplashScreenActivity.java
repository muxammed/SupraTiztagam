package ru.supratiztagam.supratiztagam.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import ru.supratiztagam.supratiztagam.DatabaseHandler;
import ru.supratiztagam.supratiztagam.R;

/**
 * Created by muxammed on 26.01.2017.
 */
public class SplashScreenActivity extends AppCompatActivity {


    DatabaseHandler db;


    ProgressBar mProgress;

    int oldumy = 0;
    int usany;
    Boolean inet;
    TextView noinettxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        mProgress = (ProgressBar) findViewById(R.id.mProgress);
        noinettxt = (TextView) findViewById(R.id.noinettxt);

        db = new DatabaseHandler(this);
        int sany = db.getUsersCount();
        Toast.makeText(this, "Icerki Ulanyjy sany " + sany, Toast.LENGTH_LONG).show();


        inet  = isNetworkAvailable();
        if (inet) {
            //Toast.makeText(this, "Inet bar", Toast.LENGTH_LONG).show();
            noinettxt.setVisibility(View.GONE);
        }
        else {
            //Toast.makeText(this, "Inet yok", Toast.LENGTH_LONG).show();
            noinettxt.setVisibility(View.VISIBLE);
        }

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent mintent = new Intent(getApplicationContext(), MainActivity.class);
                                          SplashScreenActivity.this.finish();
                                          startActivity(mintent);

                                      }
                                  },
                3000);


    }



    @Override
    protected void onResume() {
        super.onResume();
        inet  = isNetworkAvailable();
        if (inet) {
            //Toast.makeText(this, "Inet bar", Toast.LENGTH_LONG).show();
            noinettxt.setVisibility(View.GONE);
        }
        else {
            //Toast.makeText(this, "Inet yok", Toast.LENGTH_LONG).show();
            noinettxt.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        inet  = isNetworkAvailable();
        if (inet) {
            //Toast.makeText(this, "Inet bar", Toast.LENGTH_LONG).show();
            noinettxt.setVisibility(View.GONE);
        }
        else {
            //Toast.makeText(this, "Inet yok", Toast.LENGTH_LONG).show();
            noinettxt.setVisibility(View.VISIBLE);
        }


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                inet  = isNetworkAvailable();
                if (inet) {
                    //Toast.makeText(getApplicationContext(), "Inet bar", Toast.LENGTH_LONG).show();
                    noinettxt.setVisibility(View.GONE);
                }
                else {
                    //Toast.makeText(getApplicationContext(), "Inet yok", Toast.LENGTH_LONG).show();
                    noinettxt.setVisibility(View.VISIBLE);
                }
            }
        }, 3000);

        super.onWindowFocusChanged(hasFocus);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        System.out.println("GOR HANY " + connectivityManager.toString());
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
