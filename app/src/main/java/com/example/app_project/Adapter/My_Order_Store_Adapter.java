package com.example.app_project.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Model.ModelPost;
import com.example.app_project.Model.Model_My_Order_Product;
import com.example.app_project.Model.Model_My_Order_Store;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class My_Order_Store_Adapter extends RecyclerView.Adapter<My_Order_Store_Adapter.PlaceViewHolder> {

    //    public static int id ;
    private List<Model_My_Order_Store> model_my_order_stores;
    private Context context;
//

    My_Order_Product_Adapter adapter_item_cate ;
    List<ModelPost> modelPosts;


    public My_Order_Store_Adapter(List<Model_My_Order_Store> model_my_order_stores, Context context) {
        this.model_my_order_stores = model_my_order_stores;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_order_store,viewGroup,false);
        return new PlaceViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Model_My_Order_Store cateModel = model_my_order_stores.get(position);
        if (cateModel == null){
            return;
        }

        int id = model_my_order_stores.get(position).getId_order_store();

        int id_store = model_my_order_stores.get(position).getId_store();
        int id_order_user = model_my_order_stores.get(position).getId_order_user();
         holder.total.setText("Total: "+model_my_order_stores.get(position).getTotal_order() + APIService.Donvi);
         holder.total.setTextColor(Color.parseColor("#FF8C00"));

        String status = model_my_order_stores.get(position).getOrder_status();
        if(Integer.parseInt(status) == 0){
            holder.status.setText("Processing");
            holder.status.setTextColor(Color.parseColor("#FFD700"));

        }else if(Integer.parseInt(status) == 1){
            holder.status.setText("Packed");
            holder.status.setTextColor(Color.parseColor("#87CEFA"));

        }else if(Integer.parseInt(status) == 2){

            holder.status.setText("Shipped");
            holder.status.setTextColor(Color.parseColor("#0000FF"));

        }else if(Integer.parseInt(status) == 3){
            holder.status.setText("Delivered");
            holder.status.setTextColor(Color.parseColor("#7FFF00"));

        }else if(Integer.parseInt(status) == 4){
            holder.status.setText("Cancelled");
            holder.status.setTextColor(Color.parseColor("#DC143C"));

        }

//
        ////////////////// top posst ////////////////////////
        LinearLayoutManager ly = new LinearLayoutManager(context);
        ly.setOrientation(RecyclerView.HORIZONTAL);

        holder.rcv_my_order_store.setLayoutManager(ly);

//        Toast.makeText(context, "tsd: " +id , Toast.LENGTH_SHORT).show();
        RetrofitInterface dataService = APIService.getService();
        Call<Model_My_Order_Product> callback = dataService.Get_My_Order_Product(id_store, id_order_user);
        callback.enqueue(new Callback<Model_My_Order_Product>() {
            @Override
            public void onResponse(Call<Model_My_Order_Product> call, Response<Model_My_Order_Product> response) {
                if (response.isSuccessful()) {
                    List<Model_My_Order_Product> moss = response.body().getModel_My_Order_Product();

                    adapter_item_cate = new My_Order_Product_Adapter(moss, context);
                    holder.rcv_my_order_store.setAdapter(adapter_item_cate);
                    adapter_item_cate.notifyDataSetChanged();

                } else {
                    Toast.makeText(context, "that ss" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model_My_Order_Product> call, Throwable throwable) {
                Toast.makeText(context, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return model_my_order_stores.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        //        private ImageView image_cate;
        private TextView total, qty_item, status;
        private RecyclerView rcv_my_order_store;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            total =  itemView.findViewById(R.id.tv0);
//            qty_item =  itemView.findViewById(R.id.tv1);

            status =  itemView.findViewById(R.id.tv3);
            rcv_my_order_store = itemView.findViewById(R.id.rcv_my_order_store);


        }
    }
}