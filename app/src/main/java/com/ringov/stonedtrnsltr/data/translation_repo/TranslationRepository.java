package com.ringov.stonedtrnsltr.data.translation_repo;

import com.ringov.stonedtrnsltr.translation_module.entities.LangPairData;
import com.ringov.stonedtrnsltr.translation_module.entities.TranslationData;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Sergey Koltsov on 11.04.2017.
 */

public interface TranslationRepository {
    Observable<TranslationData> translate(String text, LangPairData langPair);

    PublishSubject<TranslationData> subscribeToTranslation();
}
