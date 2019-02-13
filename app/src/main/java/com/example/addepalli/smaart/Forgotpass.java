package com.example.addepalli.smaart;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import java.util.jar.Manifest;

/**
 * Created by addepalli on 19-04-2018.
 */

public class Forgotpass extends Activity {
    EditText num,ndpass,odpass,otp;
    String dnum,opass,npass;
    Button paso,verify;

    String sent="sent",delivered="sms_delivered";

     protected void onCreate(Bundle savedInstancstate) {
        super.onCreate(savedInstancstate);
        setContentView(R.layout.forgotpass);
        num = (EditText) findViewById(R.id.phoneno);
        ndpass = (EditText) findViewById(R.id.npass);
        odpass = (EditText) findViewById(R.id.opass);
        otp = (EditText) findViewById(R.id.otp);
         verify = (Button) findViewById(R.id.verify);
         paso = (Button) findViewById(R.id.sendpass);
        final long nuqm= new Random().nextInt(1000-1+1)+99999;
         //Toast.makeText(getApplicationContext(),String.valueOf(nuqm),Toast.LENGTH_LONG).show();
        paso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager sms=SmsManager.getDefault();
                String number=num.getText().toString();

                number+=" is OTP please dont share with any one !!!!";
                System.out.println(number);
                sms.sendTextMessage(number,null,String.valueOf(nuqm),null,null);

            }
        });


      verify.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String num1,num2,ran=Long.toString(nuqm),otpc;

            num1=odpass.getText().toString();
            num2=ndpass.getText().toString();
            otpc=otp.getText().toString().trim();


            if(num1.equals(num2) && otpc.equals(ran))
            {
                dnum=num.getText().toString();
                Toast.makeText(getApplicationContext(),dnum,Toast.LENGTH_LONG).show();
                opass=odpass.getText().toString();
                Back back=new Back(getApplicationContext());
                back.execute(dnum,opass);
                Toast.makeText(getApplicationContext(),"password has been reset",Toast.LENGTH_LONG).show();

            }
            else if(!num1.equals(num2)  && otpc.equals(ran))
            {
                Toast.makeText(getApplicationContext(),"enter same password",Toast.LENGTH_LONG).show();

            }
            else if(num1.equals(num2) && !otpc.equals(ran))
            {
                Toast.makeText(getApplicationContext(),"enter proper OTP",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"something went wrong" ,Toast.LENGTH_LONG).show();

            }
        }
    });


}

}


