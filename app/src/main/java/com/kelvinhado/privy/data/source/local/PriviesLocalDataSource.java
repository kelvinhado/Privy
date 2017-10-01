package com.kelvinhado.privy.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.kelvinhado.privy.data.Privy;
import com.kelvinhado.privy.data.source.PriviesDataSource;
import com.kelvinhado.privy.data.source.local.PriviesPersistenceContract.PrivyEntry;
import com.kelvinhado.privy.data.source.utils.PrivyUtils;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kelvin on 30/09/2017.
 */

public class PriviesLocalDataSource implements PriviesDataSource {

    private static final String TAG = "LocalDataSource";
    private static PriviesLocalDataSource INSTANCE;
    
    private final PriviesDbHelper mDbHelper;

    // Prevent direct instantiation.
    private PriviesLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new PriviesDbHelper(context);
    }

    public static PriviesLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PriviesLocalDataSource(context);
        }
        return INSTANCE;
    }

    /**
     * Methods that returns the list of privies from the local database
     * @param callback load privies callback
     */
    @Override
    public void getPrivies(@NonNull LoadPriviesCallback callback) {
        List<Privy> privies = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                PrivyEntry.COLUMN_NAME_ID,
                PrivyEntry.COLUMN_NAME_LOCATION_NAME,
                PrivyEntry.COLUMN_NAME_LOCATION_LAT,
                PrivyEntry.COLUMN_NAME_LOCATION_LON,
                PrivyEntry.COLUMN_NAME_OPENING_HOURS,
        };

        Cursor c = db.query(
                PrivyEntry.TABLE_NAME, projection, null, null, null, null, null);

        // if results from db
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                Privy privy = new Privy(
                        c.getString(c.getColumnIndexOrThrow(PrivyEntry.COLUMN_NAME_ID)),
                        c.getString(c.getColumnIndexOrThrow(PrivyEntry.COLUMN_NAME_LOCATION_NAME)),
                        c.getString(c.getColumnIndexOrThrow(PrivyEntry.COLUMN_NAME_OPENING_HOURS)),
                        c.getDouble(c.getColumnIndexOrThrow(PrivyEntry.COLUMN_NAME_LOCATION_LAT)),
                        c.getDouble(c.getColumnIndexOrThrow(PrivyEntry.COLUMN_NAME_LOCATION_LON))
                );
                privies.add(privy);
            }
        }
        if (c != null) {
            c.close();
        }

        db.close();

        if (privies.isEmpty()) {
            // This will be called if the table is new or just empty.
            Log.d(TAG, "no data found");
            callback.onDataNotAvailable();
        } else {
            Log.d(TAG, "data found");
            callback.onPriviesLoaded(privies);
        }
    }

    @Override
    public void refreshPrivies() {
        // Not required because the {@link PriviesRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }

    @Override
    public void savePrivy(@NonNull Privy privy) {
        checkNotNull(privy);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = PrivyUtils.toContentValues(privy);
        db.insert(PrivyEntry.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void deleteAllPrivies() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(PrivyEntry.TABLE_NAME, null, null);
        db.close();
    }
}
