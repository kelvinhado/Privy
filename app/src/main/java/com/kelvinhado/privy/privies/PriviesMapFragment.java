package com.kelvinhado.privy.privies;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import com.google.android.gms.maps.model.MarkerOptions;
import com.kelvinhado.privy.R;
import com.kelvinhado.privy.data.Privy;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class PriviesMapFragment extends Fragment implements PriviesContract.View, OnMapReadyCallback {

    private static final int PERMISSIONS_REQUEST_LOCATION = 101;

    private PriviesContract.Presenter mPresenter;

    private List<Privy> mPriviesList;

    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private boolean isMapReady;

    private View mRootView;

    private ProgressDialog mProgress;

    public PriviesMapFragment() {
        // Requires empty public constructor
    }

    public static PriviesMapFragment newInstance() {
        return new PriviesMapFragment();
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
        mRootView = inflater.inflate(R.layout.fragment_privies_map, container, false);
        mProgress = new ProgressDialog(getContext());
        mProgress.setTitle(getString(R.string.progress_loading_title));
        mProgress.setMessage(getString(R.string.progress_loading_message));
        mProgress.setIndeterminate(true);
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
        if(active) {
            mProgress.show();
        } else {
            mProgress.dismiss();
        }
    }

    @Override
    public void showPrivies(List<Privy> privies) {
        mPriviesList = privies;
        if(isMapReady) {
            updateMapMarkers();
        }
    }

    @Override
    public void showFavoritePrivies(List<Privy> privies) {

    }

    @Override
    public void showNoPrivies() {

    }

    @Override
    public void showLoadingPriviesError() {
        Toast.makeText(getContext(), "error loading data..", Toast.LENGTH_SHORT).show();
    }

    // Map Management_______________________________________________________________________________
    public void initializeMap(Bundle savedInstanceState) {
        mMapView = (MapView) mRootView.findViewById(R.id.mv_google_mapview);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);
    }

    public void updateMapMarkers() {
        mGoogleMap.clear();
        for (Privy privy : mPriviesList) {
            mGoogleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(privy.getLatitude(), privy.getLongitude()))
                    .title(getString(R.string.map_marker_title))
                    .snippet(privy.getAddressName())
            );
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        isMapReady = true;
        mGoogleMap = googleMap;
        //custom settings
        enableMyLocation();
        mGoogleMap.getUiSettings().setMapToolbarEnabled(false);
        mGoogleMap.getUiSettings().setZoomControlsEnabled(false);
        mGoogleMap.getUiSettings().setTiltGesturesEnabled(false);
        mGoogleMap.getUiSettings().setRotateGesturesEnabled(false);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.setMaxZoomPreference(18);
        LatLng position = new LatLng(48.866667, 2.333333);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 11));
        updateMapMarkers();
    }

    private void enableMyLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_LOCATION);
        } else {
            mGoogleMap.setMyLocationEnabled(true);
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation();
                }
                break;
            }
        }
    }

}
