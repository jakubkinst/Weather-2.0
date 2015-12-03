package cz.kinst.jakub.weather20;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.HashMap;
import java.util.Map;

import cz.kinst.jakub.viewmodelbinding.BaseViewModel;
import cz.kinst.jakub.viewmodelbinding.BaseViewModelActivity;
import cz.kinst.jakub.weather20.databinding.ActivityMainBinding;
import cz.kinst.jakub.weather20.viewmodel.MainViewModel;


public class MainActivity extends BaseViewModelActivity<ActivityMainBinding, MainViewModel> {

	private static final int REQUEST_PERMISSIONS = 100;
	private PermissionRequestCallback mPermissionRequestCallback;


	public interface PermissionRequestCallback {
		void onPermissionResult(Map<String, Boolean> results);
	}


	@Override
	public int getViewModelDataBindingId() {
		return cz.kinst.jakub.weather20.BR.viewModel;
	}


	@Override
	public int getLayoutResource() {
		return R.layout.activity_main;
	}


	public boolean checkGrantedPermission(String permission) {
		return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
	}


	public void requestPermissions(String[] permissions, PermissionRequestCallback callback) {
		mPermissionRequestCallback = callback;
		ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
	}


	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		Map<String, Boolean> results = new HashMap<>();
		for(int i = 0; i < permissions.length; i++)
			results.put(permissions[i], grantResults[i] == PackageManager.PERMISSION_GRANTED);


		if(mPermissionRequestCallback != null)
			mPermissionRequestCallback.onPermissionResult(results);
		mPermissionRequestCallback = null;
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setSupportActionBar(getBinding().toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
	}


	@Override
	protected Class<? extends BaseViewModel> getViewModelClass() {
		return MainViewModel.class;
	}
}
