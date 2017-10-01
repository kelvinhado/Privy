package com.kelvinhado.privy.utils;

/**
 * Created by kelvin on 30/09/2017.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

import com.kelvinhado.privy.R;
import com.kelvinhado.privy.data.Privy;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This provides methods to help Activities load their UI.
 */
public class ActivityUtils {

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     */
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void showGPSDialog(final Context context, final Privy privy) {
        String str = "Adresse : " + privy.getAddressName();
        str += "\nOuverture : " + privy.getOpeningHours();

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(str);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                R.string.alert_dialog_launch_gps,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        launchGPS(context, privy);
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                R.string.alert_dialog_cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private static void launchGPS(Context context, Privy privy) {
        String str = "geo:" + privy.getLatitude() + "," + privy.getLongitude()
                + "?q=" + privy.getLatitude() + "," + privy.getLongitude()
                + "(" + Uri.encode(privy.getAddressName()) + ")";
        Uri gmmIntentUri = Uri.parse(str);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        }
    }

}
