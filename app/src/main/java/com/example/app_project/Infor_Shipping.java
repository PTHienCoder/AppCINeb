package com.example.app_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.app_project.Adapter.Adapter_Notification;
import com.example.app_project.Adapter.Shipping_User_Adapter;
import com.example.app_project.Model.Model_Notification;
import com.example.app_project.Model.Model_Shipping;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.example.app_project.interface_app.itemOnclickListenner_ItemNoti;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Infor_Shipping extends AppCompatActivity {


    RecyclerView recyclerViewn;
    Shipping_User_Adapter shipping_user_adapter;

    itemOnclickListenner_ItemNoti itemOnclickListenner_itemNoti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_shipping);

        ImageButton backc = findViewById(R.id.backc);
        backc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ////////////////// new shipping////////////////////////
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(Infor_Shipping.this);
        layoutManager1.setOrientation(RecyclerView.VERTICAL);
        recyclerViewn = (RecyclerView) findViewById(R.id.rcv_shipping);
        recyclerViewn.setLayoutManager(layoutManager1);

        get_shipping();

        itemOnclickListenner_itemNoti = new itemOnclickListenner_ItemNoti() {
            @Override
            public void Onclickitem_delete() {
                get_shipping();
            }
        };
    }

    private void get_shipping() {
        RetrofitInterface dataService = APIService.getService();
        Call<Model_Shipping> callback = dataService.Load_Shipping(AppLayout.id_user);
        callback.enqueue(new Callback<Model_Shipping>() {
            @Override
            public void onResponse(Call<Model_Shipping> call, Response<Model_Shipping> response) {
                if(response.isSuccessful()) {
                    List<Model_Shipping>  UserModelListss =  response.body().getModel_Shipping();
                    shipping_user_adapter = new Shipping_User_Adapter(Infor_Shipping.this, UserModelListss, itemOnclickListenner_itemNoti);
                    recyclerViewn.setAdapter(shipping_user_adapter);
                    shipping_user_adapter.notifyDataSetChanged();

//                    Toast.makeText(getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();

                } else {

//                    Toast.makeText(getContext(), "That bai", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model_Shipping> call, Throwable t) {
                ///   Toast.makeText(getContext(), "Rest api faill"+t, Toast.LENGTH_SHORT).show();
            }
        });

    }
}