package com.example.dossiercultes;

import java.util.ArrayList;
import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
import android.view.Menu;

import com.example.model.Dossier;
import com.example.model.Personne;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Modele extends Application{

	private ArrayList<Personne> listeP = new ArrayList<Personne>();
	private Personne persCourant;
	private Dossier dossierCourant;
	private Uri uriCourant;
	
	public Modele(){	
		
		super();
		
		@SuppressWarnings("deprecation")
		Date d= new Date(2012,11,06);
		
		
		System.out.println("TEST");

		
		this.listeP=new ArrayList<Personne>();
		this.listeP.add(new Personne("Pitou", "pitou", "Cochon pas cher",0));
		this.listeP.add(new Personne("Titou", "titou", "Change pas de main...",0));
		this.listeP.add(new Personne("Manu", "manu", "Et voila, je suis plus un pinard",0));
		this.listeP.add(new Personne("Mast", "mas", "Un peu de dignité mas",0));	
		this.listeP.add(new Personne("Fritzz", "fritz", "Snu plata",0));
		
		Dossier d1 = new Dossier("Le dossier 1a","Description ",d, this.listeP.get(2).getListe().size()+1 );
		Dossier d2 = new Dossier("Le dossier 2","Description2", d, this.listeP.get(2).getListe().size()+1);
		Dossier d3 = new Dossier("Le dossier 3","Description3", d, this.listeP.get(2).getListe().size()+1);
		Dossier d4 = new Dossier("Le dossier 4","Description4", d, this.listeP.get(2).getListe().size()+1);
		Dossier d5 = new Dossier("Le dossier 5","Description5", d, this.listeP.get(2).getListe().size()+1);
		Dossier d6 = new Dossier("Le dossier 6","Description6", d, this.listeP.get(2).getListe().size()+1);
		Dossier d7 = new Dossier("Le dossier 7","Description7", d, this.listeP.get(2).getListe().size()+1);
		Dossier d8 = new Dossier("Le dossier 8","Description8", d, this.listeP.get(2).getListe().size()+1);
		Dossier d9 = new Dossier("Le dossier 9","Description9", d, this.listeP.get(2).getListe().size()+1);
		Dossier d10 = new Dossier("Le dossier 10","Description10", d, this.listeP.get(2).getListe().size()+1);
		
		ArrayList<Dossier> lis = new ArrayList<Dossier>();
		lis.add(d1);
		lis.add(d2);	
		lis.add(d3);
		lis.add(d4);
		lis.add(d5);
		lis.add(d6);
		lis.add(d7);
		lis.add(d8);
		lis.add(d9);
		lis.add(d10);
		
		
		
		this.listeP.get(2).setListe(lis);
		
	  
		
	
		
	}

	public ArrayList<Personne> getListe(){
		return this.listeP;
	}
	public Personne getpersCourant(){
		return this.persCourant;
	}
	public Dossier getdossierCourant(){
		return this.dossierCourant;
	}
	public void setdossCourant(Dossier d){
		this.dossierCourant=new Dossier("","",new Date(0,0,0),0);
		this.dossierCourant=d;
	}
	public void setpersCourant(Personne p){
		this.persCourant=p;
	}
	public void seturiCourant(Uri i){
		this.uriCourant = i;
	}
	public Uri geturiCourant(){
		return this.uriCourant;
	}
	
	public int findIdItem(ArrayList<Personne> lis, String nom, String phra){
		
		int i=0;
		boolean boole=false;
		
		while((i<lis.size())&&(boole==false)){
			if((lis.get(i).getNom()==nom)&&(lis.get(i).getPhrase()==phra)&&(boole==false)){			
				boole=true;		
			}
		}
		
		return i;
	}
	 
}