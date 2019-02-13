package com.example.addepalli.smaart;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by addepalli on 18-11-2017.
 */

public class Profile extends Fragment {
    String first,last,gen,reg,contact,emai;
    TextView fname,lname,gender,reg_no,phone,email;


    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        sharedPreferences=getContext().getSharedPreferences("log_details.conf", Context.MODE_PRIVATE);
        View rootView = inflater.inflate(R.layout.profile, container, false);
        fname = (TextView) rootView.findViewById(R.id.fname);
        lname = (TextView) rootView.findViewById(R.id.lname);
        gender = (TextView) rootView.findViewById(R.id.gender);
      //  reg_no = (TextView) rootView.findViewById(R.id.reg_no);
        phone = (TextView) rootView.findViewById(R.id.phone);
        email = (TextView) rootView.findViewById(R.id.email);
        new getData1().execute("");

        return rootView;
    }

    private class getData1 extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            // implement API in background and store the response in current variable
            String current = "";
            String user_id= sharedPreferences.getString("user_id","");

            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    String login_url = "https://theafricanboss.com/android/profile.php?id="+user_id;
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
            try {
                //Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                // JSON Parsing of data
                JSONArray jsonArray = new JSONArray(s);

                JSONObject oneObject = jsonArray.getJSONObject(0);

                // Pulling items from the array
                first = oneObject.getString("fname");
                last = oneObject.getString("lname");
                gen = oneObject.getString("gender");
               // reg = oneObject.getString("usn");
                contact = oneObject.getString("phone");
                emai = oneObject.getString("email");

                fname.setText(first);
                lname.setText(last);
                gender.setText(gen);
               // reg_no.setText(reg);
                phone.setText(contact);
                email.setText(emai);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}

