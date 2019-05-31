package com.example.onlineshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import APIRoute.UsersAPI;
import Model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class RegisterUser extends AppCompatActivity {
private EditText first_name,last_name,user_name,password;
private Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        first_name=findViewById(R.id.Fname);
        last_name=findViewById(R.id.Lname);
        user_name=findViewById(R.id.Uname);
        password=findViewById(R.id.Password);
        btn_register=findViewById(R.id.btnRegister);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                Intent intent=new Intent(RegisterUser.this,UserLogin.class);
                startActivity(intent);
//                finish();
            }
        });
    }

    private void registerUser()
    {
        String firstName= first_name.getText().toString();
        String lastName = last_name.getText().toString();
        String userName= user_name.getText().toString();
        String Password = password.getText().toString();

        Users users = new Users(firstName,lastName,userName,Password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsersAPI usersAPI = retrofit.create(UsersAPI.class);
        Call<Void> heroesCall = usersAPI.addUsers(users);
        heroesCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(RegisterUser.this,"Code" +response.code(),Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(RegisterUser.this,"Regristration Success",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegisterUser.this,"Error" +t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
}
