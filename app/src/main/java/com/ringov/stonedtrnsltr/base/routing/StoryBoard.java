package com.ringov.stonedtrnsltr.base.routing;

import android.content.Intent;
import android.net.Uri;

import com.ringov.stonedtrnsltr.about_module.AboutActivity;
import com.ringov.stonedtrnsltr.common_module.view.ChooseLanguageActivity;

import androidx.core.app.ShareCompat;

/**
 * Created by Sergey Koltsov on 14.04.2017.
 */

public class StoryBoard {
    public static StoryDestination yandexTranslateLink() {
        return activity -> {
            String url = "https://www.translate.yandex.ru";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            activity.startActivity(i);
        };
    }

    public static StoryDestination chooseLanguageScreen() {
        return activity -> {
            Intent i = new Intent(activity, ChooseLanguageActivity.class);
            activity.startActivity(i);
        };
    }

    public static StoryDestination openAboutScreen() {
        return activity -> {
            Intent i = new Intent(activity, AboutActivity.class);
            activity.startActivity(i);
        };
    }

    public static StoryDestination shareText(String translation) {
        return activity ->
                ShareCompat.IntentBuilder
                        .from(activity)
                        .setText(translation)
                        .setType("text/plain")
                        .setChooserTitle("Отправить перевод")
                        .startChooser();
    }
}
