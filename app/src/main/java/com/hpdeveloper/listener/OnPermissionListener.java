package com.hpdeveloper.listener;

import com.hpdeveloper.DeniedPermissionEntity;
import com.hpdeveloper.PermissionToken;

import java.util.List;

/**
 * Created by hiren.patel on 08-12-2016.
 */
public interface OnPermissionListener {

    void onPermissionGranted(List<String> permissions);

    void onPermissionDenied(List<DeniedPermissionEntity> permissions);

    void onPermissionRational(List<PermissionRequest> permissions, PermissionToken token);
}
