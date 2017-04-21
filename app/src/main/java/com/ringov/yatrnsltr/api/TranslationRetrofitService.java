package com.ringov.yatrnsltr.api;

import com.ringov.yatrnsltr.api.raw_entity.LanguagesResponse;
import com.ringov.yatrnsltr.api.raw_entity.TranslationResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Sergey Koltsov on 11.04.2017.
 */

public interface TranslationRetrofitService {

    @FormUrlEncoded
    @POST("requestTranslate")
    Observable<TranslationResponse> translate(@Field("key") String key,
                                              @Field("lang") String lang,
                                              @Field("text") String text);

    @FormUrlEncoded
    @POST("getLangs")
    Observable<LanguagesResponse> getAllLanguages(@Field("key") String key,
                                                  @Field("ui") String langOfResult);

}
