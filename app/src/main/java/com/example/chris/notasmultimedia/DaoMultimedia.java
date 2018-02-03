package com.example.chris.notasmultimedia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by chris on 22/11/2017.
 */

public class DaoMultimedia {
    Context contexto;
    SQLiteDatabase midb;

    public DaoMultimedia(Context contexto){
        this.contexto = contexto;
        this.midb = new MySQLiteOpenHelper(contexto).getWritableDatabase();
    }

    public long insert(Multimedia m){
        ContentValues cv = new ContentValues();

        cv.put(MySQLiteOpenHelper.COLUMNS_TABLE_MULTIMEDIA[1], m.getTipo());
        cv.put(MySQLiteOpenHelper.COLUMNS_TABLE_MULTIMEDIA[2], m.getUri());
        cv.put(MySQLiteOpenHelper.COLUMNS_TABLE_MULTIMEDIA[3], m.getIdNota());

        return  midb.insert(MySQLiteOpenHelper.TABLE_MULTIMEDIA,null,cv);
    }

    public ArrayList<Multimedia> getAll(){
        ArrayList<Multimedia> lstM=null;
        Cursor cursor = midb.query(MySQLiteOpenHelper.TABLE_MULTIMEDIA,
                MySQLiteOpenHelper.COLUMNS_TABLE_MULTIMEDIA,
                null, null, null,null,null);

        if(cursor.moveToFirst()){
            lstM = new ArrayList<>();
            do{
                Multimedia m=new Multimedia();

                m.setIdMultimedia(cursor.getInt(0));
                m.setTipo(cursor.getInt(1));
                m.setUri(cursor.getString(2));
                m.setIdNota(cursor.getInt(3));

                lstM.add(m);

            }while (cursor.moveToNext());
        }
        return lstM;
    }
}
