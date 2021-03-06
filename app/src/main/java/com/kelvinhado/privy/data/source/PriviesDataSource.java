package com.kelvinhado.privy.data.source;

import android.support.annotation.NonNull;

import com.kelvinhado.privy.data.Privy;

import java.util.List;

/**
 * Created by kelvin on 30/09/2017.
 */

public interface PriviesDataSource {

    void getPrivies(@NonNull LoadPriviesCallback callback);

    void refreshPrivies();

    void savePrivy(@NonNull Privy privy);

    void deleteAllPrivies();

    interface LoadPriviesCallback {

        void onPriviesLoaded(List<Privy> privies);

        void onDataNotAvailable();
    }
}
