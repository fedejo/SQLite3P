package com.example.sqlitefutbolpantallas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
 
public class UsuariosSQLiteHelper extends SQLiteOpenHelper {
 
    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE Futbol (nombre TEXT, apellido1 TEXT, apellido2 TEXT, dni TEXT, edad INTEGER, sexo TEXT, nivel TEXT)";
 
    public UsuariosSQLiteHelper(Context contexto, String nombre,
                               CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creaci�n de la tabla
        db.execSQL(sqlCreate);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //Se elimina la versi�n anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Futbol");
 
        //Se crea la nueva versi�n de la tabla
        db.execSQL(sqlCreate);
    }
}
