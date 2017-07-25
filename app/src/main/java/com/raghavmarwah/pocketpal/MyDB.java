package com.raghavmarwah.pocketpal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDB extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Budget.db";

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            UserEntry.TABLE_NAME + " (" +
            UserEntry._ID + " INTEGER PRIMARY KEY, " +
            UserEntry.COLUMN_NAME_USRNAME + " TEXT, " +
            UserEntry.COLUMN_NAME_EMAIL + " TEXT, " +
            UserEntry.COLUMN_NAME_INCOME + " DECIMAL)";

    public static final String SQL_CREATE_ENTRIES_2 = "CREATE TABLE " +
            UserEntry.TABLE_NAME_2 + " (" +
            UserEntry._ID + " INTEGER PRIMARY KEY, " +
            UserEntry.COLUMN_NAME_DATE + " DATE, " +
            UserEntry.COLUMN_NAME_GROCERIES + " DECIMAL, " +
            UserEntry.COLUMN_NAME_INSURANCES + " DECIMAL, " +
            UserEntry.COLUMN_NAME_BILLS + " DECIMAL, " +
            UserEntry.COLUMN_NAME_RENT + " DECIMAL, " +
            UserEntry.COLUMN_NAME_EAT + " DECIMAL, " +
            UserEntry.COLUMN_NAME_SHOP + " DECIMAL, " +
            UserEntry.COLUMN_NAME_MISC + " DECIMAL, " +
            UserEntry.COLUMN_NAME_GROCERIES_L + " DECIMAL, " +
            UserEntry.COLUMN_NAME_INSURANCES_L + " DECIMAL, " +
            UserEntry.COLUMN_NAME_BILLS_L + " DECIMAL, " +
            UserEntry.COLUMN_NAME_RENT_L + " DECIMAL, " +
            UserEntry.COLUMN_NAME_EAT_L + " DECIMAL, " +
            UserEntry.COLUMN_NAME_SHOP_L + " DECIMAL, " +
            UserEntry.COLUMN_NAME_MISC_L + " DECIMAL)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;

    public MyDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("MyDB","Constructor");
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_2);
        Log.d("MyDB","onCreate");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_2);
        Log.d("MyDB","onUpgrade");
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion,int newVersion){
        Log.d("MyDB","onDowngrade");
        onUpgrade(db, oldVersion, newVersion);
    }

}
