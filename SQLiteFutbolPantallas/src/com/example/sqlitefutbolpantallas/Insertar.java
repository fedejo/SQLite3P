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

public class Insertar extends Activity {
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
		setContentView(R.layout.insertar);

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
	
	private boolean validarDni(String dni){
		final String letra = "TRWAGMYFPDXBNJZSQVHLCKE";
		Boolean res = false;
		if(dni.toString().length() == 9){
			// res = true;
			for(int i = 0; i < dni.toString().length()-1; i++){
				res= res&&Character.isDigit(dni.toString().charAt(i));
			}
			Integer valor = new Integer(dni.toString().substring(0, 8));
			int aux = valor%23;
			Character letraReal = dni.toString().charAt(8);
			Character letraCalculada = letra.charAt(aux);
			if(letraReal == letraCalculada){
				res = true;
			}	
		}
		return res;
	}	
	
	public void enviar(View view) {
		ContentValues valores = new ContentValues();
		
		valores.put("apellido2", apellido2.getText().toString());
	    
		if (nombre.getText().toString().length() == 0) {
			Toast.makeText(this, "El campo nombre no puede estar vacío.", Toast.LENGTH_LONG).show();
		} else {
			valores.put("nombre", nombre.getText().toString());
			
			if (apellido1.getText().toString().length() == 0) {
				Toast.makeText(this, "El campo apellido 1 no puede estar vacío.", Toast.LENGTH_LONG).show();
			} else {
				valores.put("apellido1", apellido1.getText().toString());
				
				if (dni.getText().toString().length() == 0) {
					Toast.makeText(this, "El campo dni no puede estar vacío.", Toast.LENGTH_LONG).show();
				} else {
					Boolean es_correcto = validarDni(dni.getText().toString());
					if (es_correcto == false) {
						Toast.makeText(this, "DNI incorrecto.", Toast.LENGTH_LONG).show();
					} else {
						valores.put("dni", dni.getText().toString());
						
						if (edad.getText().toString().length() == 0) {
							Toast.makeText(this, "El campo edad no puede estar vacío.", Toast.LENGTH_LONG).show();
						} else {
							valores.put("edad", edad.getText().toString());
							
							if (sexo.getText().toString().length() == 0) {
								Toast.makeText(this, "El campo sexo no puede estar vacío.", Toast.LENGTH_LONG).show();
							} else {
								valores.put("sexo", sexo.getText().toString());

								if (nivel.getSelectedItem().toString().length() == 0) {
									Toast.makeText(this, "El campo categoría no puede estar vacío.", Toast.LENGTH_LONG).show();
								} else {
									valores.put("nivel", nivel.getSelectedItem().toString());
									
									db.insert("Futbol", null, valores);
									Toast.makeText(Insertar.this, "Jugador insertado correctamente.", Toast.LENGTH_LONG).show();
								}
							}
						}
					}
				}
			}
		}		
	}
	

	public void cancelar(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
