package com.example.onlineshop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import APIRoute.ItemAPI;
import Model.Items;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class saleItems extends AppCompatActivity {
  EditText item_name,item_price,item_image_name,item_description;
  Button btn_image_upload,btn_add_item;
  ImageView selectimage;
    ItemAPI itemAPI;
//    ImageButton selectimage;

  Uri imagePath;
  Bitmap bitmap;
  private static final int PICK_IMAGE=1;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_items);
        item_name=findViewById(R.id.itemName);
        item_price=findViewById(R.id.itemPrice);
        item_image_name=findViewById(R.id.itemImageName);
        item_description=findViewById(R.id.itemDescription);
        selectimage=findViewById(R.id.selectImage);
        btn_image_upload=findViewById(R.id.btnimageUpload);
        btn_add_item=findViewById(R.id.btnAddItem);


        btn_image_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage(bitmap);

            }


        });


        btn_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveItem();
                Intent intent=new Intent(saleItems.this,DashboardActivity.class);
                startActivity(intent);

            }
        });

      selectimage.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
          openGallery();
          }
      });
    }

private void openGallery(){
    Intent gallery=new Intent();
    gallery.setType("image/*");
    gallery.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(gallery,"Choose Image"),PICK_IMAGE);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK){
            imagePath=data.getData();
            try{
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                selectimage.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    private void UploadImage(Bitmap bitmap) {
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, stream);
        byte[] bytes=stream.toByteArray();
        try{
            File file=new File(this.getCacheDir(),"image.jpeg");
            file.createNewFile();
            FileOutputStream fos= new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
            RequestBody rb= RequestBody.create(MediaType.parse("multipart/form-data"),file);
            MultipartBody.Part body=MultipartBody.Part.createFormData("ImageUpload",file.getName(),rb);

            itemAPI= Url.getInstance().create(ItemAPI.class);
            Call<String> stringCall= itemAPI.uploadImage(body);
            stringCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(saleItems.this,response.body(), Toast.LENGTH_SHORT).show();
                   item_image_name.setText(response.body().toString());


                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(saleItems.this,t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }


    private void SaveItem() {
//        String name=addname.getText().toString();
//        String desc=adddesc.getText().toString();
//        ItemModel itemModel= new ItemModel(name,desc);
        String name = item_name.getText().toString();
        String price = item_price.getText().toString();
        String desc = item_description.getText().toString();
        String imagename=item_image_name.getText().toString();
        Items itemsModel = new Items(name,imagename,desc,Float.parseFloat(price));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         itemAPI = retrofit.create(ItemAPI.class);
        Call<Void> itemsCall = itemAPI.additem(itemsModel);
        itemsCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(saleItems.this,"Code" +response.code(),Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(saleItems.this," Product Added Successfully ",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(saleItems.this,"Error" +t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }

        });
    }
}



