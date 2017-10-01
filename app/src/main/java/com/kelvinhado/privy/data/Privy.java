package com.kelvinhado.privy.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvin on 30/09/2017.
 */

public class Privy {

    @NonNull
    private final String mId;

    @Nullable
    private final String mAddressName;

    @Nullable
    private final String mOpeningHours;

    private final double mLatitude;

    private final double mLongitude;


    public Privy(@NonNull String mId, @NonNull double mLatitude, @NonNull double mLongitude) {
        this.mId = mId;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.mAddressName = "";
        this.mOpeningHours = "";
    }

    public Privy(@NonNull String mId, String mAddressName, String mOpeningHours,
                 @NonNull double mLattitude, @NonNull double mLongitude) {
        this.mId = mId;
        this.mAddressName = mAddressName;
        this.mOpeningHours = mOpeningHours;
        this.mLatitude = mLattitude;
        this.mLongitude = mLongitude;
    }

    public static List<Privy> getFakePriviesList() {
        final Privy privy = new Privy("aaa", "109 BOULEVARD DE SEBASTOPOL", "6 h - 22 h", 48.8668198774, 2.35272664515);
        final Privy privy2 = new Privy("bbb", "123 RUE SAINT MARTIN", "24 h / 24", 48.8607904234, 2.35131574473);
        return new ArrayList<Privy>() {{ add(privy); add(privy2); }};
    }

    @NonNull
    public String getId() {
        return mId;
    }

    public String getAddressName() {
        return mAddressName;
    }

    public String getOpeningHours() {
        return mOpeningHours;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    @Override
    public String toString() {
        return "Privy{" +
                "mId='" + mId + '\'' +
                ", mAddressName='" + mAddressName + '\'' +
                ", mOpeningHours='" + mOpeningHours + '\'' +
                ", mLatitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                '}';
    }
}
