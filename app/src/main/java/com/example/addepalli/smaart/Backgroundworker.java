package com.example.addepalli.smaart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Backgroundworker extends AsyncTask<String,String,String> {
    Context context;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String user_name;
    AlertDialog alertDialog;

    Backgroundworker (Context ctx) {

        this.context = ctx;
        sharedPreferences=context.getSharedPreferences("log_details.conf", Context.MODE_PRIVATE);
    }
    Balance container;

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String current = "";

        if (type.equals("login")) {
            URL url;
            HttpURLConnection urlConnection = null;


            try {
                user_name = params[1];
                String password = params[2];
                try {

                    String login_url = "https://theafricanboss.com/android/login.php?username=" + user_name + "&pass=" + password;
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
        }
            return current;
        }


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {

if(result==null)
{
    result="0";
}
else
{
    result=result+"";
}
        if(result.equals("TRUE")) {

            editor=sharedPreferences.edit();
            editor.clear();
            editor.apply();
            editor.putString("user_id",user_name);
            editor.apply();
            System.out.println(result);
            Intent intent = new Intent(context, MainActivity.class);
            //intent.putExtra("user", user_name);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);

        }
        else if(result.equals("FALSE"))
        {
            Toast.makeText(context,"Invalid Credentials!!",Toast.LENGTH_SHORT).show();

        }
        else if(result.equals("0"))
        {
            Toast.makeText(context,"Invalid Credentials!!",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(context,"Opps! Something Went Wrong!!!!",Toast.LENGTH_SHORT).show();

        }
    }

}
