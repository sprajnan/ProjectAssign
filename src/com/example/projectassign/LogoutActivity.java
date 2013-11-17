package com.example.projectassign;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class LogoutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logout);
		PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().remove("user_email").commit();
		 Intent i = new Intent(getApplicationContext(), MainActivity.class);
         startActivity(i);
         finish();
	}

	

}
