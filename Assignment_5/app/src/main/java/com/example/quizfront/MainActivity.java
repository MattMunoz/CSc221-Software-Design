package com.example.quizfront;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizfront.databinding.MainActivityLayoutBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MainActivityLayoutBinding mainActivityLayoutBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityLayoutBinding = MainActivityLayoutBinding.inflate(getLayoutInflater());
        setContentView(mainActivityLayoutBinding.getRoot());

        mainActivityLayoutBinding.button.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View v) {
        String username = mainActivityLayoutBinding.username.getEditText().toString();
        String password = mainActivityLayoutBinding.password.getEditText().toString();


    }

    private boolean validate(String username, String password){
        if(username.isEmpty()){
            mainActivityLayoutBinding.username.setError("Username cannot be empty");
            return false;
        }else if(password.isEmpty()){
            mainActivityLayoutBinding.password.setError("Password cannot be empty!");
            return false;
        }

        if (password.length() < 5){
            mainActivityLayoutBinding.password.setError("Password should be more than 5 characters!");
            return false;
        }

        return true;
    }
}
