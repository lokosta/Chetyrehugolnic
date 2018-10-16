package com.example.user.chetyrehugolnic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

public class Opisanie extends AppCompatActivity {

    TextView ed1,ed2;
    TextView tv,tv2;
    Button button;
    CCourse cc= null;//=new CCourse();//*берём данные из базы данных
    ArrayList<CCourse> _values=new ArrayList<>();
    database db=Data_Base.instance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opisanie);
        Intent intent =getIntent();
        int intdata=intent.getIntExtra(Listener.INT_MASSAGE_INDEX,0);
        load();
        cc=_values.get(intdata);
        ed1=(TextView)findViewById(R.id.editText);
        ed2=(TextView)findViewById(R.id.editText2);
        tv=(TextView)findViewById(R.id.textView4);
        tv2=(TextView)findViewById(R.id.textView6);
        button=(Button)findViewById(R.id.button);

            if(cc.isZapis())
            button.setText("отписаться");
        else button.setText("записаться");
        ed1.setText(cc.getDescription());
        ed2.setText(cc.getTitle());
        tv.setText(cc.getDate());
        tv2.setText(cc.getName());
    }


    public void load(){
        try {
            Exchanger<ArrayList<CCourse>> exchanger=new Exchanger<>();
            new MyThread.TGetCourse(exchanger).start();
            _values = exchanger.exchange(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void Click(View v){
        Intent intent1=new Intent(this,kursy.class);
        startActivity(intent1);
    }

    public void Click2(View v){
        new MyThread.TSetrec("courses",login.userx.getUser_ID(),cc.getRandom_id()).start();
        if (button.getText().equals("записаться")){
            button.setText("отписаться");
        }else
        {
            button.setText("записаться");
        }
        //db.Srec("courses",login.userx.getUser_ID(),cc.getRandom_id());
    }

}
