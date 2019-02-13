package com.example.addepalli.smaart;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.addepalli.smaart.Balance;
import com.example.addepalli.smaart.MainActivity;

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

public class Back extends AsyncTask<String,String,String> {
    Context context;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String user_name;


    Back (Context ctx) {

        this.context = ctx;
        sharedPreferences=context.getSharedPreferences("log_details.conf", Context.MODE_PRIVATE);
    }
    Balance container;
    @Override
    protected String doInBackground(String... params) {
        String current = "";
        try {
            user_name = params[0];
            String password = params[1];
            URL url;
            HttpURLConnection urlConnection = null;
            try {

                String login_url = "https://theafricanboss.com/android/forgot.php?phone=" + user_name + "&password=" + password;
                url = new URL(login_url);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader isw = new InputStreamReader(in);


                int data = isw.read();
                while (data != -1) {
                    current += (char) data;
                    data = isw.read();
                    // System.out.print(current);

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
        return null;
    }





    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("TRUE")) {


Toast.makeText(context,"success",Toast.LENGTH_LONG).show();

        }
        else if(result.equals("FALSE"))
        {
            Toast.makeText(context,"oops something went wrong!!",Toast.LENGTH_SHORT).show();

        }
        else{
            /*
            Toast.makeText(context,"Opps! Something Went Wrong!!!!",Toast.LENGTH_SHORT).show();
*/
        }
    }

}
