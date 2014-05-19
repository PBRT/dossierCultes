package com.example.dossiercultes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class PersoActivity extends Activity {
	
	private TextView txt;
	private ListView lv2Liste;	
	private Modele model;
	private String l="";
	private ImageView logo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perso);
		this.model = (Modele)this.getApplication() ;		
		this.logo = (ImageView)findViewById(R.id.imageView1);		
		
		//Déclaration variable internes
		 ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
	     HashMap<String, String> map;	//Une hashmap pour chaques items
	    
		this.txt = (TextView) this.findViewById(R.id.textView2); 	   
		this.lv2Liste = (ListView)this.findViewById(R.id.listView1);
        this.txt.setText(this.model.getpersCourant().getNom()); 
        this.logo.setImageBitmap(this.model.getpersCourant().getPhoto());

        	for (int i=0; i<this.model.getpersCourant().getListe().size(); i++){	 	    
        		map = new HashMap<String, String>();
        		map.put("id", String.valueOf(this.model.getpersCourant().getListe().get(i).gettId()));
        		map.put("phrase", this.model.getpersCourant().getListe().get(i).getDossier());
        		listItem.add(map);	     
        	}	  

        
        
        
        	//On crée un adapter qui va afficher nos Hashmap (et donc items) avec le xml de affichage item
        	String [] attribut = new String[] {"phrase"};	//Contient les colonnes
        	int [] valeur= new int[] {R.id.phrase};	// Contient les id
	     
        	SimpleAdapter adapt = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichagepers,
        			attribut, valeur );
        
        	lv2Liste.setAdapter(adapt);
	    
        	lv2Liste.setOnItemClickListener(new OnItemClickListener() {	
			
        	public void onItemClick (AdapterView<?> a, View v, int position, long id) { 
        		int id3 = 0;
        		HashMap<String, String> map2 = (HashMap<String, String>) lv2Liste.getItemAtPosition(position);	
	    	
        		PersoActivity.this.model.setdossCourant(PersoActivity.this.model.getpersCourant().getListe().get(Integer.valueOf(map2.get("id"))-1));
        		Intent intent4=new Intent(PersoActivity.this, DossierActivity.class); 
        		startActivity(intent4); 	
        		PersoActivity.this.finish();
        	}

        	});	
        }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.perso, menu);
		return true;
	}
	
    public void onclickprec(View v){ 
        Intent intent4=new Intent(this, ListActivity.class); 
        startActivity(intent4); 
        this.finish();
    } 
    public void on_click_write(View v){ 
        Intent intent5=new Intent(this, WriteActivity.class); 
        startActivity(intent5); 
        this.finish();
    } 
    
    public void on_click_ret(View v){ 
        Intent intent5=new Intent(this, MainActivity.class); 
        startActivity(intent5); 
        this.finish();
    } 
	
	

}
