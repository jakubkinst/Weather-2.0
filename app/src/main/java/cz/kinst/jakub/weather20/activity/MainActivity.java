package cz.kinst.jakub.weather20.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import cz.kinst.jakub.viewmodelbinding.BaseViewModel;
import cz.kinst.jakub.viewmodelbinding.BaseViewModelActivity;
import cz.kinst.jakub.viewmodelbinding.permissions.PermissionHelperProvider;
import cz.kinst.jakub.viewmodelbinding.permissions.PermissionsHelper;
import cz.kinst.jakub.weather20.Preferences;
import cz.kinst.jakub.weather20.R;
import cz.kinst.jakub.weather20.databinding.ActivityMainBinding;
import cz.kinst.jakub.weather20.viewmodel.MainViewModel;


public class MainActivity extends BaseViewModelActivity<ActivityMainBinding, MainViewModel> implements PermissionHelperProvider {

	private PermissionsHelper mPermissionsHelper = new PermissionsHelper(this);


	@Override
	public int getViewModelDataBindingId() {
		return cz.kinst.jakub.weather20.BR.viewModel;
	}


	@Override
	public int getLayoutResource() {
		return R.layout.activity_main;
	}


	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		mPermissionsHelper.onRequestPermissionResult(requestCode, permissions, grantResults);
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}


	@Override
	public PermissionsHelper getPermissionHelper() {
		return mPermissionsHelper;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.findItem(R.id.action_switch_units).setTitle(Preferences.get().isTempMetric() ? R.string.action_switch_to_imperial : R.string.action_switch_to_metric);
		return super.onPrepareOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.action_switch_units)
			Preferences.get().setTempMetric(!Preferences.get().isTempMetric());
		invalidateOptionsMenu();
		return super.onOptionsItemSelected(item);
	}


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setSupportActionBar(getBinding().toolbar);
		if(getSupportActionBar() != null)
			getSupportActionBar().setDisplayShowTitleEnabled(false);
	}


	@Override
	protected Class<? extends BaseViewModel> getViewModelClass() {
		return MainViewModel.class;
	}
}
