package com.example.user.chetyrehugolnic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Sign_up extends AppCompatActivity {

    TextView name_event;
    TextView  date_event;
    TextView Description;
    TextView Teacher;
    Button _btn1;
    Data_Base db=Data_Base.instance();
    CEvent element;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name_event=(TextView)findViewById(R.id.tv_event);
        date_event=(TextView)findViewById(R.id.tv_date);
        Description=(TextView)findViewById(R.id.tv_discription);
        Teacher=(TextView)findViewById(R.id.tv_teacher);
        _btn1=(Button)findViewById(R.id.btn1);
        int position;
        Intent intent = getIntent();
       // if (intent != null) {
            position = intent.getIntExtra("POSITION_KEY", -1);
      // }
        element =Event1Fragment.sName.get(position);

    name_event.setText(element.getTitle());
    date_event.setText(String.valueOf(element.getDate()));
    Description.setText(element.getDescription());
    Teacher.setText(element.getName());

    if(element.isZapis()){
        _btn1.setText("Отписаться");
    }else {_btn1.setText("Записаться");}


    }
    public void sign(View v){
        Toast.makeText(this, "Вы записаны!", Toast.LENGTH_SHORT).show();
        //данные записывыем в таблицу БД
//        db.Srec("Events",login.userx.getUser_ID(),element.getRandom_id());
        new MyThread.TSetrec("events",login.userx.getUser_ID(),element.getRandom_id()).start();
        if(_btn1.getText().equals("Отписаться")){
            _btn1.setText("Записаться");
        }else _btn1.setText("Отписаться");
}
}
