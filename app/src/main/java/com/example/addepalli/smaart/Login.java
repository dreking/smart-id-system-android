package com.example.addepalli.smaart;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static android.Manifest.permission.SEND_SMS;


public class Login extends Activity {
    EditText username, pass;
    TextView forgotp;
    String dusername, dpass;
    Context context;
    Button login, register;
    ProgressBar progressBar;
    int t = 0, c = 0;

    protected void onCreate(Bundle savedInstancstate) {


        checkConnection();
        super.onCreate(savedInstancstate);

        username = (EditText) findViewById(R.id.username);
        forgotp = (TextView) findViewById(R.id.forgotp);
        pass = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);


        if (username.getText().toString().trim().equals("")) {
            username.setError("enter user name!");
        }
        if (pass.getText().toString().trim().equals("")) {
            pass.setError("enter password!");
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Login.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }

        if (t == 1) {
            login.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    Saveinfo(v);

                }
            });

            forgotp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), Forgotpass.class);
                    startActivity(intent);

                }
            });
        }

    }

    public void Saveinfo(View view) {

        String type = "login";
        dusername = username.getText().toString().trim();
        dpass = pass.getText().toString().trim();
                 Backgroundworker backgroundworker=new Backgroundworker(this);
        backgroundworker.execute(type,dusername,dpass);


    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void checkConnection() {
        if (isOnline()) {
            Toast.makeText(getApplicationContext(), "You are connected to Internet", Toast.LENGTH_SHORT).show();
            t = 1;
            setContentView(R.layout.login);
        } else {
            //login.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "Please check your internet connection !!", Toast.LENGTH_SHORT).show();
            t = 0;
            setContentView(R.layout.nointern);
        }
    }
}
