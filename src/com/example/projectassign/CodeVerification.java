package com.example.projectassign;

import java.net.URI;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.example.projectassign.SignUp.MyTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CodeVerification extends Activity {
	Button b;
	EditText et;
	TextView t;
	String code;
	String status;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.code_verification);
		b = (Button)findViewById(R.id.button1);
		et  =(EditText)findViewById(R.id.editText1);
		t = (TextView)findViewById(R.id.textView2);
		Intent i = getIntent();
        code = i.getStringExtra("code");
        b.setOnClickListener(new View.OnClickListener() {

            @Override	
            public void onClick(View v){
            	MyTask m = new MyTask();
            	m.execute();
            }
        });
        
	//	t.setText(code);
	}

	public class MyTask extends AsyncTask<String,Void,Void>
    {
		
		 private ProgressDialog pDialog;
		
		 
		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	             
	            pDialog = new ProgressDialog(CodeVerification.this);
	            pDialog.setMessage("Verifying...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	 
	        }
			@Override
			protected Void doInBackground(String... arg0) {
				try
				{
					
		    	   	String entered_code = et.getText().toString();
		    	   	if(code.equals(entered_code))
		    	   	{
		    	   		status = "ok";
		    	   		
		    	   	}
		    	   	else
		    	   		status = "no";
		    	   	
		    	   	
				}
				catch(Exception e)
				{
					
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				pDialog.dismiss();
					if(status.equals("ok"))
					{
						Intent i = new Intent(getApplicationContext(), SignIn.class);
		                 // sending data to new activity
		                 
		                 startActivity(i);
						 finish();
					}
					else
					
					{
						t.setText("Verification code failed ! Please enter the correct code");
					}
				super.onPostExecute(result);
			}
			
			
			
    }
	
	
	
/*	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.code_verification, menu);
		return true;
	} */

}
