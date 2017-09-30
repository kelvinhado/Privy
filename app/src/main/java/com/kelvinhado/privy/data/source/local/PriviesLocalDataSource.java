package com.kelvinhado.privy.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.kelvinhado.privy.data.source.PriviesDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kelvin on 30/09/2017.
 */

public class PriviesLocalDataSource implements PriviesDataSource {

    private static PriviesLocalDataSource INSTANCE;

    // Prevent direct instantiation.
    private PriviesLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
//        mDbHelper = new TasksDbHelper(context);
    }

    public static PriviesLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PriviesLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getPrivies(@NonNull LoadPriviesCallback callback) {

    }
}
