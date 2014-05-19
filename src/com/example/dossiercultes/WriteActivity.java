package com.example.dossiercultes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.model.Dossier;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class WriteActivity extends Activity {

	
	private Modele model;
	private EditText edit1;
	private EditText edit2;
	private EditText edit3;	
	private TextView txt;
	private Dossier led;
	
	private int mYear;
	private int mMonth;
	private int mDay;

	private TextView mDateDisplay;
	private Button mPickDate;

	static final int DATE_DIALOG_ID = 0;
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write);
		this.model = (Modele)this.getApplication() ;
		
		this.txt = (TextView) this.findViewById(R.id.textView2);
		this.txt.setText(this.model.getpersCourant().getNom());
		
		mDateDisplay = (TextView) findViewById(R.id.showMyDate);        
	    mPickDate = (Button) findViewById(R.id.myDatePickerButton);

	    mPickDate.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            showDialog(DATE_DIALOG_ID);
	        }
	    });

	    // get the current date
	    final Calendar c = Calendar.getInstance();
	    mYear = c.get(Calendar.YEAR);
	    mMonth = c.get(Calendar.MONTH);
	    mDay = c.get(Calendar.DAY_OF_MONTH);

	    // display the current date
	    affichDisplay();
	    updateDisplay();
		
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.write, menu);
		return true;
	}
	
	private DatePickerDialog.OnDateSetListener mDateSetListener =
		    new DatePickerDialog.OnDateSetListener() {
		        public void onDateSet(DatePicker view, int year, 
		                              int monthOfYear, int dayOfMonth) {
		            mYear = year;
		            mMonth = monthOfYear;
		            mDay = dayOfMonth;
		            updateDisplay();
		        }
   };
   
   @Override
   protected Dialog onCreateDialog(int id) {
      switch (id) {
      case DATE_DIALOG_ID:
         return new DatePickerDialog(this,
                   mDateSetListener,
                   mYear, mMonth, mDay);
      }
      return null;
   }
   
	private Date affichDisplay() {
		
		Date d = new Date (mYear,mMonth,mDay);
		Log.d("DAAAAAAAAAAATTE", d.toString());
		return d;
	   
	}
	private void updateDisplay() {
	    this.mDateDisplay.setText(
	        new StringBuilder()
	                // Month is 0 based so add 1
	                .append(mMonth + 1).append("-")
	                .append(mDay).append("-")
	                .append(mYear).append(" "));
	}
	
    public void on_click_retour(View v){ 
        Intent intent5=new Intent(this, PersoActivity.class); 
        startActivity(intent5); 
        this.finish();
    } 

    public void on_click_enregistrer(View v){ 
    	
    	
    	String laChaine ="";
		String laChaine2 ="";		
		Date ladate;
		
		
		
		this.edit1=(EditText) this.findViewById(R.id.editText1);
		this.edit2=(EditText) this.findViewById(R.id.editText2);	
		
		laChaine=this.edit1.getText().toString();
		laChaine2=this.edit2.getText().toString();	    	
    	
    	ladate=affichDisplay();   
    	this.led=new Dossier(laChaine,laChaine2,ladate, this.model.getpersCourant().getListe().size()+1);    	
    	this.model.getpersCourant().getListe().add(this.led);
    	
    	for (int i=0; i<this.model.getpersCourant().getListe().size(); i++){
			
			Log.d("TTTeeee",this.model.getpersCourant().getListe().get(i).getDossier());
			Log.d("TTTeeeee",String.valueOf(this.model.getpersCourant().getListe().get(i).gettId()));
			Log.d("TTTeeee",this.model.getpersCourant().getListe().get(i).getContexte());
		}
    	
        Intent intent5=new Intent(this, PersoActivity.class); 
        startActivity(intent5); 
        this.finish();
    } 
}
