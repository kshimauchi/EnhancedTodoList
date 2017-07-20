package com.kshimauchi.enhancedtodolist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kshim on 7/17/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "items.db";
    private static final String TAG = "dbhelper";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //Added the Column Choice here,
    @Override
    public void onCreate(SQLiteDatabase db) {
        //must add syntax to the query string in order to create a column for the spinner Result which is
        //Stores 1 of 4 values for each item created
        String queryString = "CREATE TABLE " + Contract.TABLE_TODO.TABLE_NAME + " ("+
                Contract.TABLE_TODO._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Contract.TABLE_TODO.COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL, " +
                Contract.TABLE_TODO.COLUMN_CHOICE+ " "+
                Contract.TABLE_TODO.COLUMN_NAME_DUE_DATE + " DATE " + "); ";

        Log.d(TAG, "Create table SQL: " + queryString);
        db.execSQL(queryString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("drop table " + Contract.TABLE_TODO.TABLE_NAME + " if exists;");
    }
}
