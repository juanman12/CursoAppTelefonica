package com.example.jmcruzya.styleapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.jmcruzya.styleapplication.utilities.MyPreference;

public class LoginActivity extends AppCompatActivity {

    EditText txtUserName;
    private MyPreference myPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUserName = (EditText)findViewById(R.id.editTextUsername);
        myPreference = new MyPreference(this);
        if(myPreference.isLogin()){
            startMainActivity();
        }

    }

    public void login(View view){
        String userName = txtUserName.getText().toString();
        Log.e("Error","Valor String: "+userName);
        if(userName.isEmpty()){
            txtUserName.setError("Campo Username obligatorio");
            return;
        }
        myPreference.setUserName(userName);
        myPreference.setStateLogin(true);
        startMainActivity();
    }

    private void startMainActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
