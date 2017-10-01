package com.kelvinhado.privy.privies;

import android.support.annotation.NonNull;

import com.kelvinhado.privy.BasePresenter;
import com.kelvinhado.privy.data.source.PriviesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kelvin on 01/10/2017.
 */

public class PriviesPresenter implements BasePresenter, PriviesContract.Presenter {

    private final PriviesRepository mPriviesRepository;

    private final PriviesContract.View mPriviesView;

    public PriviesPresenter(@NonNull PriviesRepository PriviesRepository,
                            @NonNull PriviesContract.View PriviesView) {
        mPriviesRepository = checkNotNull(PriviesRepository, "PriviesRepository cannot be null");
        mPriviesView = checkNotNull(PriviesView, "PriviesView cannot be null!");
        mPriviesView.setPresenter(this);
    }
    
    
    @Override
    public void start() {
        
    }

    @Override
    public void loadPrivies() {
        
    }

    @Override
    public void queryShowFavorites() {

    }
}
