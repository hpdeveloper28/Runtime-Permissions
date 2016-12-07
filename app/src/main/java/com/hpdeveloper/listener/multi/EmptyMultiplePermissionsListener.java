package com.hpdeveloper.listener.multi;

import com.hpdeveloper.MultiplePermissionsReport;
import com.hpdeveloper.PermissionToken;
import com.hpdeveloper.listener.PermissionRequest;

import java.util.List;

/**
 * Empty implementation of {@link MultiplePermissionsListener} to allow extensions to implement
 * only the required methods
 */
public class EmptyMultiplePermissionsListener implements MultiplePermissionsListener {

  @Override
  public void onPermissionsChecked(MultiplePermissionsReport report) {

  }

  @Override
  public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
      PermissionToken token) {

  }
}
