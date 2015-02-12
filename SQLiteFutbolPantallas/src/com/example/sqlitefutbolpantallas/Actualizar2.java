package com.example.sqlitefutbolpantallas;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Actualizar2 extends Activity {
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
		setContentView(R.layout.actualizar2);

		nombre = (EditText) findViewById(R.id.nombre2);
		apellido1 = (EditText) findViewById(R.id.apellido12);
		apellido2 = (EditText) findViewById(R.id.apellido22);
		dni = (EditText) findViewById(R.id.dni2);
		edad = (EditText) findViewById(R.id.edad2);
		sexo = (RadioButton) findViewById(R.id.sexo2);
		nivel = (Spinner) findViewById(R.id.nivel2);
		
		//Abrimos la base de datos 'DBFutbol' en modo escritura
        usdbh = new UsuariosSQLiteHelper(this, "DBFutbol", null, 1);
		db = usdbh.getWritableDatabase();
		
		String nif2 = getIntent().getStringExtra("dni");
				
		Cursor c = db.rawQuery("SELECT * FROM Futbol WHERE dni='"+nif2+"'", null);
		
		nombre.setText("");
		apellido1.setText("");
		apellido2.setText("");
		dni.setText("");
		edad.setText("");
		sexo.setText("");
		// nivel.setText("");
		
		if (c.moveToNext()) {
			String nom = c.getString(0);
			String ape = c.getString(1);
			String ape2 = c.getString(2);
			String nif = c.getString(3);
			String eda = c.getString(4);
			String sex = c.getString(5);
			
			nombre.append(nom);
			apellido1.append(ape);
			apellido2.append(ape2);
			dni.append(nif);
			edad.append(eda);
			sexo.append(sex);
		}
	}
  
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void actualizar(View view) {
		System.out.println(dni.getText().toString());
		String sql = "UPDATE Futbol SET nombre='" + nombre.getText().toString() + "', apellido1='" + apellido1.getText().toString() + "', apellido2='" + apellido2.getText().toString() + "', edad='" + edad.getText().toString() + "', sexo='" + sexo.getText().toString() + "' where dni='" + dni.getText().toString() + "'";
		db.execSQL(sql);
		Toast.makeText(Actualizar2.this, "Jugador actualizado correctamente.", Toast.LENGTH_LONG).show();
	}
	
	public void cancelar(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
