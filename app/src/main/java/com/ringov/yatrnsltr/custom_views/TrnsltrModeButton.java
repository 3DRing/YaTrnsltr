package com.ringov.yatrnsltr.custom_views;

import android.content.Context;
import android.util.AttributeSet;

import com.ringov.yatrnsltr.R;

/**
 * Created by Sergey Koltsov on 10.04.2017.
 */

public class TrnsltrModeButton extends ToggleImageButton {
    public TrnsltrModeButton(Context context) {
        super(context);
    }

    public TrnsltrModeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TrnsltrModeButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int getOnImageRes() {
        return R.drawable.ic_stoned_bear_true;
    }

    @Override
    protected int getOffImageRes() {
        return R.drawable.ic_stoned_bear_false;
    }
}
