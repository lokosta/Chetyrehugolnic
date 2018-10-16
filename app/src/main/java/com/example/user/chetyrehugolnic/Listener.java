package com.example.user.chetyrehugolnic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import static android.support.v4.content.ContextCompat.startActivity;

public class Listener implements ListView.OnItemClickListener {
    Context context;
    Class clacc;
    Bundle options=new Bundle();
    int key;
    public static final String INT_MASSAGE_INDEX="com.example.vsev.kursy_2.INT_MASSAGE_INDEX";
    public Listener(Context _context,Class _clacc){
        context=_context;
        clacc=_clacc;
    };


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        key=position;
        Intent intent=new Intent(context,clacc);//("Opisanie.app")
        intent.putExtra(INT_MASSAGE_INDEX,key);
        Log.i("kvadrat",String.valueOf(key));
        startActivity(context,intent,options);
    }
}
