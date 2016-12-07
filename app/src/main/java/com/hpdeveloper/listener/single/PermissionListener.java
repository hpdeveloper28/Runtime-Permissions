package com.hpdeveloper.listener.single;

import com.hpdeveloper.PermissionToken;
import com.hpdeveloper.listener.PermissionDeniedResponse;
import com.hpdeveloper.listener.PermissionGrantedResponse;
import com.hpdeveloper.listener.PermissionRequest;

/**
 * Interface that listens to updates to the permission requests
 */
public interface PermissionListener {

  /**
   * Method called whenever a requested permission has been granted
   *
   * @param response A response object that contains the permission that has been requested and
   * any additional flags relevant to this response
   */
  void onPermissionGranted(PermissionGrantedResponse response);

  /**
   * Method called whenever a requested permission has been denied
   *
   * @param response A response object that contains the permission that has been requested and
   * any additional flags relevant to this response
   */
  void onPermissionDenied(PermissionDeniedResponse response);

  /**
   * Method called whenever Android asks the application to inform the user of the need for the
   * requested permission. The request process won't continue until the token is properly used
   *
   * @param permission The permission that has been requested
   * @param token Token used to continue or cancel the permission request process. The permission
   * request process will remain blocked until one of the token methods is called
   */
  void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token);
}
