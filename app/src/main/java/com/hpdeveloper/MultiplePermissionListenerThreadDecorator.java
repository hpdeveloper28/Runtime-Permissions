
package com.hpdeveloper;

import com.hpdeveloper.listener.PermissionRequest;
import com.hpdeveloper.listener.multi.MultiplePermissionsListener;

import java.util.List;

/**
 * Decorator to execute the permission updates on a given thread
 */
final class MultiplePermissionListenerThreadDecorator implements MultiplePermissionsListener {

  private final MultiplePermissionsListener listener;
  private final Thread thread;

  MultiplePermissionListenerThreadDecorator(MultiplePermissionsListener listener,
      Thread thread) {
    this.thread = thread;
    this.listener = listener;
  }

  /**
   * Decorates de permission listener execution with a given thread
   *
   * @param report In detail report with all the permissions that has been denied and granted
   */
  @Override
  public void onPermissionsChecked(final MultiplePermissionsReport report) {
    thread.execute(new Runnable() {
      @Override
      public void run() {
        listener.onPermissionsChecked(report);
      }
    });
  }

  /**
   * Decorates de permission listener execution with a given thread
   *
   * @param permissions The permissions that has been requested. Collections of values found in
   * {@link android.Manifest.permission}
   * @param token Token used to continue or cancel the permission request process. The permission
   * request process will remain blocked until one of the token methods is called
   */
  @Override
  public void onPermissionRationaleShouldBeShown(
      final List<PermissionRequest> permissions, final PermissionToken token) {
    thread.execute(new Runnable() {
      @Override
      public void run() {
        listener.onPermissionRationaleShouldBeShown(permissions, token);
      }
    });
  }
}
