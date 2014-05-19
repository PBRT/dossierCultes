package com.example.dossiercultes;

import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Camera.*;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

public class FormationCameraActivity extends Activity implements
		SurfaceHolder.Callback {
	/** Called when the activity is first created. */
	private Camera camera;
	private SurfaceView surfaceCamera;
	private Boolean isPreview;
	private FileOutputStream stream;
	private Modele model;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.model=(Modele)this.getApplication();
		// On met l'application en plein écran et sans barre de titre
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		isPreview = false;

		// On applique notre layout
		setContentView(R.layout.activity_formation_camera);

		// On récupère notre surface pour le preview
		surfaceCamera = (SurfaceView) findViewById(R.id.surfaceViewCamera);

		// Quand on clique sur notre surface
		surfaceCamera.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// On prend une photo
				if (camera != null) {
					SavePicture();
				}

			}
		});

		// Méthode d'initialisation de la caméra
		InitializeCamera();
	}

	public void InitializeCamera() {
		// On attache nos retour du holder à notre activite
		surfaceCamera.getHolder().addCallback(this);

		// On spécifie le type du holder en mode SURFACE_TYPE_PUSH_BUFFERS
		surfaceCamera.getHolder().setType(
				SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	// Retour sur l'application
	@Override
	public void onResume() {
		super.onResume();
		camera = Camera.open();
	}

	// Mise en pause de l'application
	@Override
	public void onPause() {
		super.onPause();

		if (camera != null) {
			camera.release();
			camera = null;
		}
	}

	// Implémentation du surface holder

	// Quand la surface change
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

		// Si le mode preview est lancé alors on le stop
		if (isPreview) {
			camera.stopPreview();
		}
		// On récupère les parametres de la camera
		Camera.Parameters parameters = camera.getParameters();

		// On change la taille
		parameters.setPreviewSize(width, height);

		// On applique nos nouveaux parametres
		camera.setParameters(parameters);

		try {
			// On attache notre previsualisation de la camera au holder de la
			// surface
			camera.setPreviewDisplay(surfaceCamera.getHolder());
		} catch (IOException e) {
		}

		// On lance la previeuw
		camera.startPreview();

		isPreview = true;
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// On prend le controle de la camera
		if (camera == null)
			camera = Camera.open();
			camera.setDisplayOrientation(90);
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// On arrête la camera et on rend la main
		if (camera != null) {
			camera.stopPreview();
			isPreview = false;
			camera.release();
		}
	}
	
	private String getRealPathFromURI(Uri contentURI) {
		Cursor cursor = getContentResolver().query(contentURI, null, null, null, null); 
		cursor.moveToFirst(); 
		int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA); 
		return cursor.getString(idx); 
	}
	// Callback pour la prise de photo
	Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {

		public void onPictureTaken(byte[] data, Camera camera) {
			if (data != null) {
				// Enregistrement de votre image
				try {
					if (stream != null) {
						stream.write(data);
						stream.flush();
						stream.close();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				// On redémarre la prévisualisation
				camera.startPreview();
			}
		}
	};

	private void SavePicture() {
		try {
			SimpleDateFormat timeStampFormat = new SimpleDateFormat(
					"yyyy-MM-dd-HH.mm.ss");
			String fileName = "photo_" + timeStampFormat.format(new Date())
					+ ".jpg";

			// Metadata pour la photo
			ContentValues values = new ContentValues();
			values.put(Media.TITLE, fileName);
			values.put(Media.DISPLAY_NAME, fileName);
			values.put(Media.DESCRIPTION, "Image prise par FormationCamera");
			values.put(Media.DATE_TAKEN, new Date().getTime());
			values.put(Media.MIME_TYPE, "image/jpeg");

			// Support de stockage
			Uri taken = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI,values);
	
		   this.model.seturiCourant(taken);
		        
		    Log.d("CRAAS", this.getRealPathFromURI(this.model.geturiCourant()));

			// Ouverture du flux pour la sauvegarde
			stream = (FileOutputStream) getContentResolver().openOutputStream(
					taken);
			Intent intent4=new Intent(this, ArtisteActivity.class); 
            startActivity(intent4); 
            this.finish();

			camera.takePicture(null, pictureCallback, pictureCallback);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	
}