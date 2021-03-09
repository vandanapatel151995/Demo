package com.example.demo.viewmodel.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyClient {
    private static final String BASE_URL="https://reqres.in/api/";
    private static MyClient myClient;
    private Retrofit retrofit;
    private MyClient(){
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized MyClient getInstance(){
        if (myClient==null){
            myClient=new MyClient();
        }
        return myClient;
    }
    public MyApi getMyApi(){
        return retrofit.create(MyApi.class);
    }
}