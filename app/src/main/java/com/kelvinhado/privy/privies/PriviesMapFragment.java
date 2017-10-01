package com.kelvinhado.privy.privies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.kelvinhado.privy.R;
import com.kelvinhado.privy.data.Privy;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class PriviesMapFragment extends Fragment implements PriviesContract.View, OnMapReadyCallback {

    private PriviesContract.Presenter mPresenter;

    private List<Privy> mPriviesList;

    private MapView mMapView;

    private GoogleMap mGoogleMap;

    private View mRootView;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_privies_map, container, false);
        initializeMap(savedInstanceState);
        return mRootView;
    }

    @Override
    public void onResume() {
        mMapView.onResume();
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
        Toast.makeText(getContext(), privies.get(0).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFavoritePrivies(List<Privy> privies) {

    }

    @Override
    public void showNoPrivies() {

    }

    @Override
    public void showLoadingPriviesError() {
        Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
    }

    // Map Management_______________________________________________________________________________
    public void initializeMap(Bundle savedInstanceState) {
        mMapView = (MapView) mRootView.findViewById(R.id.mv_google_mapview);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        //custom settings
        mGoogleMap.getUiSettings().setMapToolbarEnabled(false);
        mGoogleMap.getUiSettings().setZoomControlsEnabled(false);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.setMaxZoomPreference(18);
        LatLng position = new LatLng(48.866667, 2.333333);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 10));
    }

    @Override
    public void onLowMemory() {
        mMapView.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }
    // End Map Management________________________________________________________________________END
}
