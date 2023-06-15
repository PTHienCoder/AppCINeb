package com.example.app_project.Profile_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Adapter.Post_Adapter;
import com.example.app_project.AppLayout;
import com.example.app_project.Model.ModelPost;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Save_Fragment extends Fragment {
    RecyclerView recyclerViewpost;
    Post_Adapter PostAdapter;

    public Save_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_save, container, false);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(RecyclerView.VERTICAL);
        recyclerViewpost = (RecyclerView) view.findViewById(R.id.rcvnpost);
        recyclerViewpost.setLayoutManager(layoutManager1);

//        postModelList.add(new Post_Model("hehe","PTHien", "Easy", "30min",R.drawable.baner1, R.drawable.ic_person));
//        postModelList.add(new Post_Model("hehe","PTHien", "Easy", "30min",R.drawable.baner1, R.drawable.ic_person));
        callApigetlistnewpost();

       return  view;
    }

    private void callApigetlistnewpost() {
        RetrofitInterface dataService = APIService.getService();
        Call<ModelPost> callback = dataService.getsave_post(AppLayout.id_user);
        callback.enqueue(new Callback<ModelPost>() {
            @Override
            public void onResponse(Call<ModelPost> call, Response<ModelPost> response) {
                if (response.isSuccessful()) {
                    List<ModelPost> postModelList = response.body().getModelPost();

                    PostAdapter = new Post_Adapter(postModelList, getContext());
                    recyclerViewpost.setAdapter(PostAdapter);
                    PostAdapter.notifyDataSetChanged();


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


}