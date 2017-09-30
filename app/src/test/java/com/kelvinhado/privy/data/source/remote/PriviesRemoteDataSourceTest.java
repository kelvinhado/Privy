package com.kelvinhado.privy.data.source.remote;

import com.kelvinhado.privy.data.Privy;
import com.kelvinhado.privy.data.source.PriviesDataSource;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kelvin on 30/09/2017.
 */
public class PriviesRemoteDataSourceTest {
    @Test
    public void testRatpServerConnexion() throws Exception {
        PriviesRemoteDataSource remoteDataSource = PriviesRemoteDataSource.getInstance();
        remoteDataSource.getPrivies(new PriviesDataSource.LoadPriviesCallback() {
            @Override
            public void onPriviesLoaded(List<Privy> privies) {
                assertTrue("should return non empty list of privies", privies.size() > 0);
            }

            @Override
            public void onDataNotAvailable() {
                assertTrue("connexion could not be established", false);
            }
        });

    }
}