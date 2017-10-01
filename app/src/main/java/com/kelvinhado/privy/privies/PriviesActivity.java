package com.kelvinhado.privy.privies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kelvinhado.privy.R;
import com.kelvinhado.privy.data.Injection;
import com.kelvinhado.privy.utils.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PriviesActivity extends AppCompatActivity {

    private PriviesPresenter mPriviesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privies);
        ButterKnife.bind(this);
        PriviesMapFragment priviesMapFragment =
                (PriviesMapFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (priviesMapFragment == null) {
            // Create the fragment
            priviesMapFragment = PriviesMapFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), priviesMapFragment, R.id.content_frame);
        }

        // add presenter
        mPriviesPresenter = new PriviesPresenter(
                Injection.providePriviesRepository(this),
                priviesMapFragment);

    }

    @OnClick(R.id.fab)
    public void navigateToList() {
        mPriviesPresenter.loadPrivies(true);
    }
}
