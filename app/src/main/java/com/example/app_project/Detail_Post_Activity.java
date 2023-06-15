package com.example.app_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.app_project.Adapter.Adapter_Comment;
import com.example.app_project.Adapter.Adapter_Product_Post;
import com.example.app_project.Adapter.ItemCart_Adapter;
import com.example.app_project.Model.ModelCart;
import com.example.app_project.Model.ModelPost;
import com.example.app_project.Model.ModelProduct;
import com.example.app_project.Model.ModelUser;
import com.example.app_project.Model.Model_Comment;
import com.example.app_project.Model.Model_Qty_Pro_Post;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Post_Activity extends AppCompatActivity {

    TextView nameuser, title, time, desc, content, ooop,ooop1, seenCmt, xempro;
    ImageView avatar, imageView;
    RelativeLayout rslu;
    String id, id_of_user, idpost;
    RelativeLayout viewGroup, viewGroup1;
    ImageView myavta_cmt;
    BottomSheetDialog bottomSheetDialog, bottomSheetDialog1;
    View sheetView, sheetView1;
    EditText editDl;
    WebView Webview;
    String Id_user_Post;

//
    RecyclerView recyclerViewCmT;
    Adapter_Comment Adapter_Comment;
    List<Model_Comment> model_comments;


    RecyclerView recyclerView_pro_post;
    Adapter_Product_Post adapter_product_post ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        nameuser = findViewById(R.id.nametvdb);
        time = findViewById(R.id.emailtvdb);

        title = findViewById(R.id.title);
        desc = findViewById(R.id.mota);
        content = findViewById(R.id.content);
        avatar = findViewById(R.id.avatar_db);
        imageView = findViewById(R.id.coverdb);
        ooop = findViewById(R.id.ooop);
        ooop1 = findViewById(R.id.ooop1);
        Webview = findViewById(R.id.Webview);
        xempro = findViewById(R.id.xempro);
        Intent intent = getIntent();
        id = ""+intent.getStringExtra("id");
        String Image_post = ""+intent.getStringExtra("Image_post");
        String Title_post = ""+intent.getStringExtra("Title_post");
        String Desc_post = ""+intent.getStringExtra("Desc_post");
        String Detail_post = ""+intent.getStringExtra("Detail_post");
        String Time_create = ""+intent.getStringExtra("Time_create");
        Id_user_Post = ""+intent.getStringExtra("Id_user_Post");
        String name_user = ""+intent.getStringExtra("name_user");
        String Qty_pro_post = ""+intent.getStringExtra("Qty_pro_post");

        if(AppLayout.id_user == Integer.valueOf(Id_user_Post)){
            ooop.setVisibility(View.GONE);
            ooop1.setVisibility(View.GONE);
        }

        if(Integer.parseInt(Qty_pro_post)  == 0){
            xempro.setVisibility(View.GONE);
        }else{
            xempro.setText("Cart (" +Qty_pro_post+")");
        }



        String image_user = ""+intent.getStringExtra("image_user");
        checkusserfollow(Integer.valueOf(Id_user_Post));
        try {
            Picasso.get().load(APIService.IPIMAGE_POST + Image_post).placeholder(R.drawable.ic_img).into(imageView);
        } catch (Exception e) {

        }
        try {
            Picasso.get().load(APIService.IPIMAGE_USER+Id_user_Post +"/"+ image_user).placeholder(R.drawable.ic_img).into(avatar);
        } catch (Exception e) {

        }
        nameuser.setText(name_user);
        title.setText(Title_post);
        time.setText(Time_create);
         desc.setText(Desc_post);
         String html = "<html><body>"+Detail_post+"</body></html>";
         Webview.loadData(html, "text/html", "UTF-8");




        seenCmt = findViewById(R.id.xemcmt);
        viewGroup = findViewById(R.id.dialog_cmt);

        viewGroup1 = findViewById(R.id.dialog_product_post);

        bottomSheetDialog = new BottomSheetDialog(Detail_Post_Activity.this, R.style.BottomsheetTheme);
        sheetView = LayoutInflater.from(Detail_Post_Activity.this).inflate(R.layout.comment_dialogbottom, viewGroup);
        myavta_cmt = sheetView.findViewById(R.id.myavata_cmt);
        seenCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
                loadcmt();
            }
        });
        try {
            Picasso.get().load(APIService.IPIMAGE_USER+Id_user_Post +"/"+ image_user).placeholder(R.drawable.ic_img).into(myavta_cmt);
        } catch (Exception e) {

        }

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplication());
        layoutManager1.setOrientation(RecyclerView.VERTICAL);
        recyclerViewCmT = (RecyclerView) sheetView.findViewById(R.id.rcv_cmt);
        recyclerViewCmT.setLayoutManager(layoutManager1);


        sheetView.findViewById(R.id.send_cmt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDl = sheetView.findViewById(R.id.text_cmt);
                String value = editDl.getText().toString().trim();
                if (TextUtils.isEmpty(value)){
                    Toast.makeText(Detail_Post_Activity.this, "Enter comment...", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendcmnt(value);

            }


        });

      ///////////////////////////// product post //////////////////////////////



        bottomSheetDialog1 = new BottomSheetDialog(Detail_Post_Activity.this, R.style.BottomsheetTheme);
        sheetView1 = LayoutInflater.from(Detail_Post_Activity.this).inflate(R.layout.bottom_list_product_of_post, viewGroup1);

        LinearLayoutManager layoutManager_top = new LinearLayoutManager(Detail_Post_Activity.this);
        layoutManager_top.setOrientation(RecyclerView.VERTICAL);
        recyclerView_pro_post = sheetView1.findViewById(R.id.rcv_product_post);
        recyclerView_pro_post.setLayoutManager(layoutManager_top);

        xempro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog1.setContentView(sheetView1);
                bottomSheetDialog1.show();
                load_product_post();
            }
        });



//        getdetailpost();


        ooop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitInterface dataService = APIService.getService();
                Call<Void> callbackd = dataService.savefollow(AppLayout.id_user, Integer.valueOf(Id_user_Post));
                callbackd.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200 ){

                        Toast.makeText(Detail_Post_Activity.this, "follow Success " +AppLayout.id_user, Toast.LENGTH_SHORT).show();
                        ooop.setVisibility(View.GONE);
                        ooop1.setVisibility(View.VISIBLE);
                            checkusserfollow(Integer.valueOf(Id_user_Post));
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });
        ooop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    RetrofitInterface dataService = APIService.getService();
                    Call<Void> callbackd = dataService.deletefollow(AppLayout.id_user, Integer.valueOf(Id_user_Post));
                    callbackd.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200 ){

                                Toast.makeText(Detail_Post_Activity.this, "Unfollow Success " +AppLayout.id_user, Toast.LENGTH_SHORT).show();
                                ooop1.setVisibility(View.GONE);
                                ooop.setVisibility(View.VISIBLE);
                                checkusserfollow(Integer.valueOf(Id_user_Post));
                            }


                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
            }
        });


    }

    private void load_product_post() {
        RetrofitInterface dataService = APIService.getService();
        Call<ModelProduct> callback = dataService.get_product_post(id);
        callback.enqueue(new Callback<ModelProduct>() {
            @Override
            public void onResponse(Call<ModelProduct> call, Response<ModelProduct> response) {

          if (response.code() == 200 ){
                    List<ModelProduct> cart = response.body().getModelProduct();
                     adapter_product_post = new Adapter_Product_Post(cart, Detail_Post_Activity.this);
                     recyclerView_pro_post.setAdapter(adapter_product_post);
                     adapter_product_post.notifyDataSetChanged();


                }

            }

            @Override
            public void onFailure(Call<ModelProduct> call, Throwable t) {
                Toast.makeText(Detail_Post_Activity.this, "Rest api faill"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadcmt() {
        RetrofitInterface dataService = APIService.getService();
        Call<Model_Comment> callback = dataService.getCmt(id);
        callback.enqueue(new Callback<Model_Comment>() {
            @Override
            public void onResponse(Call<Model_Comment> call, Response<Model_Comment> response) {

                if(response.code()==404){
                    Toast.makeText(Detail_Post_Activity.this, "Cmt faill", Toast.LENGTH_SHORT).show();
                }else if (response.code() == 200 ){

                    model_comments =  response.body().getModel_Comment();
                    Adapter_Comment = new Adapter_Comment(getApplication(),model_comments);
                    recyclerViewCmT.setAdapter(Adapter_Comment);
                    Adapter_Comment.notifyDataSetChanged();


                }

            }

            @Override
            public void onFailure(Call<Model_Comment> call, Throwable t) {
                Toast.makeText(Detail_Post_Activity.this, "Rest api faill"+t, Toast.LENGTH_SHORT).show();
            }
        });

    }
//
    private void sendcmnt( String value) {
        RetrofitInterface dataService = APIService.getService();
        Call<Void> callback = dataService.postComment(value, id, AppLayout.id_user);
        callback.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
               if (response.code() == 200 ){
                    Toast.makeText(Detail_Post_Activity.this, "Cmt success", Toast.LENGTH_SHORT).show();
                    editDl.setText("");
                    loadcmt();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Detail_Post_Activity.this, "Rest api faill"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkusserfollow(int id_of_user){
        RetrofitInterface dataService = APIService.getService();
        Call<Void> callbackd = dataService.getiduser_follow(AppLayout.id_user, id_of_user);
        callbackd.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
              if(response.code() == 200 ){
                    ooop.setVisibility(View.GONE);
                    ooop1.setVisibility(View.VISIBLE);
//                    Toast.makeText(Detail_Post_Activity.this, "Da folow", Toast.LENGTH_SHORT).show();

                }else if(response.code() == 404 ){
                    ooop1.setVisibility(View.GONE);
                    ooop.setVisibility(View.VISIBLE);
//                    Toast.makeText(Detail_Post_Activity.this, "chua folow", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }




    }
