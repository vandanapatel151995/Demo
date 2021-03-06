package com.example.demo.viewmodel;

import android.graphics.ColorSpace;
import android.text.TextUtils;
import android.util.Patterns; 
import androidx.databinding.BaseObservable; 
import androidx.databinding.Bindable;

import com.example.demo.BR;
import com.example.demo.model.UserModel;

public class AppViewModel extends BaseObservable { 

	// creating object of Model class 
	private UserModel model;

	// string variables for 
	// toast messages 
	private String successMessage = "Login successful"; 
	private String errorMessage = "Email or Password is not valid";
	public static final int START_SOME_ACTIVITY = 123;
	@Bindable
	// string variable for 
	// toast message 
	private String toastMessage = null;


	@Bindable
	private int messageId;


	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int message) {
		this.messageId = messageId;
	}


	// getter and setter methods 
	// for toast message 
	public String getToastMessage() { 
		return toastMessage; 
	} 

	private void setToastMessage(String toastMessage) {
		this.toastMessage = toastMessage; 
		notifyPropertyChanged(BR.toastMessage);
	} 

	// getter and setter methods 
	// for email varibale 
	@Bindable
	public String getUserEmail() { 
		return model.getEmail(); 
	} 

	public void setUserEmail(String email) { 
		model.setEmail(email); 
		notifyPropertyChanged(BR.userEmail); 
	} 

	// getter and setter methods 
	// for password variable 
	@Bindable
	public String getUserPassword() { 
		return model.getPassword(); 
	} 

	public void setUserPassword(String password) { 
		model.setPassword(password); 
		notifyPropertyChanged(BR.userPassword); 
	} 

	// constructor of ViewModel class 
	public AppViewModel() { 

		// instantiating object of 
		// model class 
		model = new UserModel("","");
	} 

	// actions to be performed 
	// when user clicks 
	// the LOGIN button 
	public void onButtonClicked() { 
		if (isValid()) {
			//setToastMessage(successMessage);
			messageId = START_SOME_ACTIVITY;
			notifyPropertyChanged(BR.messageId);
		}

	} 

	// method to keep a check 
	// that variable fields must 
	// not be kept empty by user 
	public boolean isValid() { 
		return !TextUtils.isEmpty(getUserEmail()) && Patterns.EMAIL_ADDRESS.matcher(getUserEmail()).matches() 
				&& getUserPassword().length() > 5; 
	} 
}
