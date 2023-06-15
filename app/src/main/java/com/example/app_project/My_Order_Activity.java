package com.example.app_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.app_project.Adapter.My_Order_Adapter;
import com.example.app_project.Adapter.Review_Product_Adapter;
import com.example.app_project.Model.ModelReView_Pro;
import com.example.app_project.Model.Model_My_Order;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class My_Order_Activity extends AppCompatActivity {
    ////////// rcv rview product ////////////
    RecyclerView recyclerView_Rview;
    My_Order_Adapter my_order_adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        ImageButton backc = findViewById(R.id.backc);
        backc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Load_my_Order();

    }

    public void Load_my_Order(){
        ////////////////// rview ////////////////////////
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(My_Order_Activity.this);
        layoutManager3.setOrientation(RecyclerView.VERTICAL);
        recyclerView_Rview = findViewById(R.id.rcv_myorder);
        recyclerView_Rview.setLayoutManager(layoutManager3);

        RetrofitInterface dataService = APIService.getService();
        Call<Model_My_Order> callback = dataService.Get_My_Order(AppLayout.id_user);
        callback.enqueue(new Callback<Model_My_Order>() {
            @Override
            public void onResponse(Call<Model_My_Order> call, Response<Model_My_Order> response) {
                if (response.isSuccessful()){
                    List<Model_My_Order> modelSize = response.body().getModel_My_Order();
                    my_order_adapter = new My_Order_Adapter(modelSize, My_Order_Activity.this);
                    recyclerView_Rview.setAdapter(my_order_adapter);
                    my_order_adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<Model_My_Order> call, Throwable throwable) {
                Toast.makeText(My_Order_Activity.this, "failll" + throwable, Toast.LENGTH_SHORT).show();
            }
        });

    }
}