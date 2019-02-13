package com.example.addepalli.smaart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by addepalli on 19-11-2017.
 */

public class Document extends Fragment {
    View view;
    Documentlist list2;
    ListView listViewd;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.debitlist,container,false);
        listViewd=(ListView)view.findViewById(R.id.doclistview);
        list2=new Documentlist(Document.this);
        listViewd.setAdapter(list2);
        return view;

    }
}