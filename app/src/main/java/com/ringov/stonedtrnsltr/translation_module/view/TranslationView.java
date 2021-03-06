package com.ringov.stonedtrnsltr.translation_module.view;

import com.ringov.stonedtrnsltr.base.interfaces.BaseView;
import com.ringov.stonedtrnsltr.ui_entities.UILangPair;
import com.ringov.stonedtrnsltr.ui_entities.UITranslation;

/**
 * Created by Sergey Koltsov on 11.04.2017.
 */

public interface TranslationView extends BaseView {
    void showTranslation(UITranslation translation);

    void showMoreOptions();

    void hideMoreOptions();

    void clearInputField();

    void showLanguagePair(UILangPair langPair);

    void hideKeyboard();

    // todo create a new parent class StonedView and move this method there
    void setStonedMode(boolean enable);

    void showTranslationAndInputText(UITranslation translation);

    void showCopiedToClipBoard();
}
