package com.attendance_monitoring_system;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Fill_Practical extends Activity implements AdapterView.OnItemSelectedListener{
	Button ok, exit;
	SQLiteDatabase db;
	Intent intent;
	//RadioButton rbsem, rbclass;
	//private RadioGroup rgsem, rgclass;
	Spinner sem,Class;

	int year, month, day;
	String[] stringsem = { "1st","2nd","3rd","4th","5th","6th","7th","8th" };
	String[] stringclass = { "CS-A","CS-B","EC-A","EC-B","ME-A","ME-B","CE-A","CE-B","IT-A","IT-B" };
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fill_practical);
		
		

		final String name = getIntent().getExtras().getString("iname");

		try {

			db = openOrCreateDatabase("FacultyDB",
					SQLiteDatabase.CREATE_IF_NECESSARY, null);
			db.execSQL("Create Table If Not Exists p" + name
					+ "(sem text , class text , date date , present number)");
		} catch (Exception e) {

		}

		ok = (Button) findViewById(R.id.ok);

		final TextView tpdate = (TextView) findViewById(R.id.tpdate);
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// set current date into textview

		tpdate.setText(day + "-" + (month + 1) + "-" + year);
		
		
		Class=(Spinner)findViewById(R.id.pspinnerclass);
		Class.setOnItemSelectedListener(Fill_Practical.this);
		ArrayAdapter aclass = new ArrayAdapter(Fill_Practical.this,android.R.layout.simple_spinner_item,stringclass);
		aclass.setDropDownViewResource(									//to pull the contents of string array
		android.R.layout.simple_spinner_dropdown_item);				// in spinner
		Class.setAdapter(aclass);
		
		sem=(Spinner)findViewById(R.id.pspinnersem);
		sem.setOnItemSelectedListener(Fill_Practical.this);				
		ArrayAdapter asem = new ArrayAdapter(Fill_Practical.this,android.R.layout.simple_spinner_item,stringsem);
		asem.setDropDownViewResource(									//to pull the contents of string array
				android.R.layout.simple_spinner_dropdown_item);			// in spinner
				sem.setAdapter(asem);
		
		
		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				final EditText eppresent = (EditText) findViewById(R.id.eppresent);

				
				
						/*rgsem = (RadioGroup) findViewById(R.id.pradioGroup1);
				rgclass = (RadioGroup) findViewById(R.id.pradioGroup2);

				int semid = rgsem.getCheckedRadioButtonId();
				int classid = rgclass.getCheckedRadioButtonId();

				rbsem = (RadioButton) findViewById(semid);
				rbclass = (RadioButton) findViewById(classid);*/
				
				

				final ContentValues values = new ContentValues();

				String stpdate = tpdate.getText().toString();
				String seppresent = eppresent.getText().toString();

				//values.put("sem", rbsem.getText().toString());
				//values.put("class", rbclass.getText().toString());
				values.put("sem", sem.getSelectedItem().toString());
				values.put("class",Class.getSelectedItem().toString());
				values.put("date", stpdate);
				values.put("present", seppresent);

				if (!(seppresent.contentEquals(""))) {
					Context context = Fill_Practical.this;
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
					// set title
					alertDialogBuilder.setTitle("Save");
					alertDialogBuilder.setInverseBackgroundForced(isChild());

					// set dialog message
					alertDialogBuilder
							.setMessage("Click yes to save!")
							.setCancelable(false)
							.setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog, int id) {
											// if this button is clicked, close
											// current activity

											try {
												if ((db.insert("p" + name,
														null, values)) != -1) {

													eppresent.setText("");

													intent = new Intent(
															Fill_Practical.this,
															AttTable.class);
													int i = 2;
													intent.putExtra(
															"practical", i);
													intent.putExtra("iname",
															name);

													startActivity(intent);
													Fill_Practical.this
															.finish();
												}
											} catch (Exception e) {

											}
										}
									})

							.setNegativeButton("No",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											// if this button is clicked, just
											// close
											// the dialog box and do nothing
											dialog.cancel();
										}
									});

					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();

					// show it
					alertDialog.show();

				} else {
					Toast.makeText(Fill_Practical.this,
							"present can't blank", Toast.LENGTH_LONG)
							.show();
				}

			}

		});

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
