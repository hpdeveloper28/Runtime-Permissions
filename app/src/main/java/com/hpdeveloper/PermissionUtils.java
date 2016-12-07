package com.hpdeveloper;

import android.Manifest;
import android.content.Context;

import com.permissionapp.R;

import java.util.HashMap;

/**
 * Created by hiren.patel on 07-12-2016.
 */
public class PermissionUtils {

    private static HashMap<String, String> permissionHashmap;

    public static void addPermissionMap(Context context) {
        if (permissionHashmap == null) {
            permissionHashmap = new HashMap<>();

            permissionHashmap.put(Manifest.permission.CAMERA, context.getString(R.string.camera_permission_denied_feedback));
            permissionHashmap.put(Manifest.permission.READ_CONTACTS, context.getString(R.string.contacts_permission_denied_feedback));
            permissionHashmap.put(Manifest.permission.ACCESS_FINE_LOCATION, context.getString(R.string.location_permission_denied_feedback));
        }
    }

    public static String getPermissionDetails(String permission) {
        if (permissionHashmap != null && !permissionHashmap.isEmpty()) {
            return permissionHashmap.get(permission);
        }

        return "";
    }
}
