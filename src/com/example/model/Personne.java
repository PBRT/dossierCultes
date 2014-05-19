package com.example.model;

import java.util.ArrayList;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;



public class Personne {
	
	/* Attributs */
	private static int nb_auteur=0;
	private int id;
	private String nom;
	private String phrase;
	private Bitmap photo;
	private String chemin;
	private ArrayList<Dossier> laListe;
	
	public Personne (String n, String chem, String phrase, int im) {	
		
		if(im==0){
			if(chem.equals("")){		//Si pas d'image
				this.chemin="/sdcard/ballack.jpg";
				this.photo = BitmapFactory.decodeFile(this.chemin);
			}
			else{
				this.chemin="/sdcard/"+chem+".jpg";
				this.photo = BitmapFactory.decodeFile(this.chemin);
			}
		}
		
		if(im==1){
			this.photo = BitmapFactory.decodeFile(chem);
			this.chemin=chem;
		}

		System.out.println("Chemin :::: " +chem);
		this.nom=n;
		this.phrase=phrase;				
		this.laListe=new ArrayList<Dossier>();
		this.id=this.selectId();
		System.out.println("Valeur de l'ID ======================="+ this.id);
	}
	
	public int selectId(){
		int i=Personne.nb_auteur;
		Personne.nb_auteur++;
		return i;
	}
	
	public String getNom() {
		return this.nom;
	}
	public int getId(){
		return this.id;
	}
	public String getPhrase(){
		return this.phrase;
	}
	
	public String getChemin(){
		return this.chemin;
	}
	
	public Bitmap getPhoto(){
		return this.photo;
	}
	
	public ArrayList<Dossier> getListe(){
		return this.laListe;
	}
	public void setListe(ArrayList<Dossier> dis){
		for (int i=0; i<dis.size(); i++){
			this.laListe.add(dis.get(i));			
		}
	}

	public String toString(){		
		return this.nom;		
	}
	public void supprDoss(int id){		
	
		this.getListe().remove(id);	
		
		if(this.getListe().size()>0){	
			for(int i=0; i<this.getListe().size(); i++){
				this.getListe().get(i).settId(i+1);
			}		
		}

		
	}
	
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
	    int width = bm.getWidth();
	    int height = bm.getHeight();
	    float scaleWidth = ((float) newWidth) / width;
	    float scaleHeight = ((float) newHeight) / height;
	    // CREATE A MATRIX FOR THE MANIPULATION
	    Matrix matrix = new Matrix();
	    // RESIZE THE BIT MAP
	    matrix.postScale(scaleWidth, scaleHeight);

	    // "RECREATE" THE NEW BITMAP
	    Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
	    return resizedBitmap;
	}
}
