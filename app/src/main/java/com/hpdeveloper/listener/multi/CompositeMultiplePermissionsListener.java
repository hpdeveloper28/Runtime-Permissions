package com.hpdeveloper.listener.multi;

import com.hpdeveloper.MultiplePermissionsReport;
import com.hpdeveloper.PermissionToken;
import com.hpdeveloper.listener.PermissionRequest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Listener that composes multiple listeners into one
 * All inner listeners will be called for a given event unless one of them throws an exception or
 * is blocked
 */
public class CompositeMultiplePermissionsListener implements MultiplePermissionsListener {

  private final Collection<MultiplePermissionsListener> listeners;

  /**
   * Creates a {@link CompositeMultiplePermissionsListener} containing all the provided listeners.
   * This constructor does not guaranty any calling order on inner listeners.
   */
  public CompositeMultiplePermissionsListener(MultiplePermissionsListener... listeners) {
    this(Arrays.asList(listeners));
  }

  /**
   * Creates a {@link CompositeMultiplePermissionsListener} containing all the provided listeners.
   * This constructor will guaranty that inner listeners are called following the iterator order
   * of the collection.
   */
  public CompositeMultiplePermissionsListener(Collection<MultiplePermissionsListener> listeners) {
    this.listeners = listeners;
  }

  @Override
  public void onPermissionsChecked(MultiplePermissionsReport report) {
    for (MultiplePermissionsListener listener : listeners) {
      listener.onPermissionsChecked(report);
    }
  }

  @Override
  public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
      PermissionToken token) {
    for (MultiplePermissionsListener listener : listeners) {
      listener.onPermissionRationaleShouldBeShown(permissions, token);
    }
  }
}
