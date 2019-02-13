package com.example.addepalli.smaart;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by addepalli on 19-11-2017.
 */

public class Transactionlist extends BaseAdapter {
    Transaction activity;
    Transactionlist(Transaction activity)
    {
        this.activity=activity;
    }
    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public Transactionlist() {
        super();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view=activity.getActivity().getLayoutInflater().inflate(R.layout.transaction,null);
        return view;
    }
}

/*

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

*/
/**
 * Created by addepalli on 19-11-2017.
 *//*


public class Transactionlist extends BaseAdapter {
    //Transaction activity;
    Context context;
    LayoutInflater inflater;
    String datel[],timel[],moneyl[];
    List<Creditdata> rowitems;
    Transactionlist(Context context, String[] date,String[] time,String[] money)
    {
        this.context=context;
        this.datel=date;
        this.timel=time;
        this.moneyl=money;
        inflater=(LayoutInflater.from(context));

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

      view =inflater.inflate(R.layout.transaction,null);

        TextView date = (TextView) view.findViewById(R.id.date);
        TextView time = (TextView) view.findViewById(R.id.time);
        TextView money = (TextView) view.findViewById(R.id.money);
        System.out.print(datel);
        String dtmp;
        dtmp=datel[i];
        date.setText(dtmp);
        time.setText(timel[i]);
        money.setText(moneyl[i]);

    return view;}

    @Override
    public int getCount() {
        return datel.length;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}

*/
