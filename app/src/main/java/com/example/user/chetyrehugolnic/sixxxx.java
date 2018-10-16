package com.example.user.chetyrehugolnic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class sixxxx extends AppCompatActivity {

    Intent intent=new Intent(this,kursy.class);
    static String number="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixxxx);
    }
    public void Click1(View v){
        String number="1";
        intent.putExtra(number,1);
        startActivity(intent);
    }
    public void Click2(View v){
        String number="2";
        intent.putExtra(number,2);
        startActivity(intent);
    }
    public void Click3(View v){
        String number="3";
        intent.putExtra(number,3);
        startActivity(intent);
    }
    public void Click4(View v){
        String number="4";
        intent.putExtra(number,4);
        startActivity(intent);
    }
    public void Click5(View v){
        String number="5";
        intent.putExtra(number,5);
        startActivity(intent);
    }
    public void Click6(View v){
        String number="6";
        intent.putExtra(number,6);
        startActivity(intent);
    }

}
