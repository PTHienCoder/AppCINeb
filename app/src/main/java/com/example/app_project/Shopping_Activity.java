package com.example.app_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_project.Adapter.Adapter_Type_Product;
import com.example.app_project.Adapter.Cate_Pro_Adapter;
import com.example.app_project.Adapter.Post_Adapter;
import com.example.app_project.Adapter.Product_All_Adapter;
import com.example.app_project.Adapter.Product_Sale_Adapter;
import com.example.app_project.Adapter.SliderAdapter;
import com.example.app_project.Adapter.TopPost_Adapter;
import com.example.app_project.Model.ModelCateProduct;
import com.example.app_project.Model.ModelPost;
import com.example.app_project.Model.ModelProduct;
import com.example.app_project.Model.Model_Load_Qty_Cart;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Shopping_Activity extends AppCompatActivity {
    SliderView sliderView;

    int[] images = {R.drawable.bnshop,
            R.drawable.bnshop0,
            R.drawable.bnshop1,
            R.drawable.bnshop2};
    //// Cate products///
    RecyclerView recyclerView_cate_pro;
    Cate_Pro_Adapter cate_pro_adapter ;
    List<ModelCateProduct> modelCateProducts;

    //// producst sale///
    RecyclerView recyclerView_pro_sale;
    Product_Sale_Adapter product_sale_adapter ;
    List<ModelProduct> modelProducts_sale;

    //// producst all///
    RecyclerView recyclerView_pro_all;
    Product_All_Adapter product_all_adapter ;
    List<ModelProduct> modelProducts_all;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LoadQty_Cart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadQty_Cart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        ImageButton search = findViewById(R.id.search_icon);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Shopping_Activity.this, Search_Product.class);
                intent2.putExtra("key", "all");
                startActivity(intent2);
            }
        });

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
                Intent intent = new Intent(Shopping_Activity.this, Cart_Activity.class);
                startActivity(intent);
            }
        });

        TextView xemthem1 = findViewById(R.id.tv_xemthem1);
        xemthem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Shopping_Activity.this, Search_Product.class);
                intent2.putExtra("key", "all");
                startActivity(intent2);
            }
        });

        TextView xemthem2 = findViewById(R.id.tv_xemthem2);
        xemthem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Shopping_Activity.this, Search_Product.class);
                intent2.putExtra("key", "all");
                startActivity(intent2);
            }
        });










        ///// slide/////////////////
        sliderView = findViewById(R.id.image_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();



        /////////////////// Cate products//////////////////
        LinearLayoutManager layoutManager = new LinearLayoutManager(Shopping_Activity.this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView_cate_pro = findViewById(R.id.rcv_cate_product);
        recyclerView_cate_pro.setLayoutManager(layoutManager);
        modelCateProducts = new ArrayList<>();


        /////////////////// producst sale//////////////////
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(Shopping_Activity.this);
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView_pro_sale = findViewById(R.id.product_sale);
        recyclerView_pro_sale.setLayoutManager(layoutManager1);
        modelProducts_sale = new ArrayList<>();

        /////////////////// producst sale//////////////////
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView_pro_all = findViewById(R.id.product_all);
        recyclerView_pro_all.setLayoutManager(layoutManager2);
        modelProducts_all = new ArrayList<>();

        getcate_product();
        get_product_sale();
        get_product_all();
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
                Toast.makeText(Shopping_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getcate_product() {
        RetrofitInterface dataService = APIService.getService();
        Call<ModelCateProduct> callback = dataService.LoadCategory();
        callback.enqueue(new Callback<ModelCateProduct>() {
            @Override
            public void onResponse(Call<ModelCateProduct> call, Response<ModelCateProduct> response) {
                if (response.code() == 200) {
                    List<ModelCateProduct> modelCateProducts = response.body().getModelCateProduct();

                    cate_pro_adapter = new Cate_Pro_Adapter(modelCateProducts, Shopping_Activity.this);
                    recyclerView_cate_pro.setAdapter(cate_pro_adapter);
                    cate_pro_adapter.notifyDataSetChanged();


                } else {

                       Toast.makeText(Shopping_Activity.this, "That bai", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelCateProduct> call, Throwable throwable) {
                Toast.makeText(Shopping_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void get_product_sale() {
        RetrofitInterface dataService = APIService.getService();
        Call<ModelProduct> callback = dataService.LoadProduct_Sale();
        callback.enqueue(new Callback<ModelProduct>() {
            @Override
            public void onResponse(Call<ModelProduct> call, Response<ModelProduct> response) {
                if (response.code() == 200) {
                    List<ModelProduct> modelProducts = response.body().getModelProduct();

                    product_sale_adapter = new Product_Sale_Adapter(modelProducts, Shopping_Activity.this);
                    recyclerView_pro_sale.setAdapter(product_sale_adapter);
                    product_sale_adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<ModelProduct> call, Throwable throwable) {
                Toast.makeText(Shopping_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }

     private void get_product_all() {
        RetrofitInterface dataService = APIService.getService();
        Call<ModelProduct> callback = dataService.LoadProduct_All();
        callback.enqueue(new Callback<ModelProduct>() {
            @Override
            public void onResponse(Call<ModelProduct> call, Response<ModelProduct> response) {
                if (response.code() == 200) {
                    List<ModelProduct> modelProducts = response.body().getModelProduct();

                    product_all_adapter = new Product_All_Adapter(Shopping_Activity.this, modelProducts);
                    recyclerView_pro_all.setAdapter(product_all_adapter);
                    product_all_adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<ModelProduct> call, Throwable throwable) {
                Toast.makeText(Shopping_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }




}