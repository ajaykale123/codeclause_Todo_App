package com.example.to_doapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME  ="taskDatabase";
    private static final String TABLE_NAME  ="taskTABLE";
    private static final String ID  ="id";
    private static final String NAME  ="NAME";
    private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME+"("+
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAME+" TEXT"+ ")";




    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String name){
        SQLiteDatabase db  = this .getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);

        long result = db.insert(TABLE_NAME,null,contentValues);
        return  result != -1;
    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select * from "+TABLE_NAME;


        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}
