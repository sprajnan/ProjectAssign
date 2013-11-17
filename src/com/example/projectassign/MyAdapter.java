package com.example.projectassign;

import java.util.ArrayList;



import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private Activity activity;
	ArrayList<String> list1;
	ArrayList<String> list2;
	ArrayList<String> list3;
	ArrayList<String> list4;
	public MyAdapter(Activity a,ArrayList<String> no_of_days , ArrayList<String> task_category, ArrayList<String> task_id,ArrayList<String> task_title)
	{
		activity = a;
		list1 = no_of_days;
		list2 = task_category;
		list3 = task_id;
		list4 = task_title;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list1.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
	     
		 
	            LayoutInflater vi =
	                (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = vi.inflate(R.layout.task_row, null);
	            TextView t1 = (TextView) v.findViewById(R.id.textView1);
	            TextView t2 = (TextView) v.findViewById(R.id.textView2);
	            TextView t5 = (TextView) v.findViewById(R.id.textView5);
	            EditText et = (EditText) v.findViewById(R.id.editText2);
	            
	            t1.setText(list4.get(position));
	            t2.setText("Category : "+list2.get(position));
	            t5.setText(list1.get(position)+" days to go!");
	            et.setText(list3.get(position));
	            // Color
	            if(Integer.parseInt(list1.get(position).toString()) <= 3)
	            {
	            	v.setBackgroundColor(activity.getResources().getColor(R.color.Tomato)); 
	            }
	            if(Integer.parseInt(list1.get(position).toString()) > 3 && Integer.parseInt(list1.get(position).toString()) <=7 )
	            {
	            	v.setBackgroundColor(Color.YELLOW); 
	            }
	            if(Integer.parseInt(list1.get(position).toString()) > 7)
	            {
	            	v.setBackgroundColor(activity.getResources().getColor(R.color.LightGreen)); 
	            }
		return v;
	}

}
