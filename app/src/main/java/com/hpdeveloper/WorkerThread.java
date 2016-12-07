
package com.hpdeveloper;

import android.os.Handler;
import android.os.Looper;

/**
 * A thread to execute passed runnable objects on a worker thread
 */
final class WorkerThread implements com.hpdeveloper.Thread {

  private final Handler handler;

  WorkerThread() {
    if (Looper.myLooper() == null) {
      Looper.prepare();
    }
    handler = new Handler();
  }

  @Override
  public void execute(final Runnable runnable) {
    handler.post(runnable);
  }

  @Override
  public void loop() {
    Looper.loop();
  }
}
