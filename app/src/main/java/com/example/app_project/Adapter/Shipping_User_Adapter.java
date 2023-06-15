package com.example.app_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Model.Model_Notification;
import com.example.app_project.Model.Model_Shipping;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.example.app_project.interface_app.itemOnclickListenner_ItemNoti;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Shipping_User_Adapter extends RecyclerView.Adapter<Shipping_User_Adapter.Myholder> {
    Context context;
    List<Model_Shipping> model_shippings;
    itemOnclickListenner_ItemNoti itemOnclickListenner_itemNoti;

    public Shipping_User_Adapter(Context context, List<Model_Shipping> model_shippings, itemOnclickListenner_ItemNoti itemOnclickListenner_itemNoti) {
        this.context = context;
        this.model_shippings = model_shippings;
        this.itemOnclickListenner_itemNoti = itemOnclickListenner_itemNoti;

    }

    @NonNull
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shipping_user, parent, false);
        return new Myholder(view);
    }

    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        //get data

        int id = model_shippings.get(position).getId_shipping();
        int id_user = model_shippings.get(position).getId_user();
        String name = model_shippings.get(position).getName_shipping();
        String phone = model_shippings.get(position).getPhone_shipping();
        String des = model_shippings.get(position).getDesc_address();
        String addd = model_shippings.get(position).getAddress_shipping();

        String checkcart = model_shippings.get(position).getCheck_shipping();

        if (Integer.parseInt(checkcart) == 1){
            holder.checkBox.setChecked(true);
        }else if (Integer.parseInt(checkcart) == 0){
            holder.checkBox.setChecked(false);
        }


        holder.tv_nameship.setText(name);
        holder.tv_phone_ship.setText(phone);

        holder.tv_address_ship.setText(des+"," +addd);

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                delete(model_shippings.get(position).getId_shipping());
            }
        });


        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked()){
                    int checkked = 1;
                    checked_iteamcart(id,checkked);
                    holder.checkBox.setChecked(true);
                }else{
                    int checkked = 0;
                    checked_iteamcart(id,checkked);
                    holder.checkBox.setChecked(false);
                }
            }
        });

    }

    public void checked_iteamcart(int id_cart, int checked){
        RetrofitInterface dataService = APIService.getService();
        Call<Void> callback = dataService.Update_Check_Shipping(id_cart, checked);
        callback.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
//                    itemOnclickListenner_itemCart.Onclickitem_update();
//                  Toast.makeText(context,  checked+"/"+id_cart , Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
            }
        });
    }

    private void delete(int time) {

        RetrofitInterface dataService = APIService.getService();
        Call<Void> callback = dataService.Clear_Shipping(time);
        callback.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Delete Item Success", Toast.LENGTH_SHORT).show();
                    itemOnclickListenner_itemNoti.Onclickitem_delete();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
            }
        });

    }

    public int getItemCount() {
        return model_shippings.size();
    }

    class Myholder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        ImageView btn_delete;
        TextView tv_nameship, tv_phone_ship, tv_address_ship;
        RelativeLayout ul;

        public Myholder(@NonNull View itemView) {
            super(itemView);
            tv_nameship = itemView.findViewById(R.id.tv_nameship);
            tv_phone_ship = itemView.findViewById(R.id.tv_phone_ship);
            tv_address_ship = itemView.findViewById(R.id.tv_address_ship);

            btn_delete = itemView.findViewById(R.id.btn_delete);
            checkBox =  itemView.findViewById(R.id.checkBoxxx);


        }
    }
}