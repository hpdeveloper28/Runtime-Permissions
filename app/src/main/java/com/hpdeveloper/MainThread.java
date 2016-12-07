
package com.hpdeveloper;

import android.os.Handler;
import android.os.Looper;

/**
 * A thread to execute passed runnable objects in the main thread
 */
final class MainThread implements com.hpdeveloper.Thread {

  MainThread() {
  }

  @Override
  public void execute(Runnable runnable) {
    if (runningMainThread()) {
      runnable.run();
    } else {
      new Handler(Looper.getMainLooper()).post(runnable);
    }
  }

  @Override
  public void loop() {
  }

  private static boolean runningMainThread() {
    return Looper.getMainLooper() == Looper.myLooper();
  }
}
