package com.example.dossiercultes;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.model.Personne;

import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class ListActivity extends Activity {

	private Modele model;
	private ListView lvListe;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);	
		
		// Recupération du modèle		
		this.model=(Modele)this.getApplication();

		
		//Récupération de la liste		
		lvListe = (ListView)findViewById(R.id.listView1);
		
		//Déclaration variable internes
		 ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
	     HashMap<String, String> map;	//Une hashmap pour chaques items
	     
	     //Création des objets à afficher, une hashmap pour chacun, on ajoute tout les artistes
	     
	     for (int i=0; i<this.model.getListe().size(); i++){
	    	 	    
	    	 map = new HashMap<String, String>();
	    	 map.put("id", String.valueOf(this.model.getListe().get(i).getId()));
	    	 map.put("titre", this.model.getListe().get(i).getNom());
	    	 map.put("description", this.model.getListe().get(i).getPhrase());
	    	 map.put("img", this.model.getListe().get(i).getChemin());	    	
	    	 listItem.add(map);
	     
	     }		
	     
	     //On crée un adapter qui va afficher nos Hashmap (et donc items) avec le xml de affichage item
	     String [] attributs = new String[] {"img", "titre", "description"};	//Contient les colonnes
	     int [] valeurs = new int[] {R.id.img, R.id.titre, R.id.description};	// Contient les id
	     
	     SimpleAdapter adapt = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichageitem,
	     attributs, valeurs );
		
	   //On connecte notre listView a cet adapteur
	    lvListe.setAdapter(adapt);

	    lvListe.setOnItemClickListener(new OnItemClickListener() {	
			
	    	public void onItemClick (AdapterView<?> a, View v, int position, long id) { 
	    	int id2 = 0;
	    	HashMap<String, String> map2 = (HashMap<String, String>) lvListe.getItemAtPosition(position);        	
	    	ListActivity.this.model.setpersCourant(ListActivity.this.model.getListe().get(Integer.valueOf(map2.get("id"))));
	    	Intent intent3=new Intent(ListActivity.this, PersoActivity.class); 
	        startActivity(intent3); 
	     
	    		
	    }


	    });	
	
	}
	



}
