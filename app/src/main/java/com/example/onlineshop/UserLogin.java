package com.example.onlineshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import APIRoute.UsersAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class UserLogin extends AppCompatActivity {
EditText user_name,password;
Button btnLogin,btnsignup;
UsersAPI usersAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        user_name=findViewById(R.id.username);
        password=findViewById(R.id.password);
        btnLogin=findViewById(R.id.btnlogin);
        btnsignup=findViewById(R.id.btnSignUp);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         usersAPI = retrofit.create(UsersAPI.class);

         btnsignup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(UserLogin.this,RegisterUser.class);
                 startActivity(intent);
             }
         });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Call<String> checkUSer = usersAPI.checkUSer(user_name.getText().toString(),password.getText().toString());

                checkUSer.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.body().equals("Login successfull")){
                            Toast.makeText(UserLogin.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                           Intent intent=new Intent(UserLogin.this, DashboardActivity.class);
                           startActivity(intent);
                           finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(UserLogin.this, "Login Denied", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });




    }




}
