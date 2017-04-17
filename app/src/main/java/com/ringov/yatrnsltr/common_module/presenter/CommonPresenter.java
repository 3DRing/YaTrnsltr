package com.ringov.yatrnsltr.common_module.presenter;

import com.ringov.yatrnsltr.Utils;
import com.ringov.yatrnsltr.base.BasePresenter;
import com.ringov.yatrnsltr.common_module.interactor.CommonInteractor;
import com.ringov.yatrnsltr.common_module.router.CommonRouter;
import com.ringov.yatrnsltr.common_module.view.CommonView;

/**
 * Created by Sergey Koltsov on 17.04.2017.
 */

public class CommonPresenter extends BasePresenter<CommonView, CommonRouter, CommonInteractor> {

    public CommonPresenter(CommonView view, CommonRouter router, CommonInteractor interactor) {
        super(view, router, interactor);

        loadStonedMode();
    }

    /**
     * each view can be switched from one mode to another
     */
    public void onStonedModeChangedClicked() {
        mSubscription.add(getInteractor().changeStonedMode()
                .compose(Utils.setRxSchedulers())
                .subscribe(getView()::setStonedMode, this::handleError));
    }

    /**
     * at start of any view we call this method
     * to understand which mode we should apply
     */
    private void loadStonedMode() {
        mSubscription.add(getInteractor().loadStonedMode()
                .compose(Utils.setRxSchedulers())
                .subscribe(getView()::setStonedMode, this::handleError));
    }

    @Override
    public void onViewResumed() {

    }

    @Override
    public void onViewPaused() {

    }
}