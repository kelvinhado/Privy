package com.kelvinhado.privy.privies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.kelvinhado.privy.data.Privy;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class PriviesMapFragment extends Fragment implements PriviesContract.View {

    private PriviesContract.Presenter mPresenter;

    private List<Privy> mPriviesList;

    public PriviesMapFragment() {
        // Requires empty public constructor
    }

    public static PriviesMapFragment newInstance() {
        return new PriviesMapFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull PriviesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showPrivies(List<Privy> privies) {

    }

    @Override
    public void showFavoritePrivies(List<Privy> privies) {

    }

    @Override
    public void showNoPrivies() {

    }

    @Override
    public void showLoadingPriviesError() {

    }
}
