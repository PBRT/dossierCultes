package com.example.model;

import java.util.Date;

import android.util.Log;

public class Dossier {

	// Attribut

	private String titre;
	private String contexte;
	private Date date;
	private int idd;
	
	public Dossier(String l, String ctx, Date d, int i){
		this.titre=l;
		this.contexte=ctx;
		this.date=d;
		this.idd=i;
		
	}

	public int gettId(){
		return this.idd;
	}
	public String getDossier(){
		return this.titre;
	}
	public void settId(int i){
		this.idd=i;
	}
	public String getContexte(){
		return this.contexte;
	}
	public Date getLaDate(){
		return this.date;
	}
	
	public String datetoString() {
		return "Le " + this.getLaDate().getDay()+"/"+this.getLaDate().getMonth()+"/"+this.getLaDate().getYear()+" a "+this.getLaDate().getHours()+"h"+this.getLaDate().getMinutes();
	}
	
	public String toString(){
		return this.titre+" "+this.contexte+" "+this.datetoString();
	}
	
	
}

