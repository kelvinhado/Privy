package com.kelvinhado.privy.privies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.kelvinhado.privy.R;
import com.kelvinhado.privy.data.Injection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PriviesActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private PriviesPresenter mPriviesMapPresenter;
    private PriviesPresenter mPriviesListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privies);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0L);
            actionBar.setTitle(R.string.actionbar_main_title);
        }

        PriviesListFragment listFragment = PriviesListFragment.newInstance();
        PriviesMapFragment mapFragment = PriviesMapFragment.newInstance();

        // add presenter
        mPriviesMapPresenter = new PriviesPresenter(Injection.providePriviesRepository(this),
                mapFragment);
        mPriviesListPresenter = new PriviesPresenter(Injection.providePriviesRepository(this),
                listFragment);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(mapFragment);
        fragments.add(listFragment);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.navigation_fragment_title_map)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.navigation_fragment_title_list)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mViewPager.setAdapter(new PriviesViewPagerAdapter(getSupportFragmentManager(), fragments));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(this);
    }

    @OnClick(R.id.fab)
    public void navigateToList() {
        mPriviesMapPresenter.loadPrivies(true);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());

        if(tab.getPosition() == 0) {
            fab.show();
        } else {
            fab.hide();
        }
        if(tab.getPosition() == 1) {
            mPriviesListPresenter.loadPrivies(false);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private class PriviesViewPagerAdapter extends FragmentStatePagerAdapter {
        List<Fragment> mFragments;

        PriviesViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
