package com.example.qrcodescanner;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionClass {
    public static String[] appPermissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };


    private static final int PERMISSIONS_REQUEST_CODE = 1;

    public static boolean checkAndRequestPermission(Activity activity){
        if (Build.VERSION.SDK_INT >= 23){
            try {
                List<String> listPermissionsNeeded = new ArrayList<>();
                for (String perm : appPermissions){
                    if (ContextCompat.checkSelfPermission(activity, perm) != PackageManager.PERMISSION_GRANTED){
                        listPermissionsNeeded.add(perm);
                    }
                }

                if (!listPermissionsNeeded.isEmpty()){
                    ActivityCompat.requestPermissions(activity,listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                            PERMISSIONS_REQUEST_CODE);
                    return false;
                }

            }catch (RuntimeException re){ }

        }
        return true;
    }
}
