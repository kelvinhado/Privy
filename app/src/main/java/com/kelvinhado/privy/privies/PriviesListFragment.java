package com.kelvinhado.privy.privies;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelvinhado.privy.R;
import com.kelvinhado.privy.data.Privy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvin on 01/10/2017.
 */

public class PriviesListFragment extends Fragment implements PriviesContract.View {

    private static final int PERMISSIONS_REQUEST_LOCATION = 101;

    private PriviesContract.Presenter mPresenter;

    private List<Privy> mPriviesList;

    private View mRootView;

    private ProgressDialog mProgress;

    public PriviesListFragment() {
        // Requires empty public constructor
    }

    public static PriviesListFragment newInstance() {
        return new PriviesListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPriviesList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_privies_list, container, false);
        return mRootView;
    }

    @Override
    public void setPresenter(PriviesContract.Presenter presenter) {
        mPresenter = presenter;
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
