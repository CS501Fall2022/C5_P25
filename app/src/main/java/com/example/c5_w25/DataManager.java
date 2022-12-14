package com.example.c5_w25;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
        sqlCreate += " " + FIRST_NAME + " TEXT,";
        sqlCreate += " " + LAST_NAME + " TEXT, ";
        sqlCreate += " " + EMAIL + " TEXT)";

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
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "INSERT INTO " + TABLE_FRIEND;
        sqlInsert += " VALUES(null, '" + friend.getFirstName();
        sqlInsert += "', '" + friend.getLastName();
        sqlInsert += "', '" + friend.getEmail();
        sqlInsert += "')";

        db.execSQL(sqlInsert);
        db.close();
    }

    public ArrayList<Friend> selectAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlSelect = "SELECT * FROM " + TABLE_FRIEND;

        ArrayList<Friend> friends = new ArrayList<Friend>();

        Cursor cur = db.rawQuery(sqlSelect, null);

        while (cur.moveToNext()){
            Friend newFriend = new Friend(cur.getInt(0), cur.getString(1), cur.getString(2), cur.getString(3));
            friends.add(newFriend);
        }

        db.close( );
        return friends;
    }

    public void updateById(int friendId, String first, String last, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "UPDATE " + TABLE_FRIEND;
        sqlUpdate += " SET " + FIRST_NAME + " = '" + first;
        sqlUpdate += "', " + LAST_NAME + " = '" + last;
        sqlUpdate += "', " + EMAIL + " = '" + email;
        sqlUpdate += "' WHERE " + ID + " = " + friendId;

        db.execSQL(sqlUpdate);
        db.close();
    }

    public void deleteById(int friendId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "DELETE FROM " + TABLE_FRIEND;
        sqlDelete += " WHERE ID = " + friendId;

        db.execSQL(sqlDelete);
        db.close();
    }

    public Friend selectByEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlSelect = "SELECT * FROM " + TABLE_FRIEND;
        sqlSelect += " WHERE " + EMAIL + " = '" + email + "'";

        Cursor cur = db.rawQuery(sqlSelect, null);
        Friend friend = null;

        if (cur.moveToFirst()) {
           friend = new Friend(cur.getInt(0), cur.getString(1), cur.getString(2), cur.getString(3));
        }

        db.close();
        return friend;
    }

    public ArrayList<String> selectEmails() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlSelect = "SELECT " + EMAIL + " FROM " + TABLE_FRIEND;
        Cursor cur = db.rawQuery(sqlSelect, null);
        ArrayList<String> strings = new ArrayList<String>();

        while (cur.moveToNext()) {
            strings.add(cur.getString(0));
        }

        return strings;
    }
}
