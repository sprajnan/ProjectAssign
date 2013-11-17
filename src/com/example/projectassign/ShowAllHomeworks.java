package com.example.projectassign;



import java.net.URI;
import java.util.ArrayList;

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
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ShowAllHomeworks extends Activity {
	private ListView lv;
	MyAdapter adapter;
	ArrayList<String> no_of_days = new ArrayList<String>();
    ArrayList<String> task_category = new ArrayList<String>();
    ArrayList<String> task_id = new ArrayList<String>();
    ArrayList<String> task_title = new ArrayList<String>();
    String user_email = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_all_homeworks);
		user_email = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("user_email", "NoUser");
		MyTask3 m = new MyTask3();
		m.execute();
	}

	public class MyTask3 extends AsyncTask<String,Void,Void>
    {
	
	       
		RestServiceUrl s = new RestServiceUrl();
		@Override
		protected Void doInBackground(String... params) {
			user_email = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("user_email", "NoUser");
			StringBuilder sb;
			try
            {
				sb = new StringBuilder(s.url+"get_all_homeworks/"+user_email);
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
	    	   	NodeList   nList5=dom.getElementsByTagName("Task");
	    	   	for(int temp=0;temp<nList5.getLength();temp++)
			  	{
			  		 Node nNode=nList5.item(temp);
			  		 if(nNode.getNodeType()==Node.ELEMENT_NODE)
			  		 {
			  			 		Element eElement=(Element)nNode;
			  			 	  no_of_days.add(getTagValue("no_of_days", eElement));
			  			 	   task_category.add(getTagValue("task_category",eElement));
			  			 	task_id.add(getTagValue("task_id",eElement));
			  			 	task_title.add(getTagValue("task_title",eElement));
			  			 	
			  	      }
			  	}
	    	   	  }
	    	   	  else
	    	   	  {
	    	   		  TextView t= (TextView)findViewById(R.id.textView2);
	    	   		  
	    	   		 t.setText("No results found");
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
		
	
			 lv = (ListView) findViewById(R.id.listview);
		        adapter = new MyAdapter(ShowAllHomeworks.this,no_of_days,task_category,task_id,task_title);
		        lv.setAdapter(adapter);
		        
		        lv.setOnItemClickListener(new OnItemClickListener() {
		            public void onItemClick(AdapterView<?> parent, View view,
		                int position, long id) {
		            	EditText e = (EditText) view.findViewById(R.id.editText2);
		            	 String task_id = e.getText().toString();
		            	 Intent i = new Intent(getApplicationContext(), ViewTask.class);
		                 // sending data to new activity
		                 i.putExtra("task_id",task_id);
		                 startActivity(i);
		                 finish();
		            }
		        }); 
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



