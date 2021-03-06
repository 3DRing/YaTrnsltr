package com.ringov.stonedtrnsltr.exceptions;

import com.ringov.stonedtrnsltr.exceptions.base.BaseException;

/**
 * Should be thrown in presenters in case of missing Activity (implementing some View)
 *
 * Created by Sergey Koltsov on 16.04.2017.
 */

public class ViewMissingException extends BaseException {
    public ViewMissingException(String message) {
        super(message);
    }
}
