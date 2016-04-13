package com.csc.colloquium1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReaderOpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "converter.db";

    private static final String SQL_CREATE_ENTRIES_TABLE =
            "CREATE TABLE " + CurrenciesTable.TABLE_NAME
                    + "("
                    + CurrenciesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CurrenciesTable.COLUMN_NAME + " TEXT, "
                    + CurrenciesTable.COLUMN_VALUE + " DOUBLE"
                    + ")";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + CurrenciesTable.TABLE_NAME;

    public ReaderOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
