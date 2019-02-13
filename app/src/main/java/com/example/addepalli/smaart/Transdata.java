package com.example.addepalli.smaart;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Transdata {
    String trans_id;
    String acc_no;
    String reg_no;
    String date;
    String time;
    String purpose;
    String money;
    protected Transdata(String s, String s1, String s2)
    {
        this.time=s;
        this.date=s1;
        this.money=s2;
    }
    public Transdata(String trans_id,String acc_no,String reg_no,String date,String time,String purpose,String money)
    {
        this.trans_id = trans_id;
        this.acc_no =acc_no;
        this.reg_no =reg_no;
        this.date =date;
        this.time = time;
        this.purpose=purpose;
        this.money=money;
    }


    public Transdata(String s) throws JSONException {
        JSONArray jsonArray = new JSONArray(s);

        JSONObject oneObject = jsonArray.getJSONObject(0);
        for (int i=0;i<jsonArray.length();i++) {
            this.trans_id = oneObject.getString("trans_id");
            this.acc_no = oneObject.getString("acc_no");
            this.reg_no = oneObject.getString("reg_no");
            this.date = oneObject.getString("date");
            this.time = oneObject.getString("time");
            this.purpose = oneObject.getString("purose");
            this.money = oneObject.getString("money");
        }
    }

    public Transdata() {

    }

    public String getDate()
    {

        return date;
    }
    public void setDate(String date)
    {

        this.date=date;
    }
    public void setTime(String time)
    {

        this.time=time;
    }
    public void setMoney(String money)
    {

        this.money=money;
    }
    public String getTime()
    {
        return time;
    }
    public String getMoney()
    {
        return money;
    }

}
