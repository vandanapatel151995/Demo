package com.example.demo.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.demo.model.DataItem;
import com.example.demo.model.UserListResponse;
import com.example.demo.model.UserModelData;
import com.example.demo.viewmodel.repository.MyApi;
import com.example.demo.viewmodel.repository.MyClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyListViewModel extends ViewModel {
    public String id = "";
    public String name = "";
    public String image = "";
    public String email= "";
    public MutableLiveData<ArrayList<MyListViewModel>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<MyListViewModel> arrayList;
    private int pageNo = 1;
    private int pageCount = 0;
    private UserListResponse userListResponse;

    public String getImageurl() {
        return image;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadimage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).apply(RequestOptions.circleCropTransform()).into(imageView);
        //Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    }

    public MyListViewModel() {

    }

    public MyListViewModel(DataItem myList) {
        this.id = String.valueOf(myList.getId());
        this.name = myList.getFirstName();
        this.image = myList.getAvatar();
        this.email = myList.getEmail();
    }

    public MutableLiveData<ArrayList<MyListViewModel>> getMutableLiveData(int pageNo) {

        arrayList = new ArrayList<>();

        MyApi api = MyClient.getInstance().getMyApi();
        Call<UserListResponse> call = api.getartistdata(pageNo);
        call.enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                userListResponse = response.body();
                pageCount=userListResponse.getTotalPages();
                if(pageNo==0){
                    arrayList=new ArrayList<>();

                }

                for (int i = 0; i < userListResponse.getData().size(); i++) {
                    DataItem myk = userListResponse.getData().get(i);

                    MyListViewModel myListViewModel = new MyListViewModel(myk);
                    arrayList.add(myListViewModel);
                    mutableLiveData.setValue(arrayList);
                }
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {

            }
/*
            @Override
            public void onResponse(Call<ArrayList<UserModelData>> call, Response<ArrayList<UserModelData>> response) {

 
            }
 
            @Override
            public void onFailure(Call<ArrayList<UserModelData>> call, Throwable t) {
 
            }*/
        });

        return mutableLiveData;
    }
}