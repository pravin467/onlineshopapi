package com.example.onlineshop;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import Model.Items;
import de.hdodenhof.circleimageview.CircleImageView;

public class ItemAdopter extends RecyclerView.Adapter<ItemAdopter.ItemViewHolder> {
   private Context mcontext;
   private List<Items> itemsList;
   private String BASE_URL="http://10.0.2.2:8090/";

    public ItemAdopter(Context mcontext, List<Items> itemsList) {
        this.mcontext = mcontext;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {

       final Items items = itemsList.get(i);

        itemViewHolder.item_name.setText(items.getItemname());
        itemViewHolder.item_price.setText(items.getItemprice()+"");
//        System.out.println(items.getItemimagename());


        Picasso.with(mcontext).load(BASE_URL+"images/"+items.getItemimagename()).into(itemViewHolder.imgProfile);

        itemViewHolder.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, Description.class);


                intent.putExtra("image", BASE_URL+"images/"+items.getItemimagename());
                intent.putExtra("name", items.getItemname()+"");
                intent.putExtra("description", items.getItemdescription()+"");
                intent.putExtra("price", items.getItemprice()+"");

                System.out.println(""+items.getItemimagename());
                System.out.println(""+items.getItemdescription());
                System.out.println(""+items.getItemname());
                System.out.println(""+items.getItemprice());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgProfile;
        TextView item_name,item_price;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.img);
            item_name=itemView.findViewById(R.id.tvName);
            item_price=itemView.findViewById(R.id.tvPrice);

        }
    }
}
