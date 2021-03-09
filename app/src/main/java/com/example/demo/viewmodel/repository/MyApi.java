package com.example.demo.viewmodel.repository;

import com.example.demo.model.UserListResponse;
import com.example.demo.model.UserModelData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {

    @GET("users?")
    Call<UserListResponse> getartistdata(@Query("page") int pageCount);


}