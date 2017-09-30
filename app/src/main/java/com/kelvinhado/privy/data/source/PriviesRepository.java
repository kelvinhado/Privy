package com.kelvinhado.privy.data.source;

import android.support.annotation.NonNull;

import com.kelvinhado.privy.data.Privy;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kelvin on 30/09/2017.
 */

public class PriviesRepository implements PriviesDataSource {

    private static PriviesRepository INSTANCE = null;

    private final PriviesDataSource mPriviesRemoteDataSource;

    private final PriviesDataSource mPriviesLocalDataSource;
    
    // Prevent direct instantiation.
    private PriviesRepository(@NonNull PriviesDataSource priviesRemoteDataSource,
                              @NonNull PriviesDataSource priviesLocalDataSource) {
        mPriviesRemoteDataSource = checkNotNull(priviesRemoteDataSource);
        mPriviesLocalDataSource = checkNotNull(priviesLocalDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param priviesRemoteDataSource the backend data source
     * @param priviesLocalDataSource  the device storage data source
     * @return the {@link PriviesRepository} instance
     */
    public static PriviesRepository getInstance(PriviesDataSource priviesRemoteDataSource,
                                                PriviesDataSource priviesLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new PriviesRepository(priviesRemoteDataSource, priviesLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(PriviesDataSource, PriviesDataSource)} to create a new
     * instance next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Gets privies local data source or remote data source, whichever is available first.
     * <p>
     * Note: {@link LoadPriviesCallback#onDataNotAvailable()} is fired if all data sources fail to
     * get the data.
     */
    @Override
    public void getPrivies(@NonNull final LoadPriviesCallback callback) {
        // Query the local storage if available. If not, query the network.
        mPriviesLocalDataSource.getPrivies(new LoadPriviesCallback() {

            @Override
            public void onPriviesLoaded(List<Privy> privies) {
                callback.onPriviesLoaded(privies);
            }

            @Override
            public void onDataNotAvailable() {
                getPriviesFromRemoteDataSource(callback);
            }
        });
    }

    /**
     * Fetch privies from remote server
     * @param callback
     */
    private void getPriviesFromRemoteDataSource(@NonNull final LoadPriviesCallback callback) {
        mPriviesRemoteDataSource.getPrivies(new LoadPriviesCallback() {

            @Override
            public void onPriviesLoaded(List<Privy> privies) {
                callback.onPriviesLoaded(privies);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
