package com.example.app_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_project.Adapter.Review_Product_Adapter;
import com.example.app_project.Model.ModelReView_Pro;
import com.example.app_project.Model.Model_Load_PriceChoose;
import com.example.app_project.Model.Model_Qty_rv;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Review_Product extends AppCompatActivity {
    TextView tv_sosaox,tv_allsao,tv_5sao,tv_4sao,tv_3sao,tv_2sao,tv_1sao;
    ImageButton btn_add_rv;
    LinearLayout bottom1;
    String id_product;
    String sosao_choose ="";
    View view1;
    ////////// rcv rview product ////////////
    RecyclerView recyclerView_Rview;
    Review_Product_Adapter review_product_adapter ;
    List<ModelReView_Pro> modelReView_pros;


    RadioButton RadioButton5,RadioButton4, RadioButton3, RadioButton2,RadioButton1;
    EditText ed_content;
    TextView btn_submit_rv;

    @Override
    protected void onResume() {
        super.onResume();
        LoadReview_product(Integer.parseInt(id_product));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_product);
        ImageButton backc = findViewById(R.id.backc);
        backc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        AnhXa();

        bottom1 = findViewById(R.id.bottom_sheet_details);

        Intent intent = getIntent();
        id_product  = ""+intent.getStringExtra("id_product");

        LoadReview_product(Integer.parseInt(id_product));
        Load_QtyReview_product(Integer.parseInt(id_product));

        btn_add_rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Review_Product.this, R.style.BottomsheetTheme);
                view1 = LayoutInflater.from(Review_Product.this).inflate(R.layout.bottom_review_product, bottom1);
                bottomSheetDialog.setContentView(view1);

                RadioButton5= view1.findViewById(R.id.RadioButton5);RadioButton4= view1.findViewById(R.id.RadioButton4);
                RadioButton3= view1.findViewById(R.id.RadioButton3);RadioButton2= view1.findViewById(R.id.RadioButton2);
                RadioButton1= view1.findViewById(R.id.RadioButton1);
                RadioGroup radioGroup = view1.findViewById(R.id.RadioGroup);

                ed_content= view1.findViewById(R.id.ed_content);
                btn_submit_rv= view1.findViewById(R.id.btn_submit_rv);


                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);

                    // Gắn sự kiện OnClickListener cho mỗi RadioButton
                    radioButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String text = ((RadioButton)view).getText().toString();
                            sosao_choose =  ((RadioButton)view).getText().toString();
                        }
                    });
                }




                btn_submit_rv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String abc = ed_content.getText().toString();
                        if (sosao_choose == ""){
                            Toast.makeText(Review_Product.this, "Please Choose Review Start", Toast.LENGTH_SHORT).show();
                        }else if (abc == ""){
                            Toast.makeText(Review_Product.this, "Please Enter Review Product", Toast.LENGTH_SHORT).show();
                        }else{
                          PostReviewPro(abc, sosao_choose);
                          bottomSheetDialog.dismiss();
                            ed_content.setText("");
                            sosao_choose ="";
                        }

                    }

                });

                bottomSheetDialog.show();
            }
        });

    }

    private void PostReviewPro(String abc, String sosao_choose) {
        RetrofitInterface dataService = APIService.getService();
        Call<Void> callback = dataService.PostCommentProduct(AppLayout.id_user, id_product, sosao_choose, abc);
        callback.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(Review_Product.this, "Post Review Product Success", Toast.LENGTH_SHORT).show();
                    LoadReview_product(Integer.parseInt(id_product));
                    Load_QtyReview_product(Integer.parseInt(id_product));

                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Toast.makeText(Review_Product.this, "failll" + throwable, Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void LoadReview_product(int id_product){
        ////////////////// rview ////////////////////////
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(Review_Product.this);
        layoutManager3.setOrientation(RecyclerView.VERTICAL);
        recyclerView_Rview = findViewById(R.id.rcv_review_pro);
        recyclerView_Rview.setLayoutManager(layoutManager3);

        RetrofitInterface dataService = APIService.getService();
        Call<ModelReView_Pro> callback = dataService.LoadRview_Product(id_product, 0);
        callback.enqueue(new Callback<ModelReView_Pro>() {
            @Override
            public void onResponse(Call<ModelReView_Pro> call, Response<ModelReView_Pro> response) {
                if (response.isSuccessful()){
                    List<ModelReView_Pro> modelSize = response.body().getModelReView_Pro();
                    review_product_adapter = new Review_Product_Adapter(modelSize, Review_Product.this);
                    recyclerView_Rview.setAdapter(review_product_adapter);
                    review_product_adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ModelReView_Pro> call, Throwable throwable) {
                Toast.makeText(Review_Product.this, "failll" + throwable, Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void Load_QtyReview_product(int id_product){
        RetrofitInterface dataService = APIService.getService();
        Call<Model_Qty_rv> callback = dataService.Load_Qty_Review(id_product);
        callback.enqueue(new Callback<Model_Qty_rv>() {
            @Override
            public void onResponse(Call<Model_Qty_rv> call, Response<Model_Qty_rv> response) {
                if (response.isSuccessful()){
                    for(Model_Qty_rv item:response.body().getModel_Qty_rv()){
                        tv_sosaox.setText(item.getAvg_rv() + "/5");
                    }
                }

            }

            @Override
            public void onFailure(Call<Model_Qty_rv> call, Throwable throwable) {
                Toast.makeText(Review_Product.this, "failll" + throwable, Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void AnhXa() {
        tv_sosaox = findViewById(R.id.tv_sosaox);
        tv_allsao = findViewById(R.id.tv_allsao);
        tv_5sao = findViewById(R.id.tv_5sao);
        tv_4sao = findViewById(R.id.tv_4sao);
        tv_3sao = findViewById(R.id.tv_3sao);
        tv_2sao = findViewById(R.id.tv_2sao);
        tv_1sao = findViewById(R.id.tv_1sao);
        btn_add_rv = findViewById(R.id.btn_add_rv);

    }
}