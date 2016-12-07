
package com.hpdeveloper;

/**
 * Utility class to let clients show the user how is the permission going to be used
 * Clients of this class must call one of the two methods and only once
 */
public interface PermissionToken {

  /**
   * Continues with the permission request process
   */
  void continuePermissionRequest();

  /**
   * Cancels the permission request process
   */
  void cancelPermissionRequest();
}
