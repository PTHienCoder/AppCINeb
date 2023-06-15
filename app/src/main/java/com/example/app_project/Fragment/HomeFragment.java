package com.example.app_project.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Adapter.Post_Adapter;
import com.example.app_project.Adapter.SliderAdapter;
import com.example.app_project.Adapter.TopPost_Adapter;
import com.example.app_project.Model.ModelPost;
import com.example.app_project.R;
import com.example.app_project.Search_Product;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.example.app_project.Shopping_Activity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    SliderView sliderView;

    int[] images = {R.drawable.banner,
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.baner3};

    //// top posst///
    RecyclerView recyclerViewpost_top;
    TopPost_Adapter post_adapter_top ;
    List<ModelPost> postModelList_top;

    //// new posst///
    RecyclerView recyclerViewpost;
    Post_Adapter post_adapter ;
    List<ModelPost> postModelList;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        //////////////////slide/////////////////
        sliderView = view.findViewById(R.id.image_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();



        ////////////////// top posst ////////////////////////
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewpost_top = (RecyclerView) view.findViewById(R.id.rcvnewposst);
        recyclerViewpost_top.setLayoutManager(layoutManager);
        postModelList_top = new ArrayList<>();


        ////////////////// new posst ////////////////////////
        LinearLayoutManager layoutManager_top = new LinearLayoutManager(getContext());
        layoutManager_top.setOrientation(RecyclerView.VERTICAL);
        recyclerViewpost = (RecyclerView) view.findViewById(R.id.rcvnpost);
        recyclerViewpost.setLayoutManager(layoutManager_top);
        postModelList = new ArrayList<>();

        callApigetlistToppost();
        callApigetlistnewpost();






        return view;
    }
    private void callApigetlistToppost() {
        RetrofitInterface dataService = APIService.getService();
        Call<ModelPost> callback = dataService.LoadPost();
        callback.enqueue(new Callback<ModelPost>() {
            @Override
            public void onResponse(Call<ModelPost> call, Response<ModelPost> response) {
                if (response.isSuccessful()) {
                    List<ModelPost> ModelPost_top = response.body().getModelPost();

                    post_adapter_top = new TopPost_Adapter(ModelPost_top, getContext());
                    recyclerViewpost_top.setAdapter(post_adapter_top);
                    post_adapter_top.notifyDataSetChanged();


                } else {

                    //     Toast.makeText(getContext(), "That bai", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelPost> call, Throwable throwable) {
                Toast.makeText(getContext(), "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callApigetlistnewpost() {
        RetrofitInterface dataService = APIService.getService();
        Call<ModelPost> callback = dataService.LoadPost();
        callback.enqueue(new Callback<ModelPost>() {
            @Override
            public void onResponse(Call<ModelPost> call, Response<ModelPost> response) {
                if (response.isSuccessful()) {
                    List<ModelPost> ModelPost_new = response.body().getModelPost();

                    post_adapter = new Post_Adapter(ModelPost_new, getContext());
                    recyclerViewpost.setAdapter(post_adapter);
                    post_adapter.notifyDataSetChanged();

                } else {


                }
            }
            @Override
            public void onFailure(Call<ModelPost> call, Throwable throwable) {
                Toast.makeText(getContext(), "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void reload(){
        callApigetlistnewpost();
    }


}