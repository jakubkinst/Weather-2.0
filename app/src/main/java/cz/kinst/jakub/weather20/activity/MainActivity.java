package cz.kinst.jakub.weather20.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import cz.kinst.jakub.viewmodelbinding.ViewModelActivity;
import cz.kinst.jakub.viewmodelbinding.ViewModelBindingConfig;
import cz.kinst.jakub.weather20.BR;
import cz.kinst.jakub.weather20.R;
import cz.kinst.jakub.weather20.databinding.ActivityMainBinding;
import cz.kinst.jakub.weather20.permissions.PermissionHelperProvider;
import cz.kinst.jakub.weather20.permissions.PermissionsHelper;
import cz.kinst.jakub.weather20.preferences.Preferences;
import cz.kinst.jakub.weather20.viewmodel.MainViewModel;


public class MainActivity extends ViewModelActivity<ActivityMainBinding, MainViewModel> implements PermissionHelperProvider {

	private PermissionsHelper mPermissionsHelper = new PermissionsHelper(this);


	@Override
	public ViewModelBindingConfig getViewModelBindingConfig() {
		return new ViewModelBindingConfig(R.layout.activity_main, MainViewModel.class, BR.viewModel);
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
}
