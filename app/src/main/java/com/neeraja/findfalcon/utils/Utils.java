package com.neeraja.findfalcon.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.neeraja.findfalcon.R;

import java.util.ArrayList;

public class Utils {
    public static boolean isValidString(String str) {
        if (str != null) {
            str = str.trim();
            if (str.length() > 0)
                return true;
        }
        return false;
    }

    public static boolean isValidArrayList(ArrayList<?> list) {
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    public static boolean getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnected();
        return isConnected;
    }

    public static void showAlert(String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setNeutralButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog d = builder.create();
        d.show();
    }
}
