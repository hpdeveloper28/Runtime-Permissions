
package com.hpdeveloper;

import com.hpdeveloper.listener.OnPermissionListener;
import com.hpdeveloper.listener.PermissionDeniedResponse;
import com.hpdeveloper.listener.PermissionGrantedResponse;
import com.hpdeveloper.listener.PermissionRequest;
import com.hpdeveloper.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

public class SamplePermissionListener implements PermissionListener {

  private OnPermissionListener onPermissionListener;

  public SamplePermissionListener(OnPermissionListener listener) {
    this.onPermissionListener = listener;
  }

  @Override
  public void onPermissionGranted(PermissionGrantedResponse response) {
    List<String> permissionRequests = new ArrayList<>();
    permissionRequests.add(response.getPermissionName());

    onPermissionListener.onPermissionGranted(permissionRequests);
  }

  @Override
  public void onPermissionDenied(PermissionDeniedResponse response) {
    List<DeniedPermissionEntity> permissionRequests = new ArrayList<>();

    DeniedPermissionEntity deniedPermissionEntity = new DeniedPermissionEntity();
    deniedPermissionEntity.permissionName = response.getPermissionName();
    deniedPermissionEntity.isDeniedPermanent = response.isPermanentlyDenied();
    permissionRequests.add(deniedPermissionEntity);

    onPermissionListener.onPermissionDenied(permissionRequests);
  }

  @Override
  public void onPermissionRationaleShouldBeShown(PermissionRequest permission,
      PermissionToken token) {
    List<PermissionRequest> permissionRequests = new ArrayList<>();
    permissionRequests.add(permission);
    onPermissionListener.onPermissionRational(permissionRequests, token);
  }
}
