package com.example.onlineshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Description extends AppCompatActivity {
    private CircleImageView circleImage;
    TextView tvItemName, tvItemPrice, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        tvItemName = findViewById(R.id.tvItemName);
        tvItemPrice = findViewById(R.id.tvItemPrice);
        tvDescription = findViewById(R.id.tvDescription);
        circleImage = findViewById(R.id.circleImage);

        Bundle bundle = getIntent().getExtras();
       // Toast.makeText(Description.this, ""+, Toast.LENGTH_LONG).show();

        if (bundle!=null){
//            Log.e("dt",bundle.getString("name"));
            String image= bundle.getString("image");
            Picasso.with(this).load(image).into(circleImage);
            tvItemName.setText(bundle.getString("name"));
            tvItemPrice.setText(bundle.getString("price"));
            tvDescription.setText(bundle.getString("description"));
        }

    }
}

