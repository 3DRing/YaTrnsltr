package com.ringov.stonedtrnsltr.data.translation_repo;

import com.ringov.stonedtrnsltr.Config;
import com.ringov.stonedtrnsltr.api.ApiFactory;
import com.ringov.stonedtrnsltr.api.TranslationRetrofitService;
import com.ringov.stonedtrnsltr.api.raw_entity.TranslationResponse;
import com.ringov.stonedtrnsltr.translation_module.entities.LangPairData;
import com.ringov.stonedtrnsltr.translation_module.entities.TranslationData;

import java.util.Locale;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Sergey Koltsov on 11.04.2017.
 */

public class TranslationRepositoryImpl implements TranslationRepository {

    private PublishSubject<TranslationData> translationEvents;

    TranslationRepositoryImpl() {
        translationEvents = PublishSubject.create();
    }

    private TranslationRetrofitService getService() {
        return ApiFactory.getRetrofitService(TranslationRetrofitService.class);
    }

    @Override
    public Observable<TranslationData> translate(String text, LangPairData langPair) {
        return getService().translate(Config.API_KEY, apiLangFormat(langPair), text)
                .map(response -> convertResponse(text, langPair, response))
                .doOnNext(translationEvents::onNext);
    }

    @Override
    public PublishSubject<TranslationData> subscribeToTranslation() {
        return translationEvents;
    }

    private TranslationData convertResponse(String originalText, LangPairData langPair, TranslationResponse response) {
        return new TranslationData(originalText, response.getText(), langPair);
    }

    private String apiLangFormat(LangPairData langPair) {
        return String.format(Locale.ENGLISH, "%1$s-%2$s", langPair.getSourceLang().getShortName(),
                langPair.getTargetLang().getShortName());
    }

}
