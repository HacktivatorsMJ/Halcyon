package com.hacktivators.mentalhealth.BackEnd;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Service {


    @FormUrlEncoded
    @POST("/taylor")
    Call<ResponseBody> postTaylorMessage(@Field("chatInput") String message);

    @FormUrlEncoded
    @POST("/cheerful")
    Call<ResponseBody> postCheerfulMessage(@Field("chatInput") String message);

    @FormUrlEncoded
    @POST("/sarcastic")
    Call<ResponseBody> postSarcasticMessage(@Field("chatInput") String message);

    @FormUrlEncoded
    @POST("/nsfw")
    Call<ResponseBody> checkNSFW(@Field("chatInput") String message);


    @POST("/createUser")
    Call<ResponseBody> postNewUser(@Body RequestBody user);



}
