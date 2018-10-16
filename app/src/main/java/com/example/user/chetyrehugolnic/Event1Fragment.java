package com.example.user.chetyrehugolnic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;


public class Event1Fragment extends Fragment {

    ListView _listview;
    MyArrayAdapter _adapter;
    static ArrayList<CEvent> sName=new ArrayList<>();
    database db=Data_Base.instance();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_event,container,false);
        _listview = (ListView)view. findViewById(R.id.list1);
        try {
            load();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        _listview.setOnItemClickListener(new ClistSelector());
        _adapter=new MyArrayAdapter(getActivity(),sName);
        _listview.setAdapter(_adapter);

        return view;
    }
    public void load() throws JSONException {
//      sName.add(new CEvent("Василий","12131812","4677","ккк","Сэр Гей",455));
//      sName.add(new CEvent("Григорий","121367812","ьть","Петров","длоррп",45));
        //sName=db.getEventdata();

        Exchanger<ArrayList<CEvent>>exchanger=new Exchanger<>();
        new MyThread.TGetEvent(exchanger).start();
        try {
            sName=exchanger.exchange(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private class ClistSelector implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(getActivity(),Sign_up.class) ;

            intent.putExtra("POSITION_KEY",position);
            startActivity(intent);
        }


    }
}
