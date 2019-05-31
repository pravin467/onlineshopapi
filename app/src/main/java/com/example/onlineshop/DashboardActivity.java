package com.example.onlineshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import APIRoute.ItemAPI;
import Model.Items;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class DashboardActivity extends AppCompatActivity {
    private Button btn_add_item;
    ItemAPI itemAPI;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//        Instance();
        getItems();
        recyclerView=findViewById(R.id.recyclerView);
        btn_add_item=findViewById(R.id.btnaddItem);
        btn_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this,saleItems.class);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

    }




    private  void Instance(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        itemAPI = retrofit.create(ItemAPI.class);
    }

    private void getItems(){
        Instance();
        Call <List<Items>> getallItems=itemAPI.getallItems();
        getallItems.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                if(response.isSuccessful()){

                    List <Items> itemsListModel=response.body();
                    Toast.makeText(DashboardActivity.this, ""+itemsListModel, Toast.LENGTH_SHORT).show();
                    recyclerView.setAdapter(new ItemAdopter(getApplicationContext(), itemsListModel));
                }

            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {

                Toast.makeText(DashboardActivity.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
