package com.example.chris.notasmultimedia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chris on 28/10/2017.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String  TABLE_NOTAS="notas";
    public static final String  TABLE_MULTIMEDIA="multimedias";
    public static final String[] COLUMNS_TABLE_NOTAS = {"id_Nota", "titulo","descripcion","fecha"};
    public static final String[] COLUMNS_TABLE_MULTIMEDIA = {"id_Multi", "tipo","uri","id_Nota"};
    private static final int VERSION_DB = 1;

    private  final String SCRIPT_TABLE_NOTAS="create table " + TABLE_NOTAS + "(" +
            COLUMNS_TABLE_NOTAS[0] + " integer primary key autoincrement, " +
            COLUMNS_TABLE_NOTAS[1] + " varchar(100) null, " +
            COLUMNS_TABLE_NOTAS[2] + " varchar(100) null, " +
            COLUMNS_TABLE_NOTAS[3] + " varchar(100) null " +
            ");";

    private  final String SCRIPT_TABLE_MULTIMEDIA="create table " + TABLE_MULTIMEDIA + "(" +
            COLUMNS_TABLE_MULTIMEDIA[0] + " integer primary key autoincrement, " +
            COLUMNS_TABLE_MULTIMEDIA[1] + " integer not null, " +
            COLUMNS_TABLE_MULTIMEDIA[2] + " varchar(100) not null, " +
            COLUMNS_TABLE_MULTIMEDIA[3] + " integer, "
            + " FOREIGN KEY (" + COLUMNS_TABLE_MULTIMEDIA[3]+") REFERENCES "+TABLE_NOTAS+"("+COLUMNS_TABLE_NOTAS[0]+"));";


    public MySQLiteOpenHelper(Context contexto){
        super(contexto, "miBD", null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_TABLE_NOTAS);
        sqLiteDatabase.execSQL(SCRIPT_TABLE_MULTIMEDIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
