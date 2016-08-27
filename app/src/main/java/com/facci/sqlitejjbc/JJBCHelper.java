package com.facci.sqlitejjbc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.StringBuilderPrinter;

/**
 * Created by Benitez on 26/08/2016.
 */
public class JJBCHelper extends SQLiteOpenHelper {

    public static final String JJBC_NOMBRE = "CNE_DATPJJBC";
    public static final String TABLA_NOMBRE = "VOTANTES_DATPJJBC";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "APELLIDO";
    public static final String COL_4 = "RECINTO ELECTORAL";
    public static final String COL_5 = "AÑO NACIMIENTO";


    public JJBCHelper(Context context) {
        super(context, JJBC_NOMBRE, null, 1);
        SQLiteDatabase jjbc = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase jjbc) {
        jjbc.execSQL(String.format("create table %s (ID INTEGER PRIMARY KEY AUTOINCREMENT. %s TEXT, s% INTEGER)",TABLA_NOMBRE,COL_2,COL_3,COL_4, COL_5));

    }

    @Override
    public void onUpgrade(SQLiteDatabase jjbc, int i, int i1) {
        jjbc.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLA_NOMBRE));
        onCreate(jjbc);
    }

    public boolean insertar(String nombre, String apellido, String recientoElectoral, int AnoNacimiento){
        SQLiteDatabase jjbc = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recientoElectoral);
        contentValues.put(COL_5,AnoNacimiento);
        long resultado = jjbc.insert(TABLA_NOMBRE,null,contentValues);

        if(resultado == -1)
            return false;
        else
            return true;

    }
    public Cursor selectVerTodos(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("select * from %s",TABLA_NOMBRE),null);
        return  res;
    }


    public boolean modificarRegistro(String id,String nombre, String apellido, String recientoElectoral, int AnoNacimiento){

        SQLiteDatabase jjbc = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recientoElectoral);
        contentValues.put(COL_5,AnoNacimiento);

        jjbc.update(TABLA_NOMBRE,contentValues,"id = ?",new String[]{id});

        return true;
    }
}