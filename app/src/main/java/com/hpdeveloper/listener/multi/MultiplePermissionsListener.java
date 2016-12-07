package com.hpdeveloper.listener.multi;

import com.hpdeveloper.MultiplePermissionsReport;
import com.hpdeveloper.PermissionToken;
import com.hpdeveloper.listener.PermissionRequest;

import java.util.List;

/**
 * Interface that listens to updates to the permission requests
 */
public interface MultiplePermissionsListener {

  /**
   * Method called when all permissions has been completely checked
   *
   * @param report In detail report with all the permissions that has been denied and granted
   */
  void onPermissionsChecked(MultiplePermissionsReport report);

  /**
   * Method called whenever Android asks the application to inform the user of the need for the
   * requested permissions. The request process won't continue until the token is properly used
   *
   * @param permissions The permissions that has been requested. Collections of values found in
   * {@link android.Manifest.permission}
   * @param token Token used to continue or cancel the permission request process. The permission
   * request process will remain blocked until one of the token methods is called
   */
  void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                          PermissionToken token);
}
