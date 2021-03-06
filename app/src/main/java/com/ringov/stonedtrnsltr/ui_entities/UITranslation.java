package com.ringov.stonedtrnsltr.ui_entities;

import com.ringov.stonedtrnsltr.storage_module.entities.StoredTranslationData;
import com.ringov.stonedtrnsltr.translation_module.entities.LangPairData;
import com.ringov.stonedtrnsltr.translation_module.entities.TranslationData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Koltsov on 11.04.2017.
 */

public class UITranslation {

    private static final long TEMPORARY_ID = -1;
    public static UITranslation EMPTY = new UITranslation();

    private long id;

    private String originalText;
    private String changedOriginal;

    private List<String> translations;
    private List<String> changedTranslations;

    private UILangPair langPair;

    private boolean favorite;
    private boolean changed;

    public UITranslation(TranslationData translationData, LangPairData langPair) {
        this.id = TEMPORARY_ID;

        this.originalText = translationData.getOriginal();
        this.changedOriginal = this.originalText;

        this.translations = translationData.getTranslations();
        this.changedTranslations = translationData.getTranslations();

        this.langPair = langPair.toUILangPair();
    }

    public UITranslation(StoredTranslationData translationData, LangPairData langPair) {
        this.id = translationData.getTimestamp();

        this.originalText = translationData.getOriginal();
        this.changedOriginal = this.originalText;

        this.translations = translationData.getTranslations();
        this.changedTranslations = translationData.getTranslations();

        this.langPair = langPair.toUILangPair();
    }

    /**
     * service constructor
     */
    private UITranslation() {
        this.translations = new ArrayList<>();
        this.originalText = "";
        this.langPair = UILangPair.EMPTY;
    }

    public void setChangedData(String changedOriginal, List<String> changedTranslations) {
        this.changedOriginal = changedOriginal;
        this.changedTranslations = changedTranslations;
    }

    public long getId() {
        return id;
    }

    public List<String> getTranslations() {
        return translations;
    }

    public String getOriginalText() {
        return originalText;
    }

    public UILangPair getLangPair() {
        return langPair;
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

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public String getChangedOriginal() {
        return changedOriginal;
    }

    public List<String> getChangedTranslations() {
        return changedTranslations;
    }
}
