package com.example.dossiercultes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class DossierActivity extends Activity {

	private TextView txt1;
	private TextView txt2;
	private TextView txt3;
	private Modele model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dossier);
		this.model = (Modele)this.getApplication() ;
		
		this.txt1 = (TextView) this.findViewById(R.id.textView2);
		this.txt2 = (TextView) this.findViewById(R.id.textView5);
		this.txt3 = (TextView) this.findViewById(R.id.textView4);

		this.txt1.setText(this.model.getdossierCourant().getDossier()); 
		this.txt2.setText(this.model.getdossierCourant().getContexte()); 
		this.txt3.setText(this.model.getdossierCourant().datetoString()); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dossier, menu);
		return true;
	}

    public void on_click_retour(View v){ 
        Intent intent4=new Intent(this, PersoActivity.class); 
        startActivity(intent4); 
        this.finish();
    }
    public void on_click_suppr(View v){ 
        Intent intent4=new Intent(this, SupressActivity.class); 
        startActivity(intent4); 
        this.finish();
    }

}
