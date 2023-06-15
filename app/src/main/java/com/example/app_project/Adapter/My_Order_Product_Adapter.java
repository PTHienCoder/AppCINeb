package com.example.app_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Model.ModelCart;
import com.example.app_project.Model.Model_My_Order_Product;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class My_Order_Product_Adapter extends RecyclerView.Adapter<My_Order_Product_Adapter.PlaceViewHolder> {


    private List<Model_My_Order_Product> model_my_order_products;
    private Context context;



    public My_Order_Product_Adapter(List<Model_My_Order_Product> model_my_order_products, Context context) {
        this.model_my_order_products = model_my_order_products;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_product_order,viewGroup,false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Model_My_Order_Product cateModel = model_my_order_products.get(position);
        if (cateModel == null){
            return;
        }
//        int id_item = model_my_order_products.get(position).get();
        int id_product = model_my_order_products.get(position).getId_product();
        String type_product = model_my_order_products.get(position).getType_pro();
        String name_type = model_my_order_products.get(position).getName_type();
        String nameSize = model_my_order_products.get(position).getName_size();
        String Qty_item = model_my_order_products.get(position).getQty_product();



        if (Integer.parseInt(type_product)  == 0){
            holder.tv_typro.setVisibility(View.GONE);
        }else if (Integer.parseInt(type_product)  == 1){

            holder.tv_typro.setVisibility(View.VISIBLE);
            holder.tv_typro.setText(name_type);

        }else if (Integer.parseInt(type_product)  == 2){

            holder.tv_typro.setVisibility(View.VISIBLE);
            holder.tv_typro.setText(name_type+ "," +nameSize);
        }

        holder.name_pro_item.setText(model_my_order_products.get(position).getName_product());


        try {
            Picasso.get().load(APIService.IPIMAGE_PRODUCT + model_my_order_products.get(position).getImage_product()).placeholder(R.drawable.ic_img).into(holder.image_item);
        } catch (Exception e) {

        }

        String price_item = model_my_order_products.get(position).getPrice_items();
        holder.tv_price.setText(Integer.parseInt(Qty_item) + "x" +Integer.parseInt(price_item)+APIService.Donvi);
        holder.tv_qty.setText(Integer.parseInt(Qty_item)*Integer.parseInt(price_item)  +APIService.Donvi);

    }



    @Override
    public int getItemCount() {
        return model_my_order_products.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_item;
        private TextView name_pro_item,tv_typro, tv_price, tv_qty;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);

            image_item =  itemView.findViewById(R.id.image_item);
            name_pro_item =  itemView.findViewById(R.id.name_pro_item);
            tv_typro =  itemView.findViewById(R.id.tv_typro);
            tv_price =  itemView.findViewById(R.id.tv_price);
            tv_qty =  itemView.findViewById(R.id.tv_qty);


        }
    }
}
