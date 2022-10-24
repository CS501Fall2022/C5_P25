package com.example.c5_w25;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "friendDB";
    private static final String TABLE_FRIEND = "friend";
    private static final int DATABASE_VERSION = 1;
    private static final String ID= "id";
    private static final String FIRST_NAME = "first";
    private static final String LAST_NAME = "last";
    private static final String EMAIL = "email";

    public DataManager( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    public void onCreate(SQLiteDatabase db){
        String sqlCreate = "CREATE TABLE IF NOT EXISTS " + TABLE_FRIEND;
        sqlCreate += "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,";
        sqlCreate += " " + FIRST_NAME + "TEXT,";
        sqlCreate += " " + LAST_NAME + "TEXT, ";
        sqlCreate += " " + EMAIL + "TEXT)";

        db.execSQL(sqlCreate);
        Log.w("TAG", "Created DB Table");
    }

    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " +
        TABLE_FRIEND );
        // Re-create table(s)
        onCreate( db );
    }

    public void insert( Friend friend){
        SQLiteDatabase db = this.getWritableDatabase( );;
        String sqlInsert = "INSERT INTO " + TABLE_FRIEND;
        sqlInsert += " VALUES(null, '" + friend.getFirstName();
        sqlInsert += "', '" + friend.getLastName();
        sqlInsert += "', '" + friend.getEmail();
        sqlInsert += "')";

        db.execSQL(sqlInsert);
    }
}
