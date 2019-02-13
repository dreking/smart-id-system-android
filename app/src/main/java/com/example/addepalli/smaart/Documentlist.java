package com.example.addepalli.smaart;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by addepalli on 19-11-2017.
 */

public class Documentlist extends BaseAdapter {
    Document activity;
    Documentlist(Document activity)
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

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view=activity.getActivity().getLayoutInflater().inflate(R.layout.document,null);
        return view;
    }
}