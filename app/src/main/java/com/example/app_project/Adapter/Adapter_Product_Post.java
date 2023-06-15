package com.example.app_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Detail_Products_Activity;
import com.example.app_project.Model.ModelCart;
import com.example.app_project.Model.ModelProduct;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Product_Post extends RecyclerView.Adapter<Adapter_Product_Post.PlaceViewHolder> {


    private List<ModelProduct> modelProducts;
    private Context context;


    public Adapter_Product_Post(List<ModelProduct> modelProducts, Context context) {
        this.modelProducts = modelProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_post, viewGroup, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        ModelProduct cateModel = modelProducts.get(position);
        if (cateModel == null) {
            return;
        }
        ModelProduct Product = modelProducts.get(position);
        if (Product == null){
            return;
        }
        int id = modelProducts.get(position).getId_product();
        String type_product = modelProducts.get(position).getType_product();
        String title = modelProducts.get(position).getName_product();
        String image_pro = modelProducts.get(position).getImage_product();
        String price_pro = modelProducts.get(position).getPrice_product();
        String price_pro2= modelProducts.get(position).getPrice_pro2();
        String qty_sale = modelProducts.get(position).getQty_sale();

        holder.name_pro_item.setText(modelProducts.get(position).getName_product());

        try {
            Picasso.get().load(APIService.IPIMAGE_PRODUCT + modelProducts.get(position).getImage_product()).placeholder(R.drawable.ic_img).into(holder.image_item);
        } catch (Exception e) {

        }
        if(Integer.parseInt(type_product) == 0){
            holder.tv_price.setText(price_pro+APIService.Donvi);

        }else if(Integer.parseInt(type_product) == 1){

            holder.tv_price.setText(price_pro +APIService.Donvi +"-"+ price_pro2 +APIService.Donvi);
        }else if(Integer.parseInt(type_product) == 2){
            holder.tv_price.setText(price_pro +APIService.Donvi +"-"+ price_pro2 +APIService.Donvi);
        }

        holder.itemProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail_Products_Activity.class);
                intent.putExtra("id_product", String.valueOf(id));
                intent.putExtra("type_product", type_product);
                intent.putExtra("name_product", title);
                intent.putExtra("Image_product", image_pro);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelProducts.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout itemProduct;
        private ImageView image_item;
        private TextView name_pro_item, tv_price;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            image_item = itemView.findViewById(R.id.image_itemxx);
            name_pro_item = itemView.findViewById(R.id.name_pro_item);
            tv_price = itemView.findViewById(R.id.tv_price);
            itemProduct = itemView.findViewById(R.id.itemProduct);


        }
    }
}