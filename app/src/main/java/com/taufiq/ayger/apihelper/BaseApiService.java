package com.taufiq.ayger.apihelper;

import com.taufiq.ayger.Adapter.JsonModel;
import com.taufiq.ayger.Model.ResponseLapangan;
import com.taufiq.ayger.Model.SemuaLapangan;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/login.php
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    @GET("getLapangan.php?id=")
    Call<ResponseLapangan> postMesssage(@Query("id") String id);

    @FormUrlEncoded
    @POST("inupSaldo.php")
    Call<ResponseBody> insertupdate(@Field("saldo") String user,
                                    @Field("idvendor") String idvendor,
                                    @Field("iduser") String iduser,
                                    @Field("idalat")String idalat);
    @GET("getHistoryBook.php")
    Call<ResponseLapangan> getRecent(@Query("id") String id);
}


