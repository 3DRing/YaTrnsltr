package com.ringov.stonedtrnsltr.ui_entities;

import com.ringov.stonedtrnsltr.common_module.entities.UILanguage;

/**
 * Created by Sergey Koltsov on 13.04.2017.
 */

public class UILangPair {
    public static final UILangPair EMPTY = new UILangPair();
    private UILanguage sourceLang;
    private UILanguage targetLang;

    public UILangPair(UILanguage sourceLang, UILanguage targetLang) {
        this.sourceLang = sourceLang;
        this.targetLang = targetLang;
    }

    /**
     * service constructor
     */
    public UILangPair() {
        this.sourceLang = UILanguage.EMPTY;
        this.targetLang = UILanguage.EMPTY;
    }

    public UILanguage getSourceLang() {
        return sourceLang;
    }

    public void setSourceLang(UILanguage sourceLang) {
        this.sourceLang = sourceLang;
    }

    public UILanguage getTargetLang() {
        return targetLang;
    }

    public void setTargetLang(UILanguage targetLang) {
        this.targetLang = targetLang;
    }
}
