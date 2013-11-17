package com.example.projectassign;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.preference.PreferenceManager;
public class MainActivity extends Activity {
String session_state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        session_state = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("user_email", "NoUser");
        if(!session_state.equals("NoUser"))
        {
        	Intent i = new Intent(getApplicationContext(), ShowAllTasks.class);
            // sending data to new activity
            
            startActivity(i);
			 finish();
        	
        }
        TextView t =(TextView)findViewById(R.id.t1);
        TextView t1 = (TextView)findViewById(R.id.textView1);
        //t1.setText(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("user_email", "NoUser"));
        t.setOnClickListener(new View.OnClickListener() {
        
        
        @Override	
        public void onClick(View v){
        	startActivity(new Intent("com.example.projectassign.SIGN"));
        }
    });
        }


    /*
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
        
        public boolean onOptionsItemSelected(MenuItem item)
        {
        	startActivity(new Intent("com.example.projectassign.MENU"));
            return true;
     
    }*/
    
}
