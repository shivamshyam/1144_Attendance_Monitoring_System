package com.attendance_monitoring_system;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Home extends Activity 
{

	Button  btnlogin, btncreatacc;

	Intent  tologin, creataccount;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		btnlogin = (Button) findViewById(R.id.btnlogin);
		btncreatacc = (Button) findViewById(R.id.creatacc);

		tologin = new Intent(this, FLogin.class);
		creataccount = new Intent(this, Creat_Account.class);

		btncreatacc.setOnClickListener(new Account());
		btnlogin.setOnClickListener(new Flgin());
		
	
	}
		
	public class Flgin implements OnClickListener 
	{

		@Override
		public void onClick(View v) 
		{
			
			startActivity(tologin);
			//Home.this.finish();
		}

	}

	public class Account implements OnClickListener 
	{

		@Override
		public void onClick(View v) 
		{
			
			startActivity(creataccount);
			//Home.this.finish();
		}

	}

}
