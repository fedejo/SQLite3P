package com.example.sqlitefutbolpantallas;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class Consultar2 extends Activity {
	private TextView texto;
	
	UsuariosSQLiteHelper usdbh;
	SQLiteDatabase db;	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consultar2);

		texto = (TextView) findViewById(R.id.text);
		
		//Abrimos la base de datos 'DBFutbol' en modo escritura
        usdbh = new UsuariosSQLiteHelper(this, "DBFutbol", null, 1);
		db = usdbh.getWritableDatabase();
		
		String nif2 = getIntent().getStringExtra("dni");
		String nom2 = getIntent().getStringExtra("nombre");
		String a1 = getIntent().getStringExtra("apellido1");
		String a2 = getIntent().getStringExtra("apellido2");
		String edad2 = getIntent().getStringExtra("edad");
		String sexo2 = getIntent().getStringExtra("sexo");
		String nivel2 = getIntent().getStringExtra("nivel");
		
		String sql = "select * from Futbol where 1=1 ";
		
		if (!nom2.equals("")) {
			sql += "and nombre='" + nom2 + "'";
			
			if (!a1.equals("")) {
				sql += " or apellido1='" + a1 + "'";
			}
			
			if (!a2.equals("")) {
				sql += " or apellido2='" + a2 + "'";
			}
			
			if (!nif2.equals("")) {
				sql += " or dni='" + nif2 + "'";
			}
			
			if (!edad2.equals("")) {
				sql += " or edad=" + edad2;
			}
			
			if (!sexo2.equals("")) {
				sql += " or sexo='" + sexo2 + "'";
			}
			
			if (!nivel2.equals("")) {
				sql += " or nivel='" + nivel2 + "'";
			}
		} else if (!a1.equals("")) {
			sql += "and apellido1='" + a1 + "'";
			
			if (!a2.equals("")) {
				sql += " or apellido2='" + a2 + "'";
			}
			
			if (!nif2.equals("")) {
				sql += " or dni='" + nif2 + "'";
			}
			
			if (!edad2.equals("")) {
				sql += " or edad=" + edad2;
			}
			
			if (!sexo2.equals("")) {
				sql += " or sexo='" + sexo2 + "'";
			}
			
			if (!nivel2.equals("")) {
				sql += " or nivel='" + nivel2 + "'";
			}
		} else if (!a2.equals("")) {
			sql += "and apellido2='" + a2 + "'";
			
			if (!nif2.equals("")) {
				sql += " or dni='" + nif2 + "'";
			}
			
			if (!edad2.equals("")) {
				sql += " or edad=" + edad2;
			}
			
			if (!sexo2.equals("")) {
				sql += " or sexo='" + sexo2 + "'";
			}
			
			if (!nivel2.equals("")) {
				sql += " or nivel='" + nivel2 + "'";
			}
		} else if (!nif2.equals("")) {
			sql += "and dni='" + nif2 + "'";
			
			if (!edad2.equals("")) {
				sql += " or edad=" + edad2;
			}
			
			if (!sexo2.equals("")) {
				sql += " or sexo='" + sexo2 + "'";
			}
			
			if (!nivel2.equals("")) {
				sql += " or nivel='" + nivel2 + "'";
			}
		} else if (!edad2.equals("")) {
			sql += "and edad=" + edad2;
			
			if (!sexo2.equals("")) {
				sql += " or sexo='" + sexo2 + "'";
			}
			
			if (!nivel2.equals("")) {
				sql += " or nivel='" + nivel2 + "'";
			}
		} else if (!sexo2.equals("")) {
			sql += "and sexo='" + sexo2 + "'";
			
			if (!nivel2.equals("")) {
				sql += " or nivel='" + nivel2 + "'";
			}
		} else if (!nivel2.equals("")) {
			sql += "and nivel='" + nivel2 + "'";
		}
		
		sql += ";";
				
		texto.setText(sql + "\n\n");
		
		Cursor c = db.rawQuery(sql, null);
		
		if (c.moveToFirst()) {
			 do {
				 String nom = c.getString(0);
					String ape = c.getString(1);
					String ape2 = c.getString(2);
					String nif = c.getString(3);
					String eda = c.getString(4);
					String sex = c.getString(5);
					String niv = c.getString(6);
					texto.append("Nombre: " + nom + " - Apellidos: " + ape + " " + ape2 + " - DNI: " + nif + " - Edad: " + eda + " - Sexo: " + sex + " - Nivel: " + niv + "\n");
		     } while(c.moveToNext());
		}
	}
  
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
