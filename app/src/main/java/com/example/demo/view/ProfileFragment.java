package com.example.demo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.demo.R;
import com.example.demo.data.repositry.AppPreferencesHelper;
import com.example.demo.view.adapter.MyDataAdapter;
import com.example.demo.viewmodel.AppViewModel;
import com.example.demo.viewmodel.MyListViewModel;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    AppViewModel appViewModel;
    AppPreferencesHelper appPreferencesHelper;
    public ProfileFragment() {
        // Required empty public constructor
    }
TextView txtEmail;
    Button btnLogout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        txtEmail=view.findViewById(R.id.txtEmail);
        btnLogout=view.findViewById(R.id.btnLogout);
        appPreferencesHelper=new AppPreferencesHelper(getActivity(),"sharedprefrence");
    String email=    appPreferencesHelper.getCurrentUserEmail();
        txtEmail.setText(""+email);



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appPreferencesHelper.setCurrentUserEmail("");
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

}
