package com.example.app_project.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Adapter.Apdapter_Cate_Post;
import com.example.app_project.Adapter.Post_Adapter;
import com.example.app_project.Adapter.TopPost_Adapter;
import com.example.app_project.Model.ModelPost;
import com.example.app_project.Model.ModelUser;
import com.example.app_project.Model.Model_Cate_Post;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WatchFragment extends Fragment {
    //// new posst///
    RecyclerView recyclerViewCate;
    Apdapter_Cate_Post apdapter_cate_post ;
    List<Model_Cate_Post> model_cate_posts;

    public WatchFragment() {
        // Required empty public constructor
    }

    //// new posst///
    RecyclerView recyclerViewpost;
    Post_Adapter post_adapter ;
    List<ModelPost> postModelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watch, container, false);

        ////////////////// top posst ////////////////////////
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewCate = (RecyclerView) view.findViewById(R.id.rvc_cate_post);
        recyclerViewCate.setLayoutManager(layoutManager);
        load_rcvCate();


        ////////////////// new posst ////////////////////////
//        LinearLayoutManager layoutManager_top = new LinearLayoutManager(getContext());
//        layoutManager_top.setOrientation(RecyclerView.VERTICAL);
//        recyclerViewpost = (RecyclerView) view.findViewById(R.id.rvc_cate_post);
//        recyclerViewpost.setLayoutManager(layoutManager_top);
//        postModelList = new ArrayList<>();
//        callApigetlistnewpost();


        return view;
    }

//    private void callApigetlistnewpost() {
//        RetrofitInterface dataService = APIService.getService();
//        Call<ModelPost> callback = dataService.LoadPost();
//        callback.enqueue(new Callback<ModelPost>() {
//            @Override
//            public void onResponse(Call<ModelPost> call, Response<ModelPost> response) {
//                if (response.isSuccessful()) {
//                    List<ModelPost> ModelPost_new = response.body().getModelPost();
//
//                    post_adapter = new Post_Adapter(ModelPost_new, getContext());
//                    recyclerViewpost.setAdapter(post_adapter);
//                    post_adapter.notifyDataSetChanged();
//
//                } else {
//
//
//                }
//            }
//            @Override
//            public void onFailure(Call<ModelPost> call, Throwable throwable) {
//                Toast.makeText(getContext(), "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    private void load_rcvCate() {
        RetrofitInterface dataService = APIService.getService();
        Call<Model_Cate_Post> callback = dataService.Load_Cate_Post();
        callback.enqueue(new Callback<Model_Cate_Post>() {
            @Override
            public void onResponse(Call<Model_Cate_Post> call, Response<Model_Cate_Post> response) {
                if (response.isSuccessful()) {
                    List<Model_Cate_Post> Model_Cate_Post = response.body().getModel_Cate_Post();
                    apdapter_cate_post = new Apdapter_Cate_Post(Model_Cate_Post, getContext());
                    recyclerViewCate.setAdapter(apdapter_cate_post);
                    apdapter_cate_post.notifyDataSetChanged();

                     int i =0;
//                    for(Model_Cate_Post item:response.body().getModel_Cate_Post()){
//                      i++;
////                                username = item.getName();
////                                img_user = item.getImage_user();
//                    }
//                    Toast.makeText(getContext(), "D : " + i, Toast.LENGTH_SHORT).show();
                } else {

                }
            }

            @Override
            public void onFailure(Call<Model_Cate_Post> call, Throwable throwable) {
//                Toast.makeText(getContext(), "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });


    }




    public void reload(){
       load_rcvCate();
    }
}