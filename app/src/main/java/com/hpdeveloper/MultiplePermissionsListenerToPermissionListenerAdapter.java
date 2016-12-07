

package com.hpdeveloper;

import com.hpdeveloper.listener.PermissionDeniedResponse;
import com.hpdeveloper.listener.PermissionGrantedResponse;
import com.hpdeveloper.listener.PermissionRequest;
import com.hpdeveloper.listener.multi.MultiplePermissionsListener;
import com.hpdeveloper.listener.single.PermissionListener;

import java.util.List;

/**
 * Adapter to translate calls to a {@link MultiplePermissionsListener} into @{PermissionListener}
 * methods
 */
final class MultiplePermissionsListenerToPermissionListenerAdapter
    implements MultiplePermissionsListener {

  private final PermissionListener listener;

  public MultiplePermissionsListenerToPermissionListenerAdapter(PermissionListener listener) {
    this.listener = listener;
  }

  @Override
  public void onPermissionsChecked(MultiplePermissionsReport report) {
    List<PermissionDeniedResponse> deniedResponses = report.getDeniedPermissionResponses();
    List<PermissionGrantedResponse> grantedResponses = report.getGrantedPermissionResponses();

    if (!deniedResponses.isEmpty()) {
      PermissionDeniedResponse response = deniedResponses.get(0);
      listener.onPermissionDenied(response);
    } else {
      PermissionGrantedResponse response = grantedResponses.get(0);
      listener.onPermissionGranted(response);
    }
  }

  @Override
  public void onPermissionRationaleShouldBeShown(List<PermissionRequest> requests,
      PermissionToken token) {
    PermissionRequest firstRequest = requests.get(0);
    listener.onPermissionRationaleShouldBeShown(firstRequest, token);
  }
}
