package com.example.app_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.app_project.Adapter.Post_Adapter;
import com.example.app_project.Model.ModelPost;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_Post extends AppCompatActivity {
    //// new posst///
    RecyclerView recyclerViewpost;
    Post_Adapter post_adapter ;
    List<ModelPost> postModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_post);

        ImageButton backc = findViewById(R.id.backc);
        backc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ////////////////// new posst ////////////////////////
        LinearLayoutManager layoutManager_top = new LinearLayoutManager(Search_Post.this);
        layoutManager_top.setOrientation(RecyclerView.VERTICAL);
        recyclerViewpost = (RecyclerView) findViewById(R.id.rcv_Search_Post);
        recyclerViewpost.setLayoutManager(layoutManager_top);


        SearchView sv = (SearchView) findViewById(R.id.sv);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query.trim())){
                    callApigetlistnewpost(query);
                }else{
                    callApigetlistnewpost("all");
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                if (!TextUtils.isEmpty(query.trim())){
                    callApigetlistnewpost(query);
                }else{
                    callApigetlistnewpost("all");
                }
                return false;
            }
        });
        callApigetlistnewpost("all");
    }
    private void callApigetlistnewpost(String query) {
        RetrofitInterface dataService = APIService.getService();
        Call<ModelPost> callback = dataService.Search_Post(query);
        callback.enqueue(new Callback<ModelPost>() {
            @Override
            public void onResponse(Call<ModelPost> call, Response<ModelPost> response) {
                if (response.isSuccessful()) {
                    List<ModelPost> ModelPost_new = response.body().getModelPost();

                    post_adapter = new Post_Adapter(ModelPost_new, Search_Post.this);
                    recyclerViewpost.setAdapter(post_adapter);
                    post_adapter.notifyDataSetChanged();

                } else {


                }
            }
            @Override
            public void onFailure(Call<ModelPost> call, Throwable throwable) {
                Toast.makeText(Search_Post.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }

}