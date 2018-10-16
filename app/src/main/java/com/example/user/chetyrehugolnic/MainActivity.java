package com.example.user.chetyrehugolnic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    ListView _listview;
    MyArrayAdapter _adapter;
    static ArrayList<CEvent> sName=new ArrayList<>();
    database db=Data_Base.instance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _listview = (ListView) findViewById(R.id.list1);
        try {
            load();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        _listview.setOnItemClickListener(new ClistSelector());
        _adapter=new MyArrayAdapter(this,sName);
        _listview.setAdapter(_adapter);
    }
    public void load() throws JSONException {
//      sName.add(new CEvent("Василий","12131812","4677","ккк","Сэр Гей",455));
//      sName.add(new CEvent("Григорий","121367812","ьть","Петров","длоррп",45));
        sName=db.getEventdata();

    }

   private class ClistSelector implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           Intent intent=new Intent(MainActivity.this,Sign_up.class) ;

            intent.putExtra("POSITION_KEY",position);
            startActivity(intent);
        }


    }

}





