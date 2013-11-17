package com.example.projectassign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Menu1 extends Activity{
	Button b1,b2,b3,b4,b5;
	 @Override
		protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.menu);
	        b1=(Button)findViewById(R.id.button1);
	        b1.setOnClickListener(new View.OnClickListener() {
	        	
	        	public void onClick(View v)
	        	{
	        		Intent i = new Intent(getApplicationContext(), NewTask.class);
	                startActivity(i);
	                finish();
	        		
	        		
	        	}});
	        b5=(Button)findViewById(R.id.button5);
	        b5.setOnClickListener(new View.OnClickListener() {
	        	
	        	public void onClick(View v)
	        	{
	        		Intent i = new Intent(getApplicationContext(), ShowAllTasks.class);
	                startActivity(i);
	                finish();
	        		
	        		
	        	}});
	        b2=(Button)findViewById(R.id.button2);
	        b2.setOnClickListener(new View.OnClickListener() {
	        	
	        	public void onClick(View v)
	        	{
	        		Intent i = new Intent(getApplicationContext(), ShowAllProjects.class);
	                startActivity(i);
	                finish();
	        		
	        		
	        	}});
	        b3=(Button)findViewById(R.id.button3);
	        b3.setOnClickListener(new View.OnClickListener() {
	        	
	        	public void onClick(View v)
	        	{
	        		Intent i = new Intent(getApplicationContext(), ShowAllAssignments.class);
	                startActivity(i);
	                finish();
	        		
	        		
	        	}});
	        
	        b4=(Button)findViewById(R.id.button4);
	        b4.setOnClickListener(new View.OnClickListener() {
	        	
	        	public void onClick(View v)
	        	{
	        		Intent i = new Intent(getApplicationContext(), ShowAllHomeworks.class);
	                startActivity(i);
	                finish();
	        		
	        		
	        	}});
	        
	        
	}
	
	 public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	        
	        public boolean onOptionsItemSelected(MenuItem item)
	        {
	        	switch (item.getItemId())
	            {
	            case R.id.logout :
	        		Intent i = new Intent(getApplicationContext(), LogoutActivity.class);
	                startActivity(i);
	                finish();
	                break;
	        	case R.id.menu :startActivity(new Intent("com.example.projectassign.MENU"));
	        	break;
	        	
	            }
	        	
	            return true;
	     
	    }
	}
