package com.example.chris.notasmultimedia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by chris on 30/10/2017.
 */

public class DaoNota {

    Context contexto;
    SQLiteDatabase midb;

    public DaoNota(Context contexto){
        this.contexto = contexto;
        this.midb = new MySQLiteOpenHelper(contexto).getWritableDatabase();
    }

    public long insert(Nota n){
        ContentValues cv = new ContentValues();

        cv.put(MySQLiteOpenHelper.COLUMNS_TABLE_NOTAS[1], n.getTitulo());
        cv.put(MySQLiteOpenHelper.COLUMNS_TABLE_NOTAS[2], n.getDescripcion());
        cv.put(MySQLiteOpenHelper.COLUMNS_TABLE_NOTAS[3], n.getFecha());

        return  midb.insert(MySQLiteOpenHelper.TABLE_NOTAS,null,cv);
    }

    public int recuperarId(){
        String selectQuery = "select max(" +MySQLiteOpenHelper.COLUMNS_TABLE_NOTAS[0]+") from "+MySQLiteOpenHelper.TABLE_NOTAS;
        SQLiteDatabase db =new MySQLiteOpenHelper(this.contexto).getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int id=0;
        if (cursor.moveToFirst()) {
            id=cursor.getInt(0);
        }
        return id;
    }

    public ArrayList<Nota> getAll(){
        ArrayList<Nota> lsN=null;
        Cursor cursor = midb.query(MySQLiteOpenHelper.TABLE_NOTAS,
                MySQLiteOpenHelper.COLUMNS_TABLE_NOTAS,
                null, null, null,null,null);

        if(cursor.moveToFirst()){
            lsN = new ArrayList<>();
            do{
                Nota c = new Nota();

                c.setIdNota(cursor.getInt(0));
                c.setTitulo(cursor.getString(1));
                c.setDescripcion(cursor.getString(2));
                c.setFecha(cursor.getString(3));

                lsN.add(c);

            }while (cursor.moveToNext());
        }
        return lsN;
    }

}
