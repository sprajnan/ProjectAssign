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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ViewTask extends Activity {

	Button b ;
	TextView t1,t2,t3,t4,t5;
	String user_email = null;
	String id = null,status = null;
	String due_date,category,description,priority,title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_task);
		Intent i = getIntent();
        id = i.getStringExtra("task_id");
        t1 = (TextView)findViewById(R.id.textView1);
        t2 = (TextView)findViewById(R.id.textView7);
        t3 = (TextView)findViewById(R.id.textView3);
        t4 = (TextView)findViewById(R.id.textView5);
        t5 = (TextView)findViewById(R.id.textView9);
        MyTask6 m  = new MyTask6();
        m.execute();
        b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v)
        	{
        		
        		 MyTask7 m1  = new MyTask7();
        	        m1.execute();
        		
        	}});
	}
	
	public class MyTask6 extends AsyncTask<String,Void,Void>
    {
	
	       
		RestServiceUrl s = new RestServiceUrl();
		@Override
		protected Void doInBackground(String... params) {
			user_email = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("user_email", "NoUser");
			StringBuilder sb;
			try
            {
				sb = new StringBuilder(s.url+"get_task/"+id);
	    	    String findlink=sb.toString();
	    	    HttpClient client=new DefaultHttpClient();
	    	   	    HttpGet request=new HttpGet();
	    	   		request.setURI(new URI(findlink)) ;
	    	   		HttpResponse response=client.execute(request);
	    	   	
	    	    	DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();	
	    	   	   DocumentBuilder Builder=factory.newDocumentBuilder();
	    	   	 Document  dom=Builder.parse(response.getEntity().getContent());
	    	   	  Node node=  dom.getDocumentElement();
	    	   	  if(node.hasChildNodes())
	    	   	  {
	    	   	NodeList   nList5=dom.getElementsByTagName("MyTask");
	    	   	for(int temp=0;temp<nList5.getLength();temp++)
			  	{
			  		 Node nNode=nList5.item(temp);
			  		 if(nNode.getNodeType()==Node.ELEMENT_NODE)
			  		 {
			  			 		Element eElement=(Element)nNode;
			  			 	  due_date  = (getTagValue("due_date", eElement));
			  			 	   category = (getTagValue("task_category",eElement));
			  			 	description = (getTagValue("task_description",eElement));
			  			 	priority  = (getTagValue("task_priority",eElement));
			  			 	title = (getTagValue("task_title",eElement));
			  			 	
			  	      }
			  	}
	    	   	  }
	    	   	  else
	    	   	  {
	    	   		  TextView t= (TextView)findViewById(R.id.textView2);
	    	   		  
	    	   		  t.setText("No search found");
	    	   		t.setVisibility(0);
	    	   	  }
	    	   	 
            }
			catch(Exception e)
			{
				
			} 
			 
			return null;
		}
		private String getTagValue(String sTag,Element eElement)
		{
	    	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
			
			if(nlList.getLength()>0)
			{
		    Node nValue = (Node) nlList.item(0);
	        return nValue.getNodeValue();
			}
			else
				return "";
		
		}

		@Override
		protected void onPostExecute(Void result) {
		
	
			 t1.setText(title);
			 t2.setText(description);
			 t3.setText(category);
			 t4.setText(due_date);
			 t5.setText(priority);
			 
			 
		}
    
    
    }
	
	public class MyTask7 extends AsyncTask<String,Void,Void>
	
    {
		 Element root ;
		 
		 private ProgressDialog pDialog;
		 RestServiceUrl s = new RestServiceUrl();
		 
		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	             
	            pDialog = new ProgressDialog(ViewTask.this);
	            pDialog.setMessage("Deleting task...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	 
	        }
			@Override
			protected Void doInBackground(String... arg0) {
				try
				{
				 StringBuilder sb=new StringBuilder(s.url+"delete_task/"+id);
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
			 		 
			 		 Intent i = new Intent(getApplicationContext(), ShowAllTasks.class);
	                    startActivity(i);
	                    finish();
			 	}
			 	else
			 	{
			 		
			 		
			 	}
	    	   
				super.onPostExecute(result);
			}
			
			
			
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
