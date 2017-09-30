package com.kelvinhado.privy.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kelvinhado.privy.data.Privy;
import com.kelvinhado.privy.data.source.PriviesDataSource;
import com.kelvinhado.privy.data.source.remote.pojo.RatpPrivyPojo;

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

    public static PriviesRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PriviesRemoteDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private PriviesRemoteDataSource() {}

    @Override
    public void getPrivies(@NonNull LoadPriviesCallback callback) {
        mCallback = callback;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.ratp.fr/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        RatpPriviesService service = retrofit.create(RatpPriviesService.class);
        Call<RatpPrivyPojo> pojo = service.listPrivies();
        pojo.enqueue(this);
    }

    private Privy convertToPrivy(RatpPrivyPojo.Record pojo) {
        return new Privy(
                pojo.getRecordid(),
                pojo.getNumero_voie() + pojo.getNom_voie(),
                pojo.getHoraires_ouverture(),
                pojo.getGeom_x_y()[0],
                pojo.getGeom_x_y()[1]
        );
    }

    @Override
    public void onResponse(Call<RatpPrivyPojo> call, Response<RatpPrivyPojo> response) {
        if(response.isSuccessful()) {
            RatpPrivyPojo pojo = response.body();
            List<Privy> privies = new ArrayList<>();
            for (RatpPrivyPojo.Record record : pojo.getRecords()) {
                privies.add(convertToPrivy(record));
            }
            mCallback.onPriviesLoaded(privies);
        } else {
            Log.e(TAG, "connexion error");
            mCallback.onDataNotAvailable();
        }
    }

    @Override
    public void onFailure(Call<RatpPrivyPojo> call, Throwable t) {
        Log.e(TAG, "connexion error");
        mCallback.onDataNotAvailable();
    }
}
