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
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.preference.PreferenceManager;

public class SignIn extends Activity{
	EditText get_email;
	EditText get_password;
	String email,password,status;
	TextView status_text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        
        
        get_email =(EditText)findViewById(R.id.getemail);
        get_password=(EditText)findViewById(R.id.getpassword);
        status_text = (TextView)findViewById(R.id.textView4);
        Button signin=(Button)findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v)
        	{
             //printing the email
        	email=get_email.getText().toString();
        	//System.out.println("Hey"+email);
        	
        	//printing the password
        	password=get_password.getText().toString();
        	//System.out.println("Heya"+password);
        	MyTask m = new MyTask();
        	m.execute();
      
        	
        }});
        
        TextView newuser =(TextView)findViewById(R.id.signup);
        newuser.setOnClickListener(new View.OnClickListener() {

            @Override	
            public void onClick(View v){
            	startActivity(new Intent("com.example.projectassign.SIGNUP"));
            }
        });
    
	
        TextView fpwd =(TextView)findViewById(R.id.forgotpwd);
        fpwd.setOnClickListener(new View.OnClickListener() {
    	
    	public void onClick(View v)
    	{
        	startActivity(new Intent("com.example.projectassign.FORGOTPWD"));

    }});
    
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
	             
	            pDialog = new ProgressDialog(SignIn.this);
	            pDialog.setMessage("Logging in...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	 
	        }
			@Override
			protected Void doInBackground(String... arg0) {
				try
				{
				 StringBuilder sb=new StringBuilder(s.url+"user_login/"+email+","+password);
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
			 	if(status.equals("Success"))
			 	{
			 		PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("user_email", email).commit(); 
			 		 Intent i = new Intent(getApplicationContext(), ShowAllTasks.class);
	                    startActivity(i);
	                    finish();
			 	}
			 	else
			 	{
			 		
			 		status_text.setText("Login Failed Please try again !");
			 	}
	    	   
				super.onPostExecute(result);
			}
			
			
			
     }

	
	
	
	}



