package com.attendance_monitoring_system;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FLogin extends Activity {
	Button btnsign , bshowpass;
	EditText uid, pass ,efname ,efuserid;
	Intent tofcontrol;
	SQLiteDatabase db;
	
	
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flogin);
		
		
		try
		{
			db=openOrCreateDatabase("FacultyDB",SQLiteDatabase.CREATE_IF_NECESSARY,null);	      
			}catch (Exception e) {
			
        }

		
		btnsign = (Button) findViewById(R.id.btnsignin);
		bshowpass=(Button)findViewById(R.id.bshowpass);
		uid = (EditText) findViewById(R.id.uid);
		pass = (EditText) findViewById(R.id.pass);
		efname=(EditText)findViewById(R.id.efname);
		efuserid=(EditText)findViewById(R.id.efuserid);
		
		bshowpass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				try
				{
					Cursor c=db.rawQuery("SELECT * FROM faculties where userid='"+efuserid.getText()+"' and name='"+efname.getText()+"'",null);
					c.moveToFirst();
					int flag=0;
					while(!c.isAfterLast())
					{
					flag=1;
					String[] s=new String[4];
					s[0]=c.getString(0);
					s[1]=c.getString(1);
					s[2]=c.getString(2);
					s[3]=c.getString(3);
		
					Toast.makeText(FLogin.this,"Your password is: "+s[2],Toast.LENGTH_LONG).show();
					uid.setText(efuserid.getText());
					pass.setText(s[2]);
					c.moveToNext();
					}
					if(flag==0)
					{
						Toast.makeText(FLogin.this,"Invalid username or userid",Toast.LENGTH_LONG).show();
					}
					efname.setText("");
					efuserid.setText("");
				}catch (Exception e) {
					Toast.makeText(FLogin.this,"Invalid username or userid",Toast.LENGTH_LONG).show();
				}
			}
		});
		
		btnsign.setOnClickListener(new Fcntrl());
		
		tofcontrol = new Intent(this, Fcontrol.class);

	}

	public class Fcntrl implements OnClickListener {


		//String userid=uid.getText().toString();
		//String password=pass.getText().toString();
		public void onClick(View v) {
			
			try
			{
				Cursor c=db.rawQuery("SELECT * FROM faculties where userid='"+uid.getText()+"' and password='"+pass.getText()+"'",null);
				
				int flag=1;
				c.moveToFirst();
				while(!c.isAfterLast())
				{
					flag=0;
		            
					String[] s=new String[4];
					s[0]=c.getString(0);
					s[1]=c.getString(1);
					s[2]=c.getString(2);
					s[3]=c.getString(3);
					
					
					tofcontrol.putExtra ("iname",s[1].toString());
					
					c.moveToNext();
				}
				if(flag==1)
				{
					Toast.makeText(FLogin.this,"Invalid userid or password", Toast.LENGTH_LONG).show();
					pass.setText("");
				}
				else if(flag==0)
				{	startActivity(tofcontrol);
				 	FLogin.this.finish();
				}
				c.close();
				
			}catch (Exception e) {
				
				Toast.makeText(FLogin.this,"Invalid userid or password", Toast.LENGTH_LONG).show();
			}
		}

	}

}
