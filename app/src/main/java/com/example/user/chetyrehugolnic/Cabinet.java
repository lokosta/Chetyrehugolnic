package com.example.user.chetyrehugolnic;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Cabinet extends AppCompatActivity {

    TextView _lg;
    TextView _nm;
    TextView _snm;
    final Context context=this;
    EditText _old;
    EditText _new1;
    EditText _new2;

    EditText _em;
    EditText _fm;
    EditText _nme;
    database db=Data_Base.instance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabinet);
        _lg=(TextView)findViewById(R.id.lg);
        _nm=(TextView)findViewById(R.id.nm);
        _snm=(TextView)findViewById(R.id.snm);

        _lg.setText(login.userx.getEmail());
        _nm.setText(login.userx.getFirstname());
        _snm.setText(login.userx.getLastName());
    }

    public void Click1(View v) {

        LayoutInflater li = LayoutInflater.from(context);//Получаем вид с файла dialog.xml, который применим для диалогового окна:
        final View dialogView = li.inflate(R.layout.dialog, null);

         AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);//Создаем AlertDialog
        mDialogBuilder.setView(dialogView);//Настраиваем dialog.xml для нашего AlertDialog:


       _old=(EditText)dialogView.findViewById(R.id.old);
        _new1=(EditText)dialogView.findViewById(R.id.new1);
        _new2=(EditText)dialogView.findViewById(R.id.new2);

        mDialogBuilder.setPositiveButton("подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            String oldpas=_old.getText().toString();
            String new1pas=_new1.getText().toString();
            String new2pas=_new2.getText().toString();


             if(login.userx.getPassword().equals(oldpas)){
                 if(login.userx.getPassword().equals(new1pas)){
                     Toast.makeText(context,"Введите другой пароль",Toast.LENGTH_SHORT).show();
                 }else {if(!(new1pas.equals(new2pas))){
                     Toast.makeText(context,"Подтвердите новый пароль еще раз",Toast.LENGTH_SHORT).show();
                 }else{
                     login.userx.setPassword(new2pas);
                     new MyThread.TUpdate(login.userx).start();
                 }
                 }
             }else {Toast.makeText(context,"Введен неверный пароль",Toast.LENGTH_SHORT).show();}

            }
        });
           mDialogBuilder.setNegativeButton("отменить", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   dialog.cancel();
               }
           });
        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();
    }
    public void Click2(View v){
        LayoutInflater li2=LayoutInflater.from(context);
        final View dialogView=li2.inflate(R.layout.dialog2,null);
        final AlertDialog.Builder m2DialogBuilder=new AlertDialog.Builder(context);
        m2DialogBuilder.setView(dialogView);

        _em=(EditText)dialogView.findViewById(R.id.em);
        _fm=(EditText)dialogView.findViewById(R.id.fm);
        _nme=(EditText)dialogView.findViewById(R.id.nme);

        m2DialogBuilder.setPositiveButton("подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              String newem=_em.getText().toString();
              String newfm=_fm.getText().toString();
              String newnme=_nme.getText().toString();

              login.userx.setEmail(newem);
              login.userx.setFirstname(newfm);
              login.userx.setLastName(newnme);

              new MyThread.TUpdate(login.userx).start();
              //


            }
        });
        m2DialogBuilder.setNegativeButton("отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = m2DialogBuilder.create();
        alertDialog.show();
    }
}
