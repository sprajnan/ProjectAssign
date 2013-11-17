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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class NewTask extends Activity{
	RadioGroup list;
	Integer priority,remind,day,month,year;
	Spinner cat;
	EditText title,desc;
	String category,task_title,task_desc,date1,status = null,user_email;
	CheckBox reminder;
	DatePicker deadline;
	AlertDialogManager alert = new AlertDialogManager();
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtask);
        
        cat = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.entries, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cat.setAdapter(adapter);
		cat.setOnItemSelectedListener(new function());
		
		 
        title=(EditText)findViewById(R.id.task_title);
        desc=(EditText)findViewById(R.id.task_desc);
        
        deadline=(DatePicker)findViewById(R.id.date);
        
        list=(RadioGroup)findViewById(R.id.radioGroup1);
        list.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        	public void onCheckedChanged(RadioGroup arg0,int arg1)
        	{
        	switch(arg1){
        	case R.id.r1:
        		priority=1;
        		break;
        		
        	case R.id.r2:
        		priority=2;
        		break;
        		
        	case R.id.r3:
        		priority=3;
        		break;
        		
        	}
        	System.out.println("The priority is"+priority);
        	
        	}
        });
        
       // reminder=(CheckBox)findViewById(R.id.reminder);
        
        Button addtask=(Button)findViewById(R.id.addtask);
        addtask.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v)
        	{
             //printing the title
        	task_title=title.getText().toString();
        	System.out.println(task_title);
        	
        	//printing the desc
        	task_desc=desc.getText().toString();
        	System.out.println(task_desc);
        	
        	// for the check box
       /* 	if(reminder.isChecked())
        		remind=1;
        	else
        		remind=0;
        	System.out.println(remind); */
        	
        	// for the date
            day=deadline.getDayOfMonth();
            month=deadline.getMonth()+1;
            year=deadline.getYear();
            day.toString();
            month.toString();
            year.toString();
            
            date1=year+"-"+month+"-"+day;
            user_email = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("user_email", "NoUser");
        	//System.out.println(date1);
        	MyTask10 m10 = new MyTask10();
        	m10.execute();

        
        }});

        
        
}
    
    public class MyTask10 extends AsyncTask<String,Void,Void>
    {
		 Element root ;
		 String password1 = "";
		 private ProgressDialog pDialog;
		 RestServiceUrl s = new RestServiceUrl();
		 
		 
			@Override
			protected Void doInBackground(String... arg0) {
				try
				{
					
					StringBuilder sb=new StringBuilder(s.url+"new_task/"+user_email+","+category+","+task_title+","+task_desc+","+date1+","+priority);
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
				
			 	if(status.equals("Success"))
			 	{
			 		Intent i = new Intent(getApplicationContext(), ShowAllTasks.class);
	                startActivity(i);
	                finish();
			 	}
			 	else
			 	{
			 		//alert.showAlertDialog(getApplicationContext(), "Error"," Creating Task", false);
			 		
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
        	startActivity(new Intent("com.example.projectassign.MENU"));
            return true;
     
    }
	

public class function implements OnItemSelectedListener {

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
			long id) {
		// TODO Auto-generated method stub
		category = parent.getItemAtPosition(pos).toString();
		System.out.println(" the category is "+category);
        
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
}
