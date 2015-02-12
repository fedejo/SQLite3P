package com.example.sqlitefutbolpantallas;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Eliminar extends Activity {
	private EditText nif;
	UsuariosSQLiteHelper usdbh;
	SQLiteDatabase db;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eliminar);
		
		nif = (EditText) findViewById(R.id.dniElimTxt);
		
		//Abrimos la base de datos 'DBFutbol' en modo escritura
        usdbh = new UsuariosSQLiteHelper(this, "DBFutbol", null, 1);
		db = usdbh.getWritableDatabase();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void eliminar(View view) {
		Intent intent = new Intent(this, Eliminar2.class);
		intent.putExtra("dni", nif.getText().toString());
		startActivity(intent);
	}
}
