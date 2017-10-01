package com.kelvinhado.privy.privies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kelvinhado.privy.R;
import com.kelvinhado.privy.data.Injection;
import com.kelvinhado.privy.utils.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PriviesActivity extends AppCompatActivity {

    private PriviesPresenter mPriviesMapPresenter;
    private PriviesPresenter mPriviesListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privies);
        ButterKnife.bind(this);
//        PriviesMapFragment priviesMapFragment =
//                (PriviesMapFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
//        if (priviesMapFragment == null) {
//            // Create the fragment
//            priviesMapFragment = PriviesMapFragment.newInstance();
//            ActivityUtils.addFragmentToActivity(
//                    getSupportFragmentManager(), priviesMapFragment, R.id.content_frame);
//        }
        PriviesListFragment priviesListFragment =
                (PriviesListFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (priviesListFragment == null) {
            // Create the fragment
            priviesListFragment = PriviesListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), priviesListFragment, R.id.content_frame);
        }



        // add presenter
//        mPriviesMapPresenter = new PriviesPresenter(Injection.providePriviesRepository(this),
//                priviesMapFragment);
        mPriviesListPresenter = new PriviesPresenter(Injection.providePriviesRepository(this),
                priviesListFragment);

    }

    @OnClick(R.id.fab)
    public void navigateToList() {
        mPriviesMapPresenter.loadPrivies(true);
    }
}
