package com.ringov.yatrnsltr.storage_module.interactor;

import com.ringov.yatrnsltr.base.interfaces.BaseInteractor;
import com.ringov.yatrnsltr.ui_entities.UITranslation;

import java.util.List;

import rx.Observable;

/**
 * Created by Sergey Koltsov on 14.04.2017.
 */

public interface StorageInteractor extends BaseInteractor {
    Observable<List<UITranslation>> loadHistory();
}
