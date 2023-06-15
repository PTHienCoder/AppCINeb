package com.example.app_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Model.ModelPost;
import com.example.app_project.Model.Model_Cate_Post;
import com.example.app_project.Model.Model_My_Order;
import com.example.app_project.Model.Model_My_Order_Store;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class My_Order_Adapter extends RecyclerView.Adapter<My_Order_Adapter.PlaceViewHolder> {

    //    public static int id ;
    private List<Model_My_Order> model_my_orders;
    private Context context;
//

    My_Order_Store_Adapter adapter_item_cate ;
    List<ModelPost> modelPosts;


    public My_Order_Adapter(List<Model_My_Order> model_my_orders, Context context) {
        this.model_my_orders = model_my_orders;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_order,viewGroup,false);
        return new PlaceViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Model_My_Order cateModel = model_my_orders.get(position);
        if (cateModel == null){
            return;
        }

        int id = model_my_orders.get(position).getId_order_user();
        holder.ordercode.setText(model_my_orders.get(position).getOrder_code());
        holder.time.setText(model_my_orders.get(position).getTime_order());

//
        ////////////////// top posst ////////////////////////
        LinearLayoutManager ly = new LinearLayoutManager(context);
        ly.setOrientation(RecyclerView.VERTICAL);
        holder.rcv_my_order.setLayoutManager(ly);

        RetrofitInterface dataService = APIService.getService();
        Call<Model_My_Order_Store> callback = dataService.Get_My_Store_Order(id);
        callback.enqueue(new Callback<Model_My_Order_Store>() {
            @Override
            public void onResponse(Call<Model_My_Order_Store> call, Response<Model_My_Order_Store> response) {
                if (response.isSuccessful()) {
                    List<Model_My_Order_Store> Model_My_Order_Store = response.body().getModel_My_Order_Store();

                    adapter_item_cate = new My_Order_Store_Adapter(Model_My_Order_Store, context);
                    holder.rcv_my_order.setAdapter(adapter_item_cate);
                    adapter_item_cate.notifyDataSetChanged();

                } else {
                    Toast.makeText(context, "that ss" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model_My_Order_Store> call, Throwable throwable) {
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
        return model_my_orders.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        //        private ImageView image_cate;
        private TextView ordercode, xem,time;
        private RecyclerView rcv_my_order;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);

            ordercode =  itemView.findViewById(R.id.tv1);
            time =  itemView.findViewById(R.id.tv2);
            xem =  itemView.findViewById(R.id.tv3);
            rcv_my_order = itemView.findViewById(R.id.rcv_my_order);


        }
    }
}
