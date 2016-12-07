
package com.hpdeveloper;

import com.hpdeveloper.listener.PermissionDeniedResponse;
import com.hpdeveloper.listener.PermissionGrantedResponse;
import com.hpdeveloper.listener.PermissionRequest;
import com.hpdeveloper.listener.single.PermissionListener;
import com.permissionapp.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class SamplePermissionListener implements PermissionListener {

  private final MainActivity activity;

  public SamplePermissionListener(MainActivity activity) {
    this.activity = activity;
  }

  @Override
  public void onPermissionGranted(PermissionGrantedResponse response) {
    List<String> permissionRequests = new ArrayList<>();
    permissionRequests.add(response.getPermissionName());

    activity.showPermissionGranted(permissionRequests);
  }

  @Override
  public void onPermissionDenied(PermissionDeniedResponse response) {
    List<DeniedPermissionEntity> permissionRequests = new ArrayList<>();

    DeniedPermissionEntity deniedPermissionEntity = new DeniedPermissionEntity();
    deniedPermissionEntity.permissionName = response.getPermissionName();
    deniedPermissionEntity.isDeniedPermanent = response.isPermanentlyDenied();
    permissionRequests.add(deniedPermissionEntity);

    activity.showPermissionDenied(permissionRequests);
  }

  @Override
  public void onPermissionRationaleShouldBeShown(PermissionRequest permission,
      PermissionToken token) {
    List<PermissionRequest> permissionRequests = new ArrayList<>();
    permissionRequests.add(permission);
    activity.showPermissionRationale(permissionRequests, token);
  }
}
