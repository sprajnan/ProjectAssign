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



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends Activity{
	EditText email,password;
	TextView t;
	String user_email,user_password,status;
	Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        email = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);
        t = (TextView)findViewById(R.id.textView1);
        
        b = (Button)findViewById(R.id.signupnow);
        b.setOnClickListener(new View.OnClickListener() {

            @Override	
            public void onClick(View v){
            	MyTask m = new MyTask();
            	m.execute();
            }
        });
        
}
	public class MyTask extends AsyncTask<String,Void,Void>
    {
		 Element root ;
		 String password1 = "";
		 private ProgressDialog pDialog;
		 RestServiceUrl s = new RestServiceUrl();
		 
		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	             
	            pDialog = new ProgressDialog(SignUp.this);
	            pDialog.setMessage("Signing you up...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	 
	        }
			@Override
			protected Void doInBackground(String... arg0) {
				try
				{
					user_email = email.getText().toString();
			        user_password = password.getText().toString();
				 StringBuilder sb=new StringBuilder(s.url+"user_reg/"+user_email+","+user_password);
		    	    String findlink=sb.toString();
		    	    HttpClient client=new DefaultHttpClient();
		    	   	    HttpGet request=new HttpGet();
		    	   		request.setURI(new URI(findlink)) ;
		    	   		HttpResponse response=client.execute(request);
		    	   	
		    	    	DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();	
		    	   	   DocumentBuilder Builder=factory.newDocumentBuilder();
		    	   	 Document  dom=Builder.parse(response.getEntity().getContent());
		    	   	   dom.getDocumentElement().normalize();
		    	   	 root=dom.getDocumentElement();
		    	   	 status = root.getTextContent().toString();
		    	   	
		  
		    	   	
		    	   	
				}
				catch(Exception e)
				{
					
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				pDialog.dismiss();
				if(status.equals("User already exists"))
				{
					t.setText("Email ID already taken ! Please enter another email");
				}
				else
				{
					Intent i = new Intent(getApplicationContext(), CodeVerification.class);
	                 // sending data to new activity
	                 i.putExtra("code",status);
	                 startActivity(i);
					 finish();
				}	
				
				super.onPostExecute(result);
			}
			
			
			
    }
	
	
	
	
	
	
	/*
	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
        
        public boolean onOptionsItemSelected(MenuItem item)
        {
        	switch (item.getItemId())
            {
        	case R.id.menu :startActivity(new Intent("com.example.projectassign.MENU"));
        	break;
        	case R.id.logout :
        		Intent i = new Intent(getApplicationContext(), LogoutActivity.class);
                startActivity(i);
                finish();
            }
        	startActivity(new Intent("com.example.projectassign.MENU"));
            return true;
     
    } */
	}