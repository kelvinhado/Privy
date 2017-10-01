package com.kelvinhado.privy.privies;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelvinhado.privy.R;
import com.kelvinhado.privy.data.Privy;
import com.kelvinhado.privy.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kelvin on 01/10/2017.
 */

public class PriviesListFragment extends Fragment implements PriviesContract.View,
        PriviesListAdapter.ListItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_privies)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private PriviesContract.Presenter mPresenter;
    private List<Privy> mPriviesList;
    private ProgressDialog mProgress;
    private Snackbar mSnackBar;
    private PriviesListAdapter mAdapter;


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
        View mRootView = inflater.inflate(R.layout.fragment_privies_list, container, false);
        ButterKnife.bind(this, mRootView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        initializeLoader();
        mSnackBar = Snackbar.make(mRootView, R.string.snackbar_error_message, Snackbar.LENGTH_SHORT);
        mAdapter = new PriviesListAdapter(mPriviesList, this);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.loadPrivies(false);
        return mRootView;
    }

    @Override
    public void setPresenter(PriviesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if(active && !mSwipeRefreshLayout.isRefreshing()) {
            mProgress.show();
        } else {
            mProgress.dismiss();
        }
    }

    private void initializeLoader() {
        mProgress = new ProgressDialog(getContext());
        mProgress.setTitle(getString(R.string.progress_loading_title));
        mProgress.setMessage(getString(R.string.progress_loading_message));
        mProgress.setIndeterminate(true);
    }

    @Override
    public void showPrivies(List<Privy> privies) {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.swap(privies);
    }

    @Override
    public void showFavoritePrivies(List<Privy> privies) {
        // Not implemented yet
    }

    @Override
    public void showNoPrivies() {
        showLoadingError();
    }

    @Override
    public void showLoadingPriviesError() {
        showLoadingError();
    }

    private void showLoadingError() {
        mSwipeRefreshLayout.setRefreshing(false);
        mSnackBar.show();
    }

    @Override
    public void onListItemClicked(int itemPosition) {
        ActivityUtils.showGPSDialog(getContext(), mPriviesList.get(itemPosition));
    }

    @Override
    public void onRefresh() {
        mPresenter.loadPrivies(true);
    }


}
