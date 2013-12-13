package com.attendance_monitoring_system;



import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AttTable extends Activity
{

	SQLiteDatabase db;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.att_table);
		
		/*try
		{
			TextView date=(TextView)findViewById(R.id.date);
			TextView Class=(TextView)findViewById(R.id.Class);
			TextView sem=(TextView)findViewById(R.id.sem);
			TextView stp=(TextView)findViewById(R.id.stp);
			date.setWidth(80);
			Class.setWidth(70);
			sem.setWidth(70);
			stp.setWidth(80);
			
		}catch (Exception e) {
			Toast.makeText(this,e.getMessage(),2000).show();
		}*/
		
		Cursor c=null;
		
		
		int p=getIntent().getExtras().getInt("practical");
		int t=getIntent().getExtras().getInt("theory");
		int dp=	getIntent().getExtras().getInt("ivp");
		int dt=	getIntent().getExtras().getInt("ivt");
		final String name=getIntent().getExtras().getString("iname");
		//Toast.makeText(this,name, Toast.LENGTH_LONG).show();
		
		try
		{
			//Toast.makeText(this,name, 2000).show();
	        db=openOrCreateDatabase("FacultyDB",SQLiteDatabase.CREATE_IF_NECESSARY,null);
	        if(t==1)
	        {
	        	 //db.execSQL("Create Table theory(date date,present number)");         
	        	 
	        	c=db.rawQuery("SELECT * FROM t"+name,null);
	        }
	        if(dt==1)
	        {
	        	 //db.execSQL("Create Table theory(date date,present number)");         
	        	 c=db.rawQuery("SELECT * FROM t"+name,null);
	        }
	        
	        if(p==2)
	        {
	        	//db.execSQL("Create Table practical(date date,present number)");
	        	c=db.rawQuery("SELECT * FROM p"+name,null);
	        }
	        if(dp==2)
	        {
	        	//db.execSQL("Create Table practical(date date,present number)");
	        	c=db.rawQuery("SELECT * FROM p"+name,null);
	        }
		
	              
			c.moveToFirst();
			
			ScrollView sv=new ScrollView(this);
			sv.setBackgroundColor(Color.BLACK);
			
			TableLayout tl = new TableLayout(this);
			tl.setBackgroundColor(Color.BLACK);
			tl.setStretchAllColumns(true);  
		    tl.setShrinkAllColumns(true);
		    
		    TableRow tblr=new TableRow(this);
		    
		    TextView date=new TextView(this);
		    date.setText("Date");
		    date.setTextColor(Color.rgb(185,211,255));
		    date.setTypeface(Typeface.SERIF, Typeface.BOLD);
		    
		    TextView sem=new TextView(this);
		    sem.setText("Sem");
		    sem.setTextColor(Color.rgb(185,211,255));
		    sem.setTypeface(Typeface.SERIF, Typeface.BOLD);
		    
		    TextView cls=new TextView(this);
		    cls.setText("Class");
		    cls.setTextColor(Color.rgb(185,211,255));
		    cls.setTypeface(Typeface.SERIF, Typeface.BOLD);
		    
		    TextView stpr=new TextView(this);
		    stpr.setText("Present");
		    stpr.setTextColor(Color.rgb(185,211,255));
		    stpr.setTypeface(Typeface.SERIF, Typeface.BOLD);
		    
		    tblr.addView(date);
		    tblr.addView(sem);
		    tblr.addView(cls);
		    tblr.addView(stpr);
		   
		    tl.addView(tblr);
			while(!c.isAfterLast())
			{
				
	            
				String[] s=new String[4];
				s[0]=c.getString(0);
				s[1]=c.getString(1);
				s[2]=c.getString(2);
				s[3]=c.getString(3);
				
				TableRow tr =new TableRow(this);
	         	
	            TextView tdate=new TextView(this);
	            //tdate.setText(s[2]+"    "+s[0]+"             "+s[1]+"               "+s[3]);
	            //tdate.setTypeface(Typeface.SERIF, Typeface.BOLD);  
	            tdate.setText(s[2]);
	            tdate.setTextColor(Color.WHITE);
	            tr.addView(tdate);
	            
	            
	            TextView tpresent=new TextView(this);
	            tpresent.setText(s[0]);
	            tpresent.setTextColor(Color.WHITE);
	            tr.addView(tpresent);
	            
	            
	            TextView tsem=new TextView(this);
	            tsem.setText(s[1]);
	            tsem.setTextColor(Color.WHITE);
	            tr.addView(tsem);
	            
	            TextView tclass=new TextView(this);
	            tclass.setText(s[3]);
	            tclass.setTextColor(Color.WHITE);
	            tr.addView(tclass);
	            
	            
	            
	            tl.addView(tr, new TableLayout.LayoutParams(20,0));
	        
				c.moveToNext();
			}
			sv.addView(tl);
			setContentView(sv);
		
			c.close();
		}
		catch (Exception e) 
		{
			
		}
	
	}
}
