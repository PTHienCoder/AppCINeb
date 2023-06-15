package com.example.app_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.app_project.Adapter.Product_All_Adapter;
import com.example.app_project.Model.ModelProduct;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_Product extends AppCompatActivity {
    //// producst all///
    RecyclerView recyclerView_pro_all;
    Product_All_Adapter product_all_adapter ;
    List<ModelProduct> modelProducts_all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);


        ImageButton backc = findViewById(R.id.backc);
        backc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /////////////////// producst sale//////////////////
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView_pro_all = findViewById(R.id.rcv_Search_Product);
        recyclerView_pro_all.setLayoutManager(layoutManager2);

        SearchView sv = (SearchView) findViewById(R.id.svpro);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query.trim())){
                    get_product_all(query);
                }else{
                    get_product_all("all");
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                if (!TextUtils.isEmpty(query.trim())){
                    get_product_all(query);
                }else{

                    get_product_all("all");
                }
                return false;
            }
        });
        Intent intent = getIntent();
        String key = ""+intent.getStringExtra("key");

//        Toast.makeText(Search_Product.this, ": " + key, Toast.LENGTH_SHORT).show();

        if(!key.equals("all")){
            get_product_cate(key);
        }else{
            get_product_all("all");
        }

    }

    private void get_product_cate(String key) {
        RetrofitInterface dataService = APIService.getService();
        Call<ModelProduct> callback = dataService.Get_Product_category(key);
        callback.enqueue(new Callback<ModelProduct>() {
            @Override
            public void onResponse(Call<ModelProduct> call, Response<ModelProduct> response) {
                if (response.code() == 200) {
                    List<ModelProduct> modelProducts = response.body().getModelProduct();

                    product_all_adapter = new Product_All_Adapter(Search_Product.this, modelProducts);
                    recyclerView_pro_all.setAdapter(product_all_adapter);
                    product_all_adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<ModelProduct> call, Throwable throwable) {
                Toast.makeText(Search_Product.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void get_product_all(String query) {
        RetrofitInterface dataService = APIService.getService();
        Call<ModelProduct> callback = dataService.Search_Product(query);
        callback.enqueue(new Callback<ModelProduct>() {
            @Override
            public void onResponse(Call<ModelProduct> call, Response<ModelProduct> response) {
                if (response.code() == 200) {
                    List<ModelProduct> modelProducts = response.body().getModelProduct();

                    product_all_adapter = new Product_All_Adapter(Search_Product.this, modelProducts);
                    recyclerView_pro_all.setAdapter(product_all_adapter);
                    product_all_adapter.notifyDataSetChanged();
                    
                }
            }

            @Override
            public void onFailure(Call<ModelProduct> call, Throwable throwable) {
                Toast.makeText(Search_Product.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }
}