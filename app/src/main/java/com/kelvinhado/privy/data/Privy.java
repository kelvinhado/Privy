package com.kelvinhado.privy.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by kelvin on 30/09/2017.
 */

public class Privy {

    @NonNull
    private final String mId;

    @Nullable
    private final String mAddressStreetNumber;

    @Nullable
    private final String mAddressStreetLabel;

    @Nullable
    private final String mOpeningHours;

    private final double mLatitude;

    private final double mLongitude;


    public Privy(@NonNull String mId, @NonNull double mLatitude, @NonNull double mLongitude) {
        this.mId = mId;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.mAddressStreetNumber = "";
        this.mAddressStreetLabel = "";
        this.mOpeningHours = "";
    }

    public Privy(@NonNull String mId, String mAddressStreetNumber, String mAddressStreetLabel,
                 String mOpeningHours, @NonNull double mLattitude, @NonNull double mLongitude) {
        this.mId = mId;
        this.mAddressStreetNumber = mAddressStreetNumber;
        this.mAddressStreetLabel = mAddressStreetLabel;
        this.mOpeningHours = mOpeningHours;
        this.mLatitude = mLattitude;
        this.mLongitude = mLongitude;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @Nullable
    public String getAddressStreetNumber() {
        return mAddressStreetNumber;
    }

    @Nullable
    public String getAddressStreetLabel() {
        return mAddressStreetLabel;
    }

    @Nullable
    public String getOpeningHours() {
        return mOpeningHours;
    }

    public double getLattitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    @Override
    public String toString() {
        return "Privy{" +
                "mId='" + mId + '\'' +
                ", mAddressStreetNumber='" + mAddressStreetNumber + '\'' +
                ", mAddressStreetLabel='" + mAddressStreetLabel + '\'' +
                ", mOpeningHours='" + mOpeningHours + '\'' +
                ", mLattitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                '}';
    }
}
