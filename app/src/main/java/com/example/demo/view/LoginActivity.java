package com.example.demo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.databinding.ActivityLoginBinding;

import com.example.demo.viewmodel.AppViewModel;

import static com.example.demo.viewmodel.AppViewModel.START_SOME_ACTIVITY;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityLoginBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityMainBinding.setViewModel(new AppViewModel());
        activityMainBinding.executePendingBindings();
    }
    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, int message) {
        if (message == START_SOME_ACTIVITY) {
            view.getContext().startActivity(new Intent(view.getContext(), MainActivity.class));
        }
       /* if (message != null){
            message==
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();}*/

    }
}