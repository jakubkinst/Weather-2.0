package cz.kinst.jakub.weather20;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cz.kinst.jakub.viewmodelbinding.BaseViewModel;
import cz.kinst.jakub.viewmodelbinding.BaseViewModelActivity;
import cz.kinst.jakub.weather20.databinding.ActivityMainBinding;
import cz.kinst.jakub.weather20.viewmodel.MainViewModel;

public class MainActivity extends BaseViewModelActivity<ActivityMainBinding, MainViewModel> {

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

    @Override
    public int getViewModelDataBindingId() {
        return cz.kinst.jakub.weather20.BR.viewModel;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }
}
