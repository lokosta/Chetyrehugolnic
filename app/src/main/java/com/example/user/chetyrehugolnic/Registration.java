package com.example.user.chetyrehugolnic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.util.concurrent.Exchanger;

public class Registration extends AppCompatActivity {

    EditText _lg;
    EditText _nm;
    EditText _snm;

    EditText _psw;
    EditText _psw1;

    //   database db=Data_Base.instance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        _lg = (EditText) findViewById(R.id.lg);
        _nm = (EditText) findViewById(R.id.nm);
        _snm = (EditText) findViewById(R.id.snm);

        _psw = (EditText) findViewById(R.id.psw);
        _psw1 = (EditText) findViewById(R.id.psw1);

    }

    public void Click3(View v) throws JSONException {
        Boolean b=false;
        Exchanger<Boolean> exchanger = new Exchanger<>();
        if (!_psw.getText().toString().equals(_psw1.getText().toString())) {
            Toast.makeText(this, "ты рукожоп,пароли не совпадают", Toast.LENGTH_SHORT).show();
        } else {


            CUsers use = new CUsers(7, 3, _lg.getText().toString(), _psw.getText().toString(), _nm.getText().toString(), _snm.getText().toString(), null);


//            class MyThread2 extends Thread {
//                Exchanger<Boolean> exchanger;
//                database db = Data_Base.instance();
//                CUsers users;
//                Boolean b;


//            }

            new MyThread.TReg(exchanger,use).start();


            try {
                b=exchanger.exchange(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("значение b",b.toString());
            if(b){
           Toast.makeText(this,"Вы успешно зарегестрировались",Toast.LENGTH_SHORT).show();
//           Intent intent=new Intent(Registration.this,Cabinet.class);
//           startActivity(intent);//переход на другую активность
       } else{
           Toast.makeText(this,"Такой e-mail уже зарегистрирован, пшёл вон",Toast.LENGTH_SHORT).show();
       }
      }
        }


    }

