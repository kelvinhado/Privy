package com.kelvinhado.privy.privies;

import android.support.annotation.NonNull;

import com.kelvinhado.privy.BasePresenter;
import com.kelvinhado.privy.data.Privy;
import com.kelvinhado.privy.data.source.PriviesDataSource;
import com.kelvinhado.privy.data.source.PriviesRepository;

import java.util.List;

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
        loadPriviesFromDataSource();
    }

    @Override
    public void loadPrivies(boolean forceUpdate) {
        if(forceUpdate) {
            mPriviesRepository.refreshPrivies();
        }
        loadPriviesFromDataSource();
    }

    @Override
    public void queryFavoritesPrivies() {

    }

    private void loadPriviesFromDataSource() {
        mPriviesView.setLoadingIndicator(true);
        mPriviesRepository.getPrivies(new PriviesDataSource.LoadPriviesCallback() {
            @Override
            public void onPriviesLoaded(List<Privy> privies) {
                mPriviesView.setLoadingIndicator(false);
                mPriviesView.showPrivies(privies);
            }

            @Override
            public void onDataNotAvailable() {
                mPriviesView.setLoadingIndicator(false);
                mPriviesView.showLoadingPriviesError();
            }
        });

    }
}
