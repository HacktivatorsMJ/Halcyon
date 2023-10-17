package com.hacktivators.mentalhealth.BackEnd;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Service {


    @FormUrlEncoded
    @POST("/sarora")
    Call<ResponseBody> postMessage(@Field("chatInput") String message);

}
