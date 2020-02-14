package com.example.dell.databaseusers.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;

public class UserDbHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Bhooshan";
    public static final String NAME = "name";
    public static final String LASTNAME = "lastname";
    public static final String AGE = "age";

    public static final String DB_NAME = "Users_db";
    public static final int DB_VERSION = 1;

    public static final String CREATE_QUERY = "create table "+ TABLE_NAME + "( "
                                                                         + NAME + " text,"
                                                                         + LASTNAME+ " text,"
                                                                          + AGE + " text" + " )";
    public static final String DROP_QUERY = "drop table if exists " + TABLE_NAME + ";";


    public UserDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_QUERY);
    }


    public void putInformation(String name,String lastname,String age,SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(LASTNAME,lastname);
        contentValues.put(AGE,age);

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

    }


}
