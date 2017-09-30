package com.kelvinhado.privy.data.source.utils;

import android.content.ContentValues;

import com.kelvinhado.privy.data.Privy;
import com.kelvinhado.privy.data.source.local.PriviesPersistenceContract;

/**
 * Created by kelvin on 30/09/2017.
 */

public class PrivyUtils {

    public static ContentValues toContentValues(Privy privy) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PriviesPersistenceContract.PrivyEntry.COLUMN_NAME_ID, privy.getId());
        contentValues.put(PriviesPersistenceContract.PrivyEntry.COLUMN_NAME_LOCATION_NAME, privy.getAddressName());
        contentValues.put(PriviesPersistenceContract.PrivyEntry.COLUMN_NAME_LOCATION_LAT, privy.getLatitude());
        contentValues.put(PriviesPersistenceContract.PrivyEntry.COLUMN_NAME_LOCATION_LON, privy.getLongitude());
        contentValues.put(PriviesPersistenceContract.PrivyEntry.COLUMN_NAME_OPENING_HOURS, privy.getOpeningHours());
        return contentValues;
    }
}
