package com.hpdeveloper;

import android.os.Handler;
import android.os.Looper;

import com.hpdeveloper.listener.OnPermissionListener;
import com.hpdeveloper.listener.PermissionDeniedResponse;
import com.hpdeveloper.listener.PermissionGrantedResponse;
import com.hpdeveloper.listener.PermissionRequest;

/**
 * Sample listener that shows how to handle permission request callbacks on a background thread
 */
public class SampleBackgroundThreadPermissionListener extends SamplePermissionListener {

  private Handler handler = new Handler(Looper.getMainLooper());

  public SampleBackgroundThreadPermissionListener(OnPermissionListener onPermissionListener) {
    super(onPermissionListener);
  }

  @Override
  public void onPermissionGranted(final PermissionGrantedResponse response) {
    handler.post(new Runnable() {
      @Override
      public void run() {
        SampleBackgroundThreadPermissionListener.super.onPermissionGranted(response);
      }
    });
  }

  @Override
  public void onPermissionDenied(final PermissionDeniedResponse response) {
    handler.post(new Runnable() {
      @Override
      public void run() {
        SampleBackgroundThreadPermissionListener.super.onPermissionDenied(response);
      }
    });
  }

  @Override
  public void onPermissionRationaleShouldBeShown(final PermissionRequest permission,
      final PermissionToken token) {
    handler.post(new Runnable() {
      @Override
      public void run() {
        SampleBackgroundThreadPermissionListener.super.onPermissionRationaleShouldBeShown(
            permission, token);
      }
    });
  }
}
