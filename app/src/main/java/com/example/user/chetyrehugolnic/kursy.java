package com.example.user.chetyrehugolnic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

public class kursy extends AppCompatActivity {

    Myadapter adapter;
    ListView lv;
    int o=0;
    //ArrayList<CPerson> _values=new ArrayList<>();
    ArrayList <CCourse> _valuesx=new ArrayList<>();
    //_DataBase sq=_DataBase.instance();
    CCourse cu;//=new CUsers();
    database db=Data_Base.instance();
    int i;
    Listener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kursy);
        Intent intent=getIntent();
        int intdata=intent.getIntExtra(CourseFragment.number,0);
        lv=(ListView)findViewById(R.id.lv);
        try {
            loadResources();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Listener lstnr=new Listener(this,Opisanie.class);
        lv.setOnItemClickListener(lstnr);
    }

    public void loadResources() throws JSONException {

        Exchanger<ArrayList<CCourse>>exchanger=new Exchanger<>();
        new MyThread.TGetCourse(exchanger).start();
        try {
            _valuesx=exchanger.exchange(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//            _valuesx=db.getCoursedata();

        //for(Ccourse c:sq){
        //sq=db.getCoursedata("Course_id",String.valueOf(i));
        //cu=db.getUsersdata("User_id",String.valueOf(sq.User_id));
        //_values.add(new CPerson(cu.first_name+cu.last_name,sq.Title,"sql"));//}
//        _values.add(new CPerson("Procopenco","gasmyas","sqs"));
//        _values.add(new CPerson("Gatalskiy","gasmyas","sqs"));
//        _values.add(new CPerson("Lunin","gasmyas","sqs"));
//        _values.add(new CPerson("Abelardo","gasmyas","sqs"));
//        _values.add(new CPerson("Dusmuhametov","gasmyas","sqs"));
//        _values.add(new CPerson("          ","gasmyas","sqs"));
//        _values.add(new CPerson("Procopenco","gasmyas","sqs"));
//        _values.add(new CPerson("Procopenco","gasmyas","sqs"));
        adapter=new Myadapter(this,_valuesx);
        lv.setAdapter(adapter);
    }
//---------------------------------------------------------------
   /* public void key(){
        Intent intent=new Intent(this,Opisanie.class);
        intent.putExtra(Listener.INT_MASSAGE_INDEX,listener.key);
        startActivity(intent);
    }*/

}
