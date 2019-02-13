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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by addepalli on 14-05-2018.
 */

public class Attendance extends Fragment {
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


        View rootView = inflater.inflate(R.layout.attendance, container, false);
        tv = (TextView) rootView.findViewById(R.id.attd);

        new Attendance.getData().execute("");

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
                    String login_url = "https://theafricanboss.com/android/attendance.php?id="+user_id;
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

if(s!=null) {
    try {

        // JSON Parsing of data
        JSONArray jsonArray = new JSONArray(s);


        // Pulling items from the array
        String date = null, status = null, money = null, string = "", reg_no = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject oneObject = jsonArray.getJSONObject(i);
            reg_no = oneObject.getString("reg_no");
            date = oneObject.getString("date");
            status = oneObject.getString("status");
            string += ("\n REG NO: " + reg_no + "  DATE : " + date + "   STATUS: " + status + "\n");

        }
        //Toast.makeText(getActivity(),string,Toast.LENGTH_SHORT).show();
        tv.setText("" + string);


    } catch (JSONException e) {
        e.printStackTrace();
    }
}

else if(s==null) {
    s="0";
    tv.setText("YOU ARE NOT A HOSTALITE");

}


        }

    }
}
