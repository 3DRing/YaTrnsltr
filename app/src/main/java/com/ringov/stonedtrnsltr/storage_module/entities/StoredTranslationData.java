package com.ringov.stonedtrnsltr.storage_module.entities;

import com.ringov.stonedtrnsltr.data.lang.Language;
import com.ringov.stonedtrnsltr.data.storage_repo.RealmString;
import com.ringov.stonedtrnsltr.translation_module.entities.LangPairData;
import com.ringov.stonedtrnsltr.translation_module.entities.TranslationData;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sergey Koltsov on 14.04.2017.
 */

public class StoredTranslationData extends RealmObject {

    @PrimaryKey
    private long timestamp;

    private String originalText;
    private RealmList<RealmString> translations;
    private String sourceLang;
    private String targetLang;
    private boolean favorite;
    private boolean changed;

    public StoredTranslationData() {

    }

    public StoredTranslationData(long timestamp, TranslationData translation, ExtraParams params) {
        this.timestamp = timestamp;
        this.originalText = translation.getOriginal();
        this.translations = new RealmList<>();
        for (String s :
                translation.getTranslations()) {
            translations.add(new RealmString(s));
        }
        this.sourceLang = translation.getLangPair().getSourceLang().getShortName();
        this.targetLang = translation.getLangPair().getTargetLang().getShortName();
        this.favorite = params.isFavorite();
        this.changed = params.isChanged();
    }

    public String getOriginal() {
        return originalText;
    }

    public List<String> getTranslations() {
        List<String> list = new ArrayList<>();
        for (RealmString s :
                translations) {
            list.add(s.value);
        }
        return list;
    }

    public LangPairData getLangPair() {
        return new LangPairData(new Language(sourceLang), new Language(targetLang));
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isChanged() {
        return changed;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
