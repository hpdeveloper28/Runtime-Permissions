
package com.hpdeveloper;

import com.hpdeveloper.listener.PermissionDeniedResponse;
import com.hpdeveloper.listener.PermissionGrantedResponse;
import com.hpdeveloper.listener.PermissionRequest;
import com.hpdeveloper.listener.multi.MultiplePermissionsListener;
import com.permissionapp.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class SampleMultiplePermissionListener implements MultiplePermissionsListener {

    private final MainActivity activity;

    public SampleMultiplePermissionListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport report) {
        List<String> grantedPermissions = new ArrayList<>();
        List<DeniedPermissionEntity> deniedPermissions = new ArrayList<>();
        for (PermissionGrantedResponse response : report.getGrantedPermissionResponses()) {
            grantedPermissions.add(response.getPermissionName());
        }
        activity.showPermissionGranted(grantedPermissions);

        for (PermissionDeniedResponse response : report.getDeniedPermissionResponses()) {
            DeniedPermissionEntity deniedPermissionEntity = new DeniedPermissionEntity();
            deniedPermissionEntity.permissionName = response.getPermissionName();
            deniedPermissionEntity.isDeniedPermanent = response.isPermanentlyDenied();
            deniedPermissions.add(deniedPermissionEntity);
        }

        activity.showPermissionDenied(deniedPermissions);
    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                   PermissionToken token) {
        activity.showPermissionRationale(permissions, token);
    }
}
