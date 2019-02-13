package com.example.addepalli.smaart;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

import static android.R.attr.data;
import static android.R.attr.name;


public class Balance extends Fragment {
    View view;
    String name;
    TextView tv,user;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      //  String type = "balance";
        sharedPreferences=getContext().getSharedPreferences("log_details.conf", Context.MODE_PRIVATE);


        View rootView = inflater.inflate(R.layout.balance, container, false);
        tv = (TextView) rootView.findViewById(R.id.bal);
        user = (TextView) rootView.findViewById(R.id.user);
        new getData().execute("");

        return rootView;


    }

    private class getData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            // implement API in background and store the response in current variable
            String current = "";
            String user_id=sharedPreferences.getString("user_id","");
//            Toast.makeText(getContext(),user_id,Toast.LENGTH_LONG).show();

            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    name=user_id;

                    String login_url = "https://theafricanboss.com/android/bal.php?id="+user_id;
                    url = new URL(login_url);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();

                    InputStreamReader isw = new InputStreamReader(in);


                    int data = isw.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                       System.out.print(current);

                    }
                    // return the data to onPostExecute method
                    return current;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            System.out.print("here");

            Log.d("data", s.toString());


    try {

        // JSON Parsing of data
        JSONArray jsonArray = new JSONArray(s);

        JSONObject oneObject = jsonArray.getJSONObject(0);
        JSONObject oneObject1 = jsonArray.getJSONObject(1);

        String credit = oneObject.getString("credit");
        String debit = oneObject1.getString("debit");
        //Toast.makeText(getContext(), creditc, Toast.LENGTH_SHORT).show();
        if(credit=="null" && debit=="null")
        {
            credit="0";
            debit="0";

            int qc = Integer.parseInt(credit);
            int qd = Integer.parseInt(debit);
            int qb;
            qb = qc - qd;
            // display the data in UI
            user.setText(name + "'s wallet");
            tv.setText("" + qb);
        }
      else if(credit=="null")
        {
            credit="0";

            int qc = Integer.parseInt(credit);
            int qd = Integer.parseInt(debit);
            int qb;
            qb = qc - qd;
            // display the data in UI
            user.setText(name + "'s wallet");
            tv.setText("" + qb);
        }
        else if(debit=="null")
       {
           debit="0";

           int qc = Integer.parseInt(credit);
           int qd = Integer.parseInt(debit);
           int qb;
           qb = qc - qd;
           // display the data in UI
           user.setText(name + "'s wallet");
           tv.setText("" + qb);
       }

        else  {


        int qc = Integer.parseInt(credit);
        int qd = Integer.parseInt(debit);
        int qb;
        qb = qc - qd;
        // display the data in UI
        user.setText(name + "'s wallet");
        tv.setText("" + qb);

    }


    } catch (JSONException e) {
        e.printStackTrace();
    }



        }

    }
}







/*


    public class getdata extends AsyncTask<String, String, String> {
        Context context;

        String user_name;
        AlertDialog alertDialog;

        getdata() {

        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            URL url;
            String current = "";
            HttpURLConnection urlConnection = null;


            String bal_url = "http://localhost/android/bal.php?id=1";

            try {

                url = new URL(bal_url);

                urlConnection = (HttpURLConnection) url
                        .openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader isw = new InputStreamReader(in);

                int data = isw.read();
                while (data != -1) {
                    current += (char) data;
                    data = isw.read();
                    System.out.print(current);
                }
                return current;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("data", result.toString());
            try {
                // JSON Parsing of data
                JSONArray jsonArray = new JSONArray(result);

                JSONObject oneObject = jsonArray.getJSONObject(0);
                // Pulling items from the array
                String aaa=oneObject.getString("balance");

                // display the data in UI
                tv.setText(aaa);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}



*/
