package com.example.user.chetyrehugolnic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sokol on 16.08.2018.
 */

public class MyAdapter1 extends ArrayAdapter<CRandom> {
    private final Activity _context;
    private final ArrayList<CRandom> _objects;

   public MyAdapter1(Activity context, ArrayList<CRandom>values){
        super(context,R.layout.records,values);
        _context=context;
        _objects=values;
    }
    static class ViewHolder{
        ImageView Image;
        TextView  date;
        TextView title;
        TextView name;
        TextView time;
        CRandom crandom;
    }
   @Override
    public View getView(int position, View convertView, ViewGroup parent){
       ViewHolder holder;
       View rowView =convertView;
       CRandom CurrentCrandom=_objects.get(position);

       if(rowView==null){
           LayoutInflater le=_context.getLayoutInflater();
           rowView=le.inflate(R.layout.records,null,true);
           holder=new ViewHolder();

           holder.Image=(ImageView)rowView.findViewById(R.id.im);
           holder.date=(TextView)rowView.findViewById(R.id.dte);
           holder.title=(TextView) rowView.findViewById(R.id.nt);
           holder.name=(TextView)rowView.findViewById(R.id.nme);
           holder.time=(TextView)rowView.findViewById(R.id.te);
           rowView.setTag(holder);
       }else holder=(ViewHolder)rowView.getTag();
     //if для картинок отдельно для курса и мероприятий
       holder.crandom=CurrentCrandom;
      if(CurrentCrandom.isCourse()){
          holder.Image.setImageResource(R.drawable.ex4);
      }
      else{ holder.Image.setImageResource(R.drawable.ex3);}

//       holder.Image.setImageResource(R.mipmap.ic_launcher);
       holder.date.setText(_objects.get(position).getDate());
       holder.title.setText(_objects.get(position).getTitle());
       holder.name.setText(_objects.get(position).getName());
       holder.time.setText(_objects.get(position).getDate());

       return rowView;

//    }  public static void quickSort(ArrayList<CRandom>_objects,int left,int right){
//    //if(_objects.isEmpty()) continue;
//
//    int middle=left+(right-left)/2;
//    int opora=Integer.valueOf(_objects.get(middle).getDate());
//    int i=left,j=right;
//    while(i<=j){
//        while(Integer.valueOf(_objects.get(i).getDate())<opora){
//            i++;
//        }
//        while(Integer.valueOf(_objects.get(j).getDate())>opora){
//            j--;
//        }
//        if(i<=j){
//            int temp=Integer.valueOf(_objects.get(i).getDate());
//            _objects.get(i).setDate(_objects.get(j).getDate());
//            _objects.get(j).setDate(String.valueOf(temp));
//            i++;
//            j--;
//        }
//    }
//    if(left<j)
//        quickSort(_objects, left,j);
//    if(right>i)
//        quickSort(_objects,i,right);
}
}
