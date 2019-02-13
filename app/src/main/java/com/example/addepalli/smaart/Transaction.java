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


public class Transaction extends Fragment {
    View view;
    String name;
    TextView tv,user;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String type = "balance";

        sharedPreferences=getContext().getSharedPreferences("log_details.conf", Context.MODE_PRIVATE);


        View rootView = inflater.inflate(R.layout.retrivecredit, container, false);
        tv = (TextView) rootView.findViewById(R.id.ctxt);

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

            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    name=user_id;
                    String login_url = "https://theafricanboss.com/android/credit.php?id="+user_id;
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

            Log.d("data", s.toString());
            // tv.setText(s);

            try {

                // JSON Parsing of data
                JSONArray jsonArray = new JSONArray(s);


                // Pulling items from the array
                String purpose=null,time=null,money = null,string="",trans_id=null,date=null;
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject oneObject = jsonArray.getJSONObject(i);
                    trans_id=oneObject.getString("trans_id");
                    date=oneObject.getString("date");
                    time = oneObject.getString("time");
                    money = oneObject.getString("amount");
                    string+=("\n TRANS ID: "+trans_id+"  DATE & TIME: "+date+" & "+time+"   AMOUNT: "+money+"\n");

                }
                //Toast.makeText(getActivity(),string,Toast.LENGTH_SHORT).show();
                tv.setText(""+ string);




            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}

