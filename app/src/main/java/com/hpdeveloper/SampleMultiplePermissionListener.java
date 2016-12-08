
package com.hpdeveloper;

import com.hpdeveloper.listener.OnPermissionListener;
import com.hpdeveloper.listener.PermissionDeniedResponse;
import com.hpdeveloper.listener.PermissionGrantedResponse;
import com.hpdeveloper.listener.PermissionRequest;
import com.hpdeveloper.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

public class SampleMultiplePermissionListener implements MultiplePermissionsListener {

    private OnPermissionListener onPermissionListener;

    public SampleMultiplePermissionListener(OnPermissionListener listener) {
        this.onPermissionListener = listener;
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport report) {
        List<String> grantedPermissions = new ArrayList<>();
        List<DeniedPermissionEntity> deniedPermissions = new ArrayList<>();
        for (PermissionGrantedResponse response : report.getGrantedPermissionResponses()) {
            grantedPermissions.add(response.getPermissionName());
        }
        onPermissionListener.onPermissionGranted(grantedPermissions);

        for (PermissionDeniedResponse response : report.getDeniedPermissionResponses()) {
            DeniedPermissionEntity deniedPermissionEntity = new DeniedPermissionEntity();
            deniedPermissionEntity.permissionName = response.getPermissionName();
            deniedPermissionEntity.isDeniedPermanent = response.isPermanentlyDenied();
            deniedPermissions.add(deniedPermissionEntity);
        }

        onPermissionListener.onPermissionDenied(deniedPermissions);
    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                   PermissionToken token) {
        onPermissionListener.onPermissionRational(permissions, token);
    }
}
