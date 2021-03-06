package com.ringov.stonedtrnsltr.base.implementations;

import android.os.Bundle;

import com.ringov.stonedtrnsltr.MessageDialogHelper;
import com.ringov.stonedtrnsltr.R;
import com.ringov.stonedtrnsltr.base.BasePresenter;
import com.ringov.stonedtrnsltr.base.interfaces.BaseView;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

/**
 * Created by Sergey Koltsov on 18.04.2017.
 */

public abstract class BaseActivity<PRESENTER extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected PRESENTER mPresenter;

    protected abstract PRESENTER providePresenter();

    protected abstract @LayoutRes
    int getLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        mPresenter = providePresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onViewResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onViewPaused();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
    }

    @Override
    public void showLoading() {
        // nothing
    }

    @Override
    public void hideLoading() {
        // nothing
    }

    @Override
    public void showKnownException(String message) {
        MessageDialogHelper.getErrorDialog(this, getString(R.string.exception), message);
    }

    @Override
    public void showInternalException(String message) {
        MessageDialogHelper.getErrorDialog(this, getString(R.string.exception),
                getString(R.string.internal_exception_appeared));
    }

    @Override
    public void showUnknownException(String message) {
        MessageDialogHelper.getErrorDialog(this, getString(R.string.exception),
                getString(R.string.unknown_exception_appeared));
    }

    @Override
    public void showInternetConnectionException(String message) {
        MessageDialogHelper.getErrorDialog(this, getString(R.string.exception),
                getString(R.string.no_internet_connection_text)).show();
    }
}
