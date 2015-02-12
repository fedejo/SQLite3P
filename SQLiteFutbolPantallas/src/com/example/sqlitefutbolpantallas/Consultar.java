package com.example.sqlitefutbolpantallas;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Consultar extends Activity {
	private EditText nombre;
	private EditText apellido1;
	private EditText apellido2;
	private EditText dni;
	private EditText edad;
	private RadioButton sexo;
	private Spinner nivel;
	
	UsuariosSQLiteHelper usdbh;
	SQLiteDatabase db;	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consultar);

		nombre = (EditText) findViewById(R.id.nombre);
		apellido1 = (EditText) findViewById(R.id.apellido1);
		apellido2 = (EditText) findViewById(R.id.apellido2);
		dni = (EditText) findViewById(R.id.dni);
		edad = (EditText) findViewById(R.id.edad);
		sexo = (RadioButton) findViewById(R.id.sexo);
		nivel = (Spinner) findViewById(R.id.nivel);
		
		//Abrimos la base de datos 'DBFutbol' en modo escritura
        usdbh = new UsuariosSQLiteHelper(this, "DBFutbol", null, 1);
		db = usdbh.getWritableDatabase();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void consultar(View view) {
		Intent intent = new Intent(this, Consultar2.class);
		
		intent.putExtra("dni", dni.getText().toString());
		intent.putExtra("nombre", nombre.getText().toString());
		intent.putExtra("apellido1", apellido1.getText().toString());
		intent.putExtra("apellido2", apellido2.getText().toString());
		intent.putExtra("edad", edad.getText().toString());
		intent.putExtra("sexo", sexo.getText().toString());
		intent.putExtra("nivel", nivel.getSelectedItem().toString());
		
		startActivity(intent);
	}
	
	public void cancelar(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
