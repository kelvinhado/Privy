package com.kelvinhado.privy.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kelvinhado.privy.data.Privy;
import com.kelvinhado.privy.data.source.PriviesDataSource;
import com.kelvinhado.privy.data.source.remote.pojo.RatpPrivyPojo;
import com.kelvinhado.privy.data.source.remote.pojo.Record;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by kelvin on 30/09/2017.
 */

public class PriviesRemoteDataSource implements PriviesDataSource, Callback<RatpPrivyPojo> {

    private static final String TAG = "RemoteDataSource";

    private static PriviesRemoteDataSource INSTANCE;

    private LoadPriviesCallback mCallback;

    // Prevent direct instantiation.
    private PriviesRemoteDataSource() {}

    public static PriviesRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PriviesRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getPrivies(@NonNull LoadPriviesCallback callback) {
        mCallback = callback;
        Log.d(TAG, "fetched from remote db");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.ratp.fr/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        RatpPriviesService service = retrofit.create(RatpPriviesService.class);
        Call<RatpPrivyPojo> pojo = service.listPrivies();
        pojo.enqueue(this);
    }

    @Override
    public void refreshPrivies() {
        // Not required because the {@link PriviesRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }

    @Override
    public void savePrivy(@NonNull Privy privy) {
        // Not supported because our remote datasource does not support saving privies
    }

    @Override
    public void deleteAllPrivies() {
        // Not supported because our remote datasource does not support saving privies
    }

    private Privy convertToPrivy(Record pojo) {
        return new Privy(
                pojo.getRecordid(),
                pojo.getFields().getNumero_voie() + " " + pojo.getFields().getNom_voie(),
                pojo.getFields().getHoraires_ouverture(),
                pojo.getFields().getGeom_x_y()[0],
                pojo.getFields().getGeom_x_y()[1]
        );
    }

    @Override
    public void onResponse(Call<RatpPrivyPojo> call, Response<RatpPrivyPojo> response) {
        if(response.isSuccessful()) {
            RatpPrivyPojo pojo = response.body();
            List<Privy> privies = new ArrayList<>();
            for (Record record : pojo.getRecords()) {
                privies.add(convertToPrivy(record));
            }
            Log.d(TAG, "remote data found");
            mCallback.onPriviesLoaded(privies);
        } else {
            Log.e(TAG, "connexion error");
            mCallback.onDataNotAvailable();
        }
    }

    @Override
    public void onFailure(Call<RatpPrivyPojo> call, Throwable t) {
        Log.e(TAG, "connexion error" + t.getLocalizedMessage());
        mCallback.onDataNotAvailable();
    }
}
