package com.example.dossiercultes;

import java.io.File;

import com.example.model.Personne;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ArtisteActivity extends Activity {

	private Modele model;
	private EditText edit1;
	private EditText edit2;
	private TextView txt;
	private boolean takePicture=false;
	private int mode_ac = 0;
	private String leCheminExpl="";


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artiste);
		this.model=(Modele)this.getApplication();
		this.txt = (TextView) findViewById(R.id.textView3);
		this.edit1=(EditText) findViewById(R.id.editText1);
		this.edit2=(EditText) findViewById(R.id.editText2);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.artiste, menu);
		return true;
	}
    public void on_click_cancel(View v){ 
        Intent intent4=new Intent(this, MainActivity.class); 
        startActivity(intent4); 
        this.finish();
        
     } 
    
    public void on_click_t(View v){ 
        //Intent intent4=new Intent(this, FormationCameraActivity.class); 
       // startActivity(intent4); 
    	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    	 
    	File fichier = new File(Environment.getExternalStorageDirectory(), this.edit1.getText()+".jpg");
    	Uri chemin = Uri.fromFile(fichier);
    	 
    	intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, chemin);
    	 
    	this.startActivityForResult(intent, 0);    	
    	this.txt.setText(chemin.getPath());
    	this.takePicture=true;
    	this.mode_ac=0;
        
     } 
    
    public void on_click_t2(View v){ 
    	Intent mediaChooser =  new Intent(Intent.ACTION_GET_CONTENT);
        mediaChooser.setType("image/*");
        startActivityForResult(mediaChooser, 1);   
       
        this.takePicture=true;  
        this.mode_ac=1;
        
     } 
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	 
		if (resultCode == Activity.RESULT_OK) {
			if (data != null && data.getData() != null) {
				Uri uri=data.getData();				
				this.leCheminExpl=getRealPathFromURI(uri);
			}
		}
	}
    
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
       
    
    
    public void on_click_save(View v){ 
    	
    	String laChaine ="";
    	String laChaine2 ="";
    	String laChaine3 ="";
    	int chemin=0;
    	
    	
    	Personne p;
    	
    	this.edit1=(EditText) this.findViewById(R.id.editText1);
    	this.edit2=(EditText) this.findViewById(R.id.editText2);    
    	
    	laChaine=this.edit1.getText().toString();
		laChaine2=this.edit2.getText().toString();	 
		txt.setText("Aucune image selectionnée");
		
		if(this.takePicture==false){
			p=new Personne(laChaine,"", laChaine2,0);		//Par défaut, on affiche ballack
			this.model.getListe().add(p);
		}
		else if (this.takePicture==true){
			if(this.mode_ac==0){
				p=new Personne(laChaine,this.edit1.getText().toString(), laChaine2,0);
				this.model.getListe().add(p);
			}
			if (this.mode_ac==1) {
				p=new Personne(laChaine,this.leCheminExpl, laChaine2,1);
				this.model.getListe().add(p);		
			}
			
			
			
		}		

        Intent intent4=new Intent(this, MainActivity.class); 
        startActivity(intent4); 
        this.finish();
     } 
}
