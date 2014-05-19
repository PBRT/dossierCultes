package com.example.dossiercultes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class SupressActivity extends Activity {

	
	private Modele model;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_supress);
		this.model = (Modele)this.getApplication() ;
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.supress, menu);
		return true;
	}
	
    public void on_click_ret(View v){ 
        Intent intent4=new Intent(this, DossierActivity.class); 
        startActivity(intent4); 
        this.finish();
    } 
    public void on_click_suppr(View v){ 
       Intent intent4=new Intent(this, SuppressionActivity.class); 
       startActivity(intent4); 
       this.finish();
    } 

}
