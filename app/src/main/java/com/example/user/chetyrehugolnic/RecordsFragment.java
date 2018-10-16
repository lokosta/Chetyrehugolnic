package com.example.user.chetyrehugolnic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Exchanger;


public class RecordsFragment extends Fragment {

    static CUsers users;

    ListView _listview;
    MyAdapter1 _adapter;
    ArrayList<CRandom> sRandom=new ArrayList<>();
    database db=Data_Base.instance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_records,container,false);
        _listview=(ListView)view.findViewById(R.id.list1);
        try {
            lead();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        MyAdapter1.quickSort(sRandom,0,sRandom.size());
        _adapter=new MyAdapter1(getActivity(),sRandom);
        _listview.setAdapter(_adapter);

        return view;
    }
    public void lead() throws JSONException {
        Exchanger<ArrayList<CRandom>>exchanger=new Exchanger<>();
        new MyThread.TGetRandom(exchanger,login.userx.getUser_ID()).start();
        try {
            sRandom=exchanger.exchange(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
