package com.example.app_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Model.ModelCart;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.example.app_project.interface_app.itemOnclickListenner_ItemCart;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemCheckout_Adapter extends RecyclerView.Adapter<ItemCheckout_Adapter.PlaceViewHolder> {


    private List<ModelCart> modelCarts;
    private Context context;



    public ItemCheckout_Adapter(List<ModelCart> modelCarts, Context context) {
        this.modelCarts = modelCarts;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_checkout,viewGroup,false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        ModelCart cateModel = modelCarts.get(position);
        if (cateModel == null){
            return;
        }
        int id_cart = modelCarts.get(position).getId_cart();
        int id_product = modelCarts.get(position).getId_product();
        int type_product = modelCarts.get(position).getType_product();
        int id_type = modelCarts.get(position).getId_type_product();
        int id_size = modelCarts.get(position).getId_size_product();
        String checkCart = modelCarts.get(position).getCheck_item();
        String Qty_item = modelCarts.get(position).getQty_product();



        if (type_product == 0){
            holder.tv_typro.setVisibility(View.GONE);
        }else{
            holder.tv_typro.setVisibility(View.VISIBLE);
        }

        holder.name_pro_item.setText(modelCarts.get(position).getName_item());
        holder.tv_typro.setText(modelCarts.get(position).getType_item());


        try {
            Picasso.get().load(APIService.IPIMAGE_PRODUCT + modelCarts.get(position).getImage_item()).placeholder(R.drawable.ic_img).into(holder.image_item);
        } catch (Exception e) {

        }

        String price_item = modelCarts.get(position).getPrice_item();
        holder.tv_price.setText(Integer.parseInt(Qty_item) + "x" +Integer.parseInt(price_item)+APIService.Donvi);
        holder.tv_qty.setText(Integer.parseInt(Qty_item)*Integer.parseInt(price_item)  +APIService.Donvi);

    }



    @Override
    public int getItemCount() {
        return modelCarts.size();
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
