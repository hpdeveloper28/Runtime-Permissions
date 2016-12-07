package com.hpdeveloper.listener.single;

import com.hpdeveloper.PermissionToken;
import com.hpdeveloper.listener.PermissionDeniedResponse;
import com.hpdeveloper.listener.PermissionGrantedResponse;
import com.hpdeveloper.listener.PermissionRequest;

import java.util.Arrays;
import java.util.Collection;

/**
 * Listener that composes multiple listeners into one
 * All inner listeners will be called for a given event unless one of them throws an exception or
 * is blocked
 */
public class CompositePermissionListener implements PermissionListener {

  private final Collection<PermissionListener> listeners;

  /**
   * Creates a {@link CompositePermissionListener} containing all the provided listeners.
   * This constructor does not guaranty any calling order on inner listeners.
   */
  public CompositePermissionListener(PermissionListener... listeners) {
    this(Arrays.asList(listeners));
  }

  /**
   * Creates a {@link CompositePermissionListener} containing all the provided listeners.
   * This constructor will guaranty that inner listeners are called following the iterator order
   * of the collection.
   */
  public CompositePermissionListener(Collection<PermissionListener> listeners) {
    this.listeners = listeners;
  }

  @Override
  public void onPermissionGranted(PermissionGrantedResponse response) {
    for (PermissionListener listener : listeners) {
      listener.onPermissionGranted(response);
    }
  }

  @Override
  public void onPermissionDenied(PermissionDeniedResponse response) {
    for (PermissionListener listener : listeners) {
      listener.onPermissionDenied(response);
    }
  }

  @Override
  public void onPermissionRationaleShouldBeShown(PermissionRequest permission,
      PermissionToken token) {
    for (PermissionListener listener : listeners) {
      listener.onPermissionRationaleShouldBeShown(permission, token);
    }
  }
}
