package com.example.app_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Detail_Post_Activity;
import com.example.app_project.Detail_Products_Activity;
import com.example.app_project.Model.ModelCateProduct;
import com.example.app_project.Model.ModelPost;
import com.example.app_project.Model.ModelProduct;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Product_All_Adapter extends RecyclerView.Adapter<Product_All_Adapter.PlaceViewHolder> {


    private List<ModelProduct> modelProducts;
    private Context context;



    public Product_All_Adapter(Context context, List<ModelProduct> modelProducts ) {
        this.modelProducts = modelProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_all,viewGroup,false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
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


        holder.name_pro.setText(title);
        if(Integer.parseInt(type_product) == 0){
            holder.price_pro.setText(price_pro+APIService.Donvi);

        }else if(Integer.parseInt(type_product) == 1){

            holder.price_pro.setText(price_pro +APIService.Donvi +"-"+ price_pro2 +APIService.Donvi);
        }else if(Integer.parseInt(type_product) == 2){
            holder.price_pro.setText(price_pro +APIService.Donvi +"-"+ price_pro2 +APIService.Donvi);
        }

        holder.qty_sale.setText("Đã bán "+qty_sale);


        try {
            Picasso.get().load(APIService.IPIMAGE_PRODUCT + image_pro).placeholder(R.drawable.ic_img).into(holder.image_pro);
        } catch (Exception e) {

        }
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "id_product: " + id, Toast.LENGTH_SHORT).show();
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
        if (modelProducts != null){
            return modelProducts.size();
        }

        return 0;
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        private CardView card_view;
        private ImageView image_pro;
        private TextView name_pro, price_pro, qty_sale;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);

            card_view =  itemView.findViewById(R.id.card_view);
            image_pro =  itemView.findViewById(R.id.image_pro);
            name_pro =  itemView.findViewById(R.id.textView1);
            price_pro =  itemView.findViewById(R.id.name_pro);
            qty_sale =  itemView.findViewById(R.id.qty_sale);


        }
    }
}
