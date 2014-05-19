package com.example.dossiercultes;

import com.example.model.Dossier;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class SuppressionActivity extends Activity {
	
	public Modele model;
	private EditText edit1;
	private ImageView logo;
	private Bitmap photo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String s;
		setContentView(R.layout.activity_suppression);
		this.model=(Modele)this.getApplication();		
		this.logo = (ImageView)findViewById(R.id.imageView1);	
		/*s=this.model.geturiCourant().getPath();
		this.photo = BitmapFactory.decodeFile(s);
		this.logo.setImageBitmap(this.photo);*/
		this.logo.setImageURI(this.model.geturiCourant());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.suppression, menu);
		return true;
	}
    public void on_click_ret(View v){ 
        Intent intent4=new Intent(this, SupressActivity.class); 
        startActivity(intent4); 
    } 
  public void on_click_del(View v){ 
    	
    	String laChaine ="";
    	String password="1111";  
    	this.edit1=(EditText)this.findViewById(R.id.editText1);
    	laChaine=this.edit1.getText().toString();
    	
    	if(laChaine.equals(password)){    	
    		this.model.getpersCourant().supprDoss(this.model.getdossierCourant().gettId()-1);    		
    		Intent intent5=new Intent(this, ListActivity.class); 
            startActivity(intent5);     
            this.finish();
    	}
    	
    	else {
        Intent intent4=new Intent(this, SupressActivity.class); 
        startActivity(intent4); 
        this.finish();
    	}
    } 

}
