package com.example.user.chetyrehugolnic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;


public class CourseFragment extends Fragment {



    static String number="";
    ImageButton btn,btn2,btn3,btn4,btn5,btn6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_sixxxx,container,false);
        btn=(ImageButton)view.findViewById(R.id.imageButton);
        btn2=(ImageButton)view.findViewById(R.id.imageButton2);
        btn3=(ImageButton)view.findViewById(R.id.imageButton3);
        btn4=(ImageButton)view.findViewById(R.id.imageButton4);
        btn5=(ImageButton)view.findViewById(R.id.imageButton5);
        btn6=(ImageButton)view.findViewById(R.id.imageButton6);
        btn.setOnClickListener(new Listener());
        btn2.setOnClickListener(new Listener());
        btn3.setOnClickListener(new Listener());
        btn4.setOnClickListener(new Listener());
        btn5.setOnClickListener(new Listener());
        btn6.setOnClickListener(new Listener());

        return view;
    }

    public class Listener implements ImageButton.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(getActivity(),kursy.class);
            intent.putExtra(number,v.getId());
            startActivity(intent);
        }
    }

    public void Click1(View v){

    }
//    public void Click2(View v){
//        String number="2";
//        intent.putExtra(number,2);
//        startActivity(intent);
//    }
//    public void Click3(View v){
//        String number="3";
//        intent.putExtra(number,3);
//        startActivity(intent);
//    }
//    public void Click4(View v){
//        String number="4";
//        intent.putExtra(number,4);
//        startActivity(intent);
//    }
//    public void Click5(View v){
//        String number="5";
//        intent.putExtra(number,5);
//        startActivity(intent);
//    }
//    public void Click6(View v){
//        String number="6";
//        intent.putExtra(number,6);
//        startActivity(intent);
//    }

}
