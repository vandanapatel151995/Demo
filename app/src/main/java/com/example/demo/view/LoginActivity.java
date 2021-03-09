package com.example.demo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.data.repositry.AppPreferencesHelper;
import com.example.demo.databinding.ActivityLoginBinding;

import com.example.demo.viewmodel.AppViewModel;

import static com.example.demo.viewmodel.AppViewModel.START_SOME_ACTIVITY;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityLoginBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityMainBinding.setViewModel(new AppViewModel(this));
        activityMainBinding.executePendingBindings();
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_LONG).show();
    }

    @BindingAdapter({"showMessage"})
    public static void runMe(View view, int messageId) {
        if (messageId == START_SOME_ACTIVITY) {
            view.getContext().startActivity(new Intent(view.getContext(), MainActivity2.class));
        }
    }
}