package com.example.addepalli.smaart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by addepalli on 06-03-2018.
 */

public class Creditdata  {
    String trans_id;
    String acc_no;
    String reg_no;
    String date;
    String time;
    String purpose;
    String money;
    protected Creditdata()
    {
        super();
    }
    public Creditdata(String time,String money,String date){


        this.date =date;
        this.time = time;
        this.money=money;
    }


    public Creditdata(String s) throws JSONException {
        JSONArray jsonArray = new JSONArray(s);

        JSONObject oneObject = jsonArray.getJSONObject(0);
        this.trans_id = oneObject.getString("trans_id");
        this.acc_no = oneObject.getString("acc_no");
        this.money = oneObject.getString("amount");
        this.date = oneObject.getString("date");
        this.time = oneObject.getString("time");




    }

    public String getDate()
    {

        return this.date;
    }
    public String getTime()
    {
        return this.time;
    }
    public String getMoney()
    {
        return this.money;
    }

}
