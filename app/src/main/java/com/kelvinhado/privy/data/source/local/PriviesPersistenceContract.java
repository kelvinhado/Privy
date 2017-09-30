package com.kelvinhado.privy.data.source.local;

/**
 * Created by kelvin on 30/09/2017.
 */

import android.provider.BaseColumns;

/**
 * The contract used for the db to save the privies locally.
 */
public final class PriviesPersistenceContract {

    //private to prevent accidentally instantiating the contract class
    private PriviesPersistenceContract() {}

    /* Inner class that defines the table contents */
    public static abstract class PrivyEntry implements BaseColumns {
        public static final String TABLE_NAME = "privies";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_ID = "privyId";
        public static final String COLUMN_NAME_LOCATION_NAME = "address";
        public static final String COLUMN_NAME_LOCATION_LAT = "latitude";
        public static final String COLUMN_NAME_LOCATION_LON = "longitude";
        public static final String COLUMN_NAME_OPENING_HOURS = "openingHours";
    }
}
