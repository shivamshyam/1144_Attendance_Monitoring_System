package com.attendance_monitoring_system;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Creat_Account extends Activity {

	SQLiteDatabase db;
	Intent intent;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createaccount);

		/*
		 * TextView tname ,tpass,tcpass,tuserid;
		 * 
		 * tname=(TextView)findViewById(R.id.tname);
		 * tpass=(TextView)findViewById(R.id.tpass);
		 * tcpass=(TextView)findViewById(R.id.tcpass);
		 * tuserid=(TextView)findViewById(R.id.tuserid);
		 */

		Button submit = (Button) findViewById(R.id.submit);

		try {

			db = openOrCreateDatabase("FacultyDB",
					SQLiteDatabase.CREATE_IF_NECESSARY, null);

			db.execSQL("Create Table faculties(name text, userid text primary key ,password text , cpassword text)");

		} catch (Exception e) {

		}

		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				EditText ename = (EditText) findViewById(R.id.ename);
				EditText epass = (EditText) findViewById(R.id.epass);
				EditText ecpass = (EditText) findViewById(R.id.ecpass);
				EditText euserid = (EditText) findViewById(R.id.euserid);
				
				ContentValues values = new ContentValues();

				String sename = ename.getText().toString();
				String sepass = epass.getText().toString();
				String secpass = ecpass.getText().toString();
				String seuserid = euserid.getText().toString();

				
				
				values.put("name", sename);
				values.put("userid", seuserid);
				values.put("password", sepass);
				values.put("cpassword", secpass);

				if (!(sename.contentEquals("")) && !(sepass.contentEquals(""))
						&& !(seuserid.contentEquals(""))) {
					if (sepass.contentEquals(secpass) == true) {
						try {

							if ((db.insert("faculties", null, values)) != -1) {
								ename.setText("");
								epass.setText("");
								ecpass.setText("");
								euserid.setText("");
								Toast.makeText(Creat_Account.this,
										"Record Successfully Inserted",
										Toast.LENGTH_LONG).show();

								intent = new Intent(Creat_Account.this,
										Fcontrol.class);
								intent.putExtra("iname", seuserid);
								int i = 1;
								intent.putExtra("i", i);
								startActivity(intent);
								Creat_Account.this.finish();
							}
							else{
								Toast.makeText(Creat_Account.this,"userid already exist", Toast.LENGTH_LONG).show();
								euserid.setText("");
							}
						} catch (Exception e) {

						}

					} else {
						ecpass.setText("");
						Toast.makeText(Creat_Account.this,
								"Password & ConfirmPassword must be same",
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(Creat_Account.this,
							"Name or id or password cant blank",
							Toast.LENGTH_LONG).show();
				}
			}

		});

		/*
		 * view.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { // TODO Auto-generated
		 * method stub
		 * 
		 * 
		 * try {
		 * 
		 * //String[] s=new String[1];
		 * 
		 * Cursor c=db.rawQuery("SELECT * FROM faculty1",null);
		 * 
		 * c.moveToFirst(); while(!c.isAfterLast()) {
		 * 
		 * Toast.makeText(Creat_Account.this,c.getString(0)+ " "+c.getString(1),
		 * 1000).show(); c.moveToNext(); }
		 * 
		 * c.close(); } catch (Exception e) {
		 * Toast.makeText(Creat_Account.this,e.getMessage(), 10000).show(); } }
		 * });
		 */

		/*
		 * delete.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { // TODO Auto-generated
		 * method stub
		 * 
		 * 
		 * if(db.delete("faculty1",null,null)!=-1); {
		 * Toast.makeText(Creat_Account.this, "successfully deleted",
		 * Toast.LENGTH_LONG).show(); } } });
		 */
	}
}
