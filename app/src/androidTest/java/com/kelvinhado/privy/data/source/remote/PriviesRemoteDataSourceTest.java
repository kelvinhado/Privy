package com.kelvinhado.privy.data.source.remote;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.kelvinhado.privy.data.Privy;
import com.kelvinhado.privy.data.source.PriviesDataSource;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by kelvin on 30/09/2017.
 */
@RunWith(AndroidJUnit4.class)
public class PriviesRemoteDataSourceTest {

    private final Context mContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void testRatpServerConnexion() throws Exception {
        PriviesRemoteDataSource remoteDataSource = PriviesRemoteDataSource.getInstance();
        remoteDataSource.getPrivies(new PriviesDataSource.LoadPriviesCallback() {
            @Override
            public void onPriviesLoaded(List<Privy> privies) {

            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }
}