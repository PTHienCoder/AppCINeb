package com.example.app_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_project.Model.Model_Load_Qty_Cart;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Store_and_Product extends AppCompatActivity {
    RetrofitInterface dataService = APIService.getService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_and_product);
        ImageButton backc = findViewById(R.id.backc);
        backc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ImageButton btn_cart = findViewById(R.id.btn_cart);
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Store_and_Product.this, Cart_Activity.class);
                startActivity(intent);
            }
        });
//
//        LoadQty_Cart();
    }

//    public void LoadQty_Cart(){
//        Call<Model_Load_Qty_Cart> callback = dataService.Get_Qty_Cart(AppLayout.id_user);
//        callback.enqueue(new Callback<Model_Load_Qty_Cart>() {
//            @Override
//            public void onResponse(Call<Model_Load_Qty_Cart> call, Response<Model_Load_Qty_Cart> response) {
//                if (response.isSuccessful()){
//                    for(Model_Load_Qty_Cart item:response.body().getModel_Load_Qty_Cart()){
//                        TextView tv_Qty_cart = findViewById(R.id.tv_Qty_cart);
//                        tv_Qty_cart.setText(item.getQty_cart());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Model_Load_Qty_Cart> call, Throwable throwable) {
//                Toast.makeText(Store_and_Product.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}