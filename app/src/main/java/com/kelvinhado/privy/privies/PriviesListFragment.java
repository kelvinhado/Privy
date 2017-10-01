package com.kelvinhado.privy.privies;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kelvinhado.privy.R;
import com.kelvinhado.privy.data.Privy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kelvin on 01/10/2017.
 */

public class PriviesListFragment extends Fragment implements PriviesContract.View, PriviesListAdapter.ListItemClickListener {

    private static final int PERMISSIONS_REQUEST_LOCATION = 101;
    @BindView(R.id.rv_privies)
    RecyclerView mRecyclerView;
    private PriviesContract.Presenter mPresenter;
    private List<Privy> mPriviesList;
    private View mRootView;
    private ProgressDialog mProgress;
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
        mRootView = inflater.inflate(R.layout.fragment_privies_list, container, false);
        ButterKnife.bind(this, mRootView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
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

    }

    @Override
    public void showPrivies(List<Privy> privies) {
        mAdapter.swap(privies);
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

    @Override
    public void onListItemClicked(int itemPosition) {
        Toast.makeText(getContext(), "item selected "  + itemPosition, Toast.LENGTH_SHORT).show();
    }
}
