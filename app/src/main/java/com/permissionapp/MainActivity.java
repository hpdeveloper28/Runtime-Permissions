package com.permissionapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hpdeveloper.DeniedPermissionEntity;
import com.hpdeveloper.Dexter;
import com.hpdeveloper.PermissionToken;
import com.hpdeveloper.PermissionUtils;
import com.hpdeveloper.SampleMultiplePermissionListener;
import com.hpdeveloper.SamplePermissionListener;
import com.hpdeveloper.listener.OnPermissionListener;
import com.hpdeveloper.listener.PermissionRequest;
import com.hpdeveloper.listener.multi.CompositeMultiplePermissionsListener;
import com.hpdeveloper.listener.multi.MultiplePermissionsListener;
import com.hpdeveloper.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;
import com.hpdeveloper.listener.single.CompositePermissionListener;
import com.hpdeveloper.listener.single.PermissionListener;
import com.hpdeveloper.listener.single.SnackbarOnDeniedPermissionListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements OnPermissionListener{

    private MultiplePermissionsListener allPermissionsListener;
    private PermissionListener contactsPermissionListener;
    private PermissionListener cameraPermissionListener;
    private PermissionListener locationPermissionListener;
    private ViewGroup rootView;
    private String clickedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = (ViewGroup) findViewById(android.R.id.content);

        createPermissionListeners();
    /*
     * If during the rotate screen process the activity has been restarted you can call this method
     * to start with the check permission process without keep in an Android Bundle the state of
     * the request permission process.
     */
        Dexter.continuePendingRequestsIfPossible(allPermissionsListener);
    }

    public void onAllButtonClick(View view) {
        if (Dexter.isRequestOngoing()) {
            return;
        }
        setTag(view);
        Dexter.checkPermissions(allPermissionsListener, Manifest.permission.CAMERA,
                Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public void onCameraClick(View view) {
        if (Dexter.isRequestOngoing()) {
            return;
        }
        setTag(view);
        Dexter.checkPermission(cameraPermissionListener, Manifest.permission.CAMERA);
    }

    public void onContactButtonClick(View view) {
        if (Dexter.isRequestOngoing()) {
            return;
        }
        setTag(view);
        Dexter.checkPermission(contactsPermissionListener, Manifest.permission.READ_CONTACTS);
    }

    public void onLocationButtonClick(View view) {
        if (Dexter.isRequestOngoing()) {
            return;
        }
        setTag(view);
        Dexter.checkPermission(locationPermissionListener, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private void setTag(View view){
        Button button = (Button) view;
        clickedButton = (String) button.getTag();
    }


    private boolean containsString(List<String> list, String value){
        for (String string : list){
            if(string.equals(value)){
                return true;
            }else {
                continue;
            }
        }
        return false;
    }

    private boolean contains(List<DeniedPermissionEntity> list, String value){
        for (DeniedPermissionEntity string : list){
            if(string.permissionName.equals(value)){
                return true;
            }else {
                continue;
            }
        }
        return false;
    }

    private void createPermissionListeners() {

        // Add all permission in hashmap to find description
        PermissionUtils.addPermissionMap(MainActivity.this);

        PermissionListener feedbackViewPermissionListener = new SamplePermissionListener(this);
        MultiplePermissionsListener feedbackViewMultiplePermissionListener = new SampleMultiplePermissionListener(this);

        allPermissionsListener =
                new CompositeMultiplePermissionsListener(feedbackViewMultiplePermissionListener,
                        SnackbarOnAnyDeniedMultiplePermissionsListener.Builder.with(rootView,
                                R.string.all_permissions_denied_feedback)
                                .withOpenSettingsButton(R.string.permission_rationale_settings_button_text)
                                .build());
        contactsPermissionListener = new CompositePermissionListener(feedbackViewPermissionListener,
                SnackbarOnDeniedPermissionListener.Builder.with(rootView,
                        R.string.contacts_permission_denied_feedback)
                        .withOpenSettingsButton(R.string.permission_rationale_settings_button_text)
                        .withCallback(new Snackbar.Callback() {
                            @Override
                            public void onShown(Snackbar snackbar) {
                                super.onShown(snackbar);
                            }

                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                            }
                        })
                        .build());


        cameraPermissionListener = new CompositePermissionListener(feedbackViewPermissionListener,
                SnackbarOnDeniedPermissionListener.Builder.with(rootView,
                        R.string.camera_permission_denied_feedback)
                        .withOpenSettingsButton(R.string.permission_rationale_settings_button_text)
                        .withCallback(new Snackbar.Callback() {
                            @Override
                            public void onShown(Snackbar snackbar) {
                                super.onShown(snackbar);
                            }

                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                            }
                        })
                        .build());

        locationPermissionListener = new CompositePermissionListener(feedbackViewPermissionListener,
                SnackbarOnDeniedPermissionListener.Builder.with(rootView,
                        R.string.location_permission_denied_feedback)
                        .withOpenSettingsButton(R.string.permission_rationale_settings_button_text)
                        .withCallback(new Snackbar.Callback() {
                            @Override
                            public void onShown(Snackbar snackbar) {
                                super.onShown(snackbar);
                            }

                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                            }
                        })
                        .build());

    }

    @Override
    public void onPermissionGranted(List<String> permissions) {
        if(permissions!=null&&!permissions.isEmpty()){
            if(clickedButton.equals("")){
                String allGrantedPermissnions = TextUtils.join(",", permissions);
                Toast.makeText(MainActivity.this, "Granted: "+allGrantedPermissnions, Toast.LENGTH_SHORT).show();
            }else if(containsString(permissions, clickedButton)){
                Toast.makeText(MainActivity.this, "Granted: "+clickedButton, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPermissionDenied(List<DeniedPermissionEntity> permissions) {
        if(permissions!=null&&!permissions.isEmpty()){
            if(clickedButton.equals("")){
                List<String> list = new ArrayList<>();
                for (DeniedPermissionEntity deniedPermissionEntity : permissions){
                    list.add(deniedPermissionEntity.permissionName);
                }
                String allDeniedPermissnions = TextUtils.join(",", list);
                Toast.makeText(MainActivity.this, "Denied: " + allDeniedPermissnions, Toast.LENGTH_SHORT).show();
            }else if(contains(permissions, clickedButton)){
                Toast.makeText(MainActivity.this, "Denied: "+clickedButton, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPermissionRational(List<PermissionRequest> permissions, final PermissionToken token) {
        StringBuilder message = new StringBuilder();
        for (PermissionRequest permissionRequest : permissions){
            message.append(PermissionUtils.getPermissionDetails(permissionRequest.getName()));
            message.append(",\n");
        }

        new AlertDialog.Builder(this).setTitle(R.string.permission_rationale_title)
                .setMessage(message.toString())
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        token.cancelPermissionRequest();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        token.continuePermissionRequest();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        token.cancelPermissionRequest();
                    }
                })
                .show();
    }
}
