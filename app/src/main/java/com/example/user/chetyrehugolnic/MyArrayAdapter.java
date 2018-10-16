package com.example.user.chetyrehugolnic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sokol on 31.07.2018.
 */

public class MyArrayAdapter extends ArrayAdapter<CEvent> {
    private final Activity _context;
    private final ArrayList<CEvent> _objects;

    public MyArrayAdapter(Activity context,ArrayList<CEvent>values){
        super(context,R.layout.event,values);
        _context=context;
        _objects=values;
    }
    static class ViewHolder{
        TextView date;
        TextView Name;
     TextView Title;
     TextView  date_event;
     TextView time_event;
    // TextView Description;
    // TextView name_event;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
     ViewHolder holder;
     View rowView=convertView;


     if(rowView==null){
         LayoutInflater inflater=_context.getLayoutInflater();
         rowView=inflater.inflate(R.layout.event,null,true);
         holder=new ViewHolder();
         holder.Title=(TextView)rowView.findViewById(R.id.tv1);
         holder.time_event=(TextView)rowView.findViewById(R.id.tv6);
         holder.date_event=(TextView)rowView.findViewById(R.id.tv2);
        // holder.Description=(TextView)rowView.findViewById(R.id.tv3);
        // holder.name_event=(TextView)rowView.findViewById(R.id.tv4);
         rowView.setTag(holder);
     }
     else holder=(ViewHolder)rowView.getTag();

     holder.Title.setText(_objects.get(position).getTitle());
     holder.date_event.setText(_objects.get(position).getDate());
     holder.time_event.setText(_objects.get(position).getTime());
   //  holder.Description.setText(_objects.get(position).Description);
    // holder.name_event.setText(_objects.get(position).name_event.first_name);
     return rowView;
    }

}
