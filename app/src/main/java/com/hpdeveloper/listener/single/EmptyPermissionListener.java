package com.hpdeveloper.listener.single;

import com.hpdeveloper.PermissionToken;
import com.hpdeveloper.listener.PermissionDeniedResponse;
import com.hpdeveloper.listener.PermissionGrantedResponse;
import com.hpdeveloper.listener.PermissionRequest;

/**
 * Empty implementation of {@link PermissionListener} to allow extensions to implement only the
 * required methods
 */
public class EmptyPermissionListener implements PermissionListener {

  @Override
  public void onPermissionGranted(PermissionGrantedResponse response) {

  }

  @Override
  public void onPermissionDenied(PermissionDeniedResponse response) {

  }

  @Override
  public void onPermissionRationaleShouldBeShown(PermissionRequest permission,
      PermissionToken token) {

  }
}
