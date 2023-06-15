package com.example.app_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_project.Adapter.Adapter_Size_Product;
import com.example.app_project.Adapter.ItemCart_Adapter;
import com.example.app_project.Adapter.TopPost_Adapter;
import com.example.app_project.Model.ModelCart;
import com.example.app_project.Model.ModelPost;
import com.example.app_project.Model.ModelSize_Pro;
import com.example.app_project.Model.Model_Load_Qty_Cart;
import com.example.app_project.Model.Model_TotalPriceCart;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.example.app_project.interface_app.ItemClicklistener_Type;
import com.example.app_project.interface_app.itemOnclickListenner_ItemCart;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart_Activity extends AppCompatActivity {
    LinearLayout ln_noitem, nav_bottom;
    TextView tv_total_cart, tv_price_cart, tv_discount;
    Button btn_checkout;

    itemOnclickListenner_ItemCart itemOnclickListenner_itemCart;


    RecyclerView recyclerView_itemcart;
    ItemCart_Adapter itemCart_adapter ;
    List<ModelCart> modelCarts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ln_noitem = findViewById(R.id.ln_noitem);
        nav_bottom = findViewById(R.id.nav_bottom);

        tv_total_cart = findViewById(R.id.tv_total_cart);
        tv_price_cart = findViewById(R.id.tv_price_cart);
        btn_checkout = findViewById(R.id.btn_checkout);
        tv_discount = findViewById(R.id.tv_discount);
        ImageButton backc = findViewById(R.id.backc);
        backc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart_Activity.this, Checkout_Activity.class);
                intent.putExtra("checkout_type", "cart");
                startActivity(intent);
            }
        });
//        Toast.makeText(Cart_Activity.this, "id_user" + AppLayout.id_user, Toast.LENGTH_SHORT).show();
        ////////////////// new posst ////////////////////////
        LinearLayoutManager layoutManager_top = new LinearLayoutManager(Cart_Activity.this);
        layoutManager_top.setOrientation(RecyclerView.VERTICAL);
        recyclerView_itemcart = findViewById(R.id.rcv_item_cart);
        recyclerView_itemcart.setLayoutManager(layoutManager_top);
        modelCarts = new ArrayList<>();

        load_Iteam_cart();
        load_total();
        itemOnclickListenner_itemCart = new itemOnclickListenner_ItemCart() {
            @Override
            public void Onclickitem_update() {
                load_total();
            }
            @Override
            public void Onclickitem_delete() {
                load_Iteam_cart();
                load_total();
                LoadQty_Cart();
            }
        };
        LoadQty_Cart();
    }
    public void LoadQty_Cart(){
        RetrofitInterface dataService = APIService.getService();
        Call<Model_Load_Qty_Cart> callback = dataService.Get_Qty_Cart(AppLayout.id_user);
        callback.enqueue(new Callback<Model_Load_Qty_Cart>() {
            @Override
            public void onResponse(Call<Model_Load_Qty_Cart> call, Response<Model_Load_Qty_Cart> response) {
                if (response.isSuccessful()){
                    for(Model_Load_Qty_Cart item:response.body().getModel_Load_Qty_Cart()){
                        TextView tv_Qty_cart = findViewById(R.id.tv_Qty_cart);
                        tv_Qty_cart.setText(item.getQty_cart());
                    }
                }
            }

            @Override
            public void onFailure(Call<Model_Load_Qty_Cart> call, Throwable throwable) {
                Toast.makeText(Cart_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void load_Iteam_cart() {
        RetrofitInterface dataService = APIService.getService();
        Call<ModelCart> callback = dataService.LoadProductCart(AppLayout.id_user);
        callback.enqueue(new Callback<ModelCart>() {
            @Override
            public void onResponse(Call<ModelCart> call, Response<ModelCart> response) {
                if (response.code() == 200) {
                    List<ModelCart> cart = response.body().getModelCart();
                    itemCart_adapter = new ItemCart_Adapter(cart, Cart_Activity.this, itemOnclickListenner_itemCart);
                    recyclerView_itemcart.setAdapter(itemCart_adapter);
                    itemCart_adapter.notifyDataSetChanged();

                    ln_noitem.setVisibility(View.GONE);
                    nav_bottom.setVisibility(View.VISIBLE);

                }else{
                    ln_noitem.setVisibility(View.VISIBLE);
                    nav_bottom.setVisibility(View.GONE);


                }
            }

            @Override
            public void onFailure(Call<ModelCart> call, Throwable throwable) {
                Toast.makeText(Cart_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void load_total() {
        RetrofitInterface dataService = APIService.getService();
        Call<Model_TotalPriceCart> callback = dataService.Get_Total_Cart(AppLayout.id_user);
        callback.enqueue(new Callback<Model_TotalPriceCart>() {
            @Override
            public void onResponse(Call<Model_TotalPriceCart> call, Response<Model_TotalPriceCart> response) {
                if (response.isSuccessful()) {
                    for(Model_TotalPriceCart item:response.body().getModel_TotalPriceCart()){
                        if (item.getTotal() == "0"){
                            tv_discount.setVisibility(View.GONE);
                        }
                        tv_total_cart.setText(item.getTotal() + APIService.Donvi);
                        Double price = Integer.parseInt(item.getTotal()) * 0.67;
                        Double abc = (double) Math.round(price * 10) / 10;
                        tv_price_cart.setText("Tổng tiền: "+abc.toString() +" ("+APIService.Donvi+")");
                    }

                }
            }

            @Override
            public void onFailure(Call<Model_TotalPriceCart> call, Throwable throwable) {
                Toast.makeText(Cart_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }
}