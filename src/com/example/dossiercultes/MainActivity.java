package com.example.dossiercultes;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	private Modele model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main);      
        this.model = (Modele)this.getApplication() ;
        //this.model.seturiCourant("/sdcard/fritz");
       /* Uri ur = Uri.parse("/sdcard/bb.mp3");
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), ur);
        mediaPlayer.start();
        mediaPlayer.isPlaying();*/
        
    }
    
    public class MusixActivity extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
        	
        	Uri ur = Uri.parse("/sdcard/bb.mp3");
            MediaPlayer player = MediaPlayer.create(MainActivity.this, ur); 
            player.setLooping(true); // Set looping 
            player.setVolume(100,100); 
            player.start(); 
            return null;
            

        }

    }
    
    public void on_click_x(View v){ 
        Intent intent4=new Intent(this, ListActivity.class); 
        startActivity(intent4);
        this.finish();
    } 
    public void on_click_create(View v){ 
        Intent intent4=new Intent(this, ArtisteActivity.class); 
        startActivity(intent4);
        this.finish();
    } 


    
}
