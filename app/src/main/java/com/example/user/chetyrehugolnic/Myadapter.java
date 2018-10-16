package com.example.user.chetyrehugolnic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Myadapter extends ArrayAdapter<CCourse> {
    private final Activity _context;
    private final ArrayList<CCourse> _values;
    public Myadapter(Activity context, ArrayList<CCourse> objects) {
        super(context,R.layout.item2,objects);
        _context=context;
        _values=objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        MyArrayAdapter.ViewHolder holder;
        View rowView=convertView;

        if(rowView==null) {
            LayoutInflater inflater=_context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.item2, null,true);
            holder= new MyArrayAdapter.ViewHolder();
            //holder.image=(ImageView)rowView.findViewById(R.id.imgAcces);
            holder.Name=(TextView) rowView.findViewById(R.id.textView);
            holder.Title=(TextView)rowView.findViewById(R.id.textView2);
            holder.date=(TextView)rowView.findViewById(R.id.textView3);
            //holder.Comment=(TextView)rowView.findViewById(R.id.textView4);
            rowView.setTag(holder);
        }
        else holder =(MyArrayAdapter.ViewHolder)rowView.getTag();
            holder.Name.setText(_values.get(position).getName());//.getName()
            holder.Title.setText(_values.get(position).getTitle());//.getTitle()
            holder.date.setText(String.valueOf(_values.get(position).getDate()));//.getDate()
            //holder.Comment.setText(_values.get(position).Comment);

        return rowView;
    }
}
