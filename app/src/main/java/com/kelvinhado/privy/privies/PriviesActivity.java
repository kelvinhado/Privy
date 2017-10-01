package com.kelvinhado.privy.privies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kelvinhado.privy.R;
import com.kelvinhado.privy.data.Injection;
import com.kelvinhado.privy.utils.ActivityUtils;

public class PriviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privies);

        PriviesMapFragment priviesMapFragment =
                (PriviesMapFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (priviesMapFragment == null) {
            // Create the fragment
            priviesMapFragment = PriviesMapFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), priviesMapFragment, R.id.content_frame);
        }

        // add presenter
        PriviesPresenter presenter = new PriviesPresenter(
                Injection.providePriviesRepository(this),
                priviesMapFragment);

    }
}
