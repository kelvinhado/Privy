package com.kelvinhado.privy.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kelvin on 30/09/2017.
 */

public class PriviesDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "privies.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String REAL_TYPE = " REAL";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PriviesPersistenceContract.PrivyEntry.TABLE_NAME + " (" +
                    PriviesPersistenceContract.PrivyEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + " PRIMARY KEY," +
                    PriviesPersistenceContract.PrivyEntry.COLUMN_NAME_ID + TEXT_TYPE + COMMA_SEP +
                    PriviesPersistenceContract.PrivyEntry.COLUMN_NAME_LOCATION_NAME + TEXT_TYPE + COMMA_SEP +
                    PriviesPersistenceContract.PrivyEntry.COLUMN_NAME_LOCATION_LAT + REAL_TYPE + COMMA_SEP +
                    PriviesPersistenceContract.PrivyEntry.COLUMN_NAME_LOCATION_LON + REAL_TYPE + COMMA_SEP +
                    PriviesPersistenceContract.PrivyEntry.COLUMN_NAME_OPENING_HOURS + TEXT_TYPE +
                    " )";

    // Helper constructor
    public PriviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // To simplify we'll just drop the table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + PriviesPersistenceContract.PrivyEntry.TABLE_NAME);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}