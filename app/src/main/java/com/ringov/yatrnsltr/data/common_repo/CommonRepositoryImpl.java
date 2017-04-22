package com.ringov.yatrnsltr.data.common_repo;

import com.ringov.yatrnsltr.Config;
import com.ringov.yatrnsltr.api.ApiFactory;
import com.ringov.yatrnsltr.api.TranslationRetrofitService;
import com.ringov.yatrnsltr.api.raw_entity.LanguageItem;
import com.ringov.yatrnsltr.data.SharedPreferencesStorage;
import com.ringov.yatrnsltr.data.lang.Language;
import com.ringov.yatrnsltr.translation_module.entities.LangPairData;

import java.util.List;

import io.realm.Realm;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by Sergey Koltsov on 17.04.2017.
 */

public class CommonRepositoryImpl implements CommonRepository {

    private static final long langUpdateExpired = 1000 * 60 * 60 * 24 * 7; // 7 days
    private LangPairData crtLangPair;

    private BehaviorSubject<LangPairData> changingLanguageEvents;
    private BehaviorSubject<Boolean> changingModeEvents;

    CommonRepositoryImpl() {
        changingModeEvents = BehaviorSubject.create();
        changingLanguageEvents = BehaviorSubject.create();
    }


    @Override
    public Observable<LangPairData> loadLastLangPair() {
        crtLangPair = SharedPreferencesStorage.loadLastLangPair();
        changingLanguageEvents.onNext(crtLangPair);
        return Observable.just(crtLangPair);
    }

    @Override
    public void saveLastLangPair() {
        changingLanguageEvents.onNext(crtLangPair);
        SharedPreferencesStorage.saveLastLangPair(crtLangPair);
    }

    @Override
    public Observable<Boolean> changeStonedMode(boolean stonedMode) {
        return SharedPreferencesStorage.saveStonedMode(stonedMode)
                .doOnNext(changingModeEvents::onNext);
    }

    @Override
    public Observable<Boolean> loadStonedMode() {
        return SharedPreferencesStorage.loadStonedMode()
                .doOnNext(changingModeEvents::onNext);
    }

    @Override
    public Observable<Boolean> subscribeToModeChanging() {
        return changingModeEvents;
    }

    @Override
    public Observable<List<Language>> loadAllLanguages() {
        // update languages once in 7 days
        long lastUpdate = SharedPreferencesStorage.loadLastLanguagesUpdate();
        long crt = System.currentTimeMillis();
        if (crt - lastUpdate < langUpdateExpired) {
            return Observable.just(Realm.getDefaultInstance()
                    .copyFromRealm(Realm.getDefaultInstance()
                            .where(Language.class)
                            .findAll()));
        } else {
            return getService().getAllLanguages(Config.API_KEY,
                    new Language(Language.SupportedLanguage.RU).getShortName())
                    .flatMap(response -> Observable.from(response.getAllLangs()))
                    .map(this::convertToLanguage)
                    .toList()
                    .doOnNext(langs -> SharedPreferencesStorage.saveLanguagesUpdate(crt))
                    .doOnNext(langs ->
                            Realm.getDefaultInstance().executeTransaction(realm -> {
                                realm.delete(Language.class);
                                realm.insert(langs);
                            }));
        }
    }

    @Override
    public Observable<LangPairData> subscribeToLangPairChanging() {
        return changingLanguageEvents;
    }

    @Override
    public Observable<LangPairData> changeLangPair(LangPairData langPair) {
        crtLangPair = langPair;
        return Observable.just(langPair);
    }

    private Language convertToLanguage(LanguageItem languageItem) {
        Language language = new Language(languageItem.getShortName());
        language.setFullOriginalName(languageItem.getFullName());
        return language;
    }

    private TranslationRetrofitService getService() {
        return ApiFactory.getRetrofitService(TranslationRetrofitService.class);
    }
}
