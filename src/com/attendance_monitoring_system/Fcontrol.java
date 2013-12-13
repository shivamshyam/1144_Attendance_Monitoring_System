package com.attendance_monitoring_system;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Fcontrol extends Activity {
	
	Button btntatt,btnpatt,btheory,bpractical,blogout;
	Intent theoryAtt,practicalAtt,ivtheory,ivpractical;
	
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fcontrol);
		
		
		try{
			 			
			//int j=getIntent().getExtras().getInt("j");
			
			}catch (Exception e) {
				Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			}
		
		ivtheory = new Intent(this, AttTable.class);
		ivpractical = new Intent(this, AttTable.class);
		
		btheory = (Button) findViewById(R.id.bvtheory);
		bpractical = (Button) findViewById(R.id.bvpractical);
		btntatt=(Button)findViewById(R.id.btnfilltheory);
		btnpatt=(Button)findViewById(R.id.btnfillpractical);
		blogout=(Button)findViewById(R.id.tlogout);
		
	    //TextView tname=(TextView)findViewById(R.id.namefrom);
		//String sname=getIntent().getExtras().getString("iname");
		//tname.setText(sname);
		btheory.setOnClickListener(new theory());
		bpractical.setOnClickListener(new practical());
		blogout.setOnClickListener(new logout());
				
		
		btnpatt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {				
							
			practicalAtt=new Intent(Fcontrol.this,Fill_Practical.class);
			
			String name=getIntent().getExtras().getString("iname");
			practicalAtt.putExtra("iname",name);
			startActivity(practicalAtt);
			//Fcontrol.this.finish();
			}
		});
		
	

		btntatt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				
			theoryAtt=new Intent(Fcontrol.this,Fill_Theory.class);	
			String name=getIntent().getExtras().getString("iname");
			theoryAtt.putExtra("iname",name);
			startActivity(theoryAtt);
			//Fcontrol.this.finish();
			}
		});
	}

	public class practical implements OnClickListener {

		@Override
		public void onClick(View v) {
			
			
			
			String name=getIntent().getExtras().getString("iname");
			
			int i=2;
			ivpractical.putExtra("ivp",i);
			ivpractical.putExtra("iname",name);
			startActivity(ivpractical);
			//Fcontrol.this.finish();
		}

	}

	public class theory implements OnClickListener {

		@Override
		public void onClick(View v) {
			String name=getIntent().getExtras().getString("iname");
			
			int i=1;
			ivtheory.putExtra("ivt",i);
			ivtheory.putExtra("iname",name);
			
			startActivity(ivtheory);
			//Fcontrol.this.finish();
		}

	}
		
	public class logout implements OnClickListener {
		

		

				//@Override
				public void onClick(View arg0) {

					Context context=Fcontrol.this;
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

					// set title
					alertDialogBuilder.setTitle("Logout");
					alertDialogBuilder.setInverseBackgroundForced(isChild());
					
					// set dialog message
					alertDialogBuilder
							.setMessage("Click yes to logout!")
							.setCancelable(false)
							.setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int id) {
											// if this button is clicked, close
											// current activity
											Fcontrol.this.finish();
											
										}
									})
							.setNegativeButton("No",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int id) {
											// if this button is clicked, just close
											// the dialog box and do nothing
											dialog.cancel();
										}
									});

					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();

					// show it
					alertDialog.show();
			
	
		}
	}
}

