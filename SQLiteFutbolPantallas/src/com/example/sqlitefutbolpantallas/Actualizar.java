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

public class Actualizar extends Activity {
	private EditText dni;
	
	UsuariosSQLiteHelper usdbh;
	SQLiteDatabase db;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actualizar);
		
		dni = (EditText) findViewById(R.id.dniTxt2);
		
		usdbh = new UsuariosSQLiteHelper(this, "DBFutbol", null, 1);
		db = usdbh.getWritableDatabase();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void actualiza(View view) {
		Intent intent = new Intent(this, Actualizar2.class);
		intent.putExtra("dni", dni.getText().toString());
		startActivity(intent);
	}
}
