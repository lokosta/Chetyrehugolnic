package com.example.user.chetyrehugolnic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;

public class Records extends AppCompatActivity {
    static CUsers users;

    ListView _listview;
    MyAdapter1 _adapter;
    ArrayList<CRandom> sRandom=new ArrayList<>();
    database db=Data_Base.instance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        _listview=(ListView)findViewById(R.id.list1);
        MyAdapter1.quickSort(sRandom,0,sRandom.size());
        _adapter=new MyAdapter1(this,sRandom);
        _listview.setAdapter(_adapter);


    }
    public void lead() throws JSONException {
       sRandom= db.getRandomdata(users.user_ID);
    }

}
