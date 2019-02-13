package com.example.addepalli.smaart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Belal on 9/5/2017.
 */

public class ListViewAdapter extends ArrayAdapter<Transdata> {

    //the hero list that will be displayed
    private List<Transdata> heroList;

    //the context object
    private Context mCtx;

    //here we are getting the herolist and context
    //so while creating the object of this adapter class we need to give herolist and context
    public ListViewAdapter(List<Transdata> heroList, Context mCtx) {
        super(mCtx, R.layout.debited, heroList);
        this.heroList = heroList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.debited, null, true);

        //getting text views
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.time);
        TextView textViewImageUrl = (TextView) listViewItem.findViewById(R.id.date);
        TextView mone = (TextView) listViewItem.findViewById(R.id.money);

        //Getting the hero for the specified position
        Transdata hero = heroList.get(position);

        //setting hero values to textviews
        textViewName.setText(hero.getTime());
        textViewImageUrl.setText(hero.getDate());
        mone.setText(hero.getMoney());

        //returning the listitem
        return listViewItem;
    }
}