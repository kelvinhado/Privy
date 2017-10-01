package com.kelvinhado.privy.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.kelvinhado.privy.data.source.PriviesRepository;
import com.kelvinhado.privy.data.source.local.PriviesLocalDataSource;
import com.kelvinhado.privy.data.source.remote.PriviesRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kelvin on 01/10/2017.
 */

public class Injection {
    
    public static PriviesRepository providePriviesRepository(@NonNull Context context) {
        checkNotNull(context);
        return PriviesRepository.getInstance(
                PriviesRemoteDataSource.getInstance(),
                PriviesLocalDataSource.getInstance(context)
        );
    }

}
