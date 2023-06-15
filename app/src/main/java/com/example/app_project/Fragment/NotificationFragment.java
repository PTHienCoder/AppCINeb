package com.example.app_project.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Adapter.Adapter_Notification;
import com.example.app_project.AppLayout;
import com.example.app_project.Model.Model_Notification;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.example.app_project.interface_app.itemOnclickListenner_ItemCart;
import com.example.app_project.interface_app.itemOnclickListenner_ItemNoti;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationFragment extends Fragment {



    ////notification ///
    RecyclerView recyclerViewnotification;
    Adapter_Notification notificationAdapter;
    List<Model_Notification> notificationModelList;

    itemOnclickListenner_ItemNoti itemOnclickListenner_itemNoti;
    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_notification, container, false);




        ////////////////// new notification online////////////////////////
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(RecyclerView.VERTICAL);
        recyclerViewnotification = (RecyclerView) view.findViewById(R.id.rcvnotifi);
        recyclerViewnotification.setLayoutManager(layoutManager1);
        notificationModelList = new ArrayList<>();


        getnotification();


        itemOnclickListenner_itemNoti = new itemOnclickListenner_ItemNoti() {
            @Override
            public void Onclickitem_delete() {
                getnotification();
            }
        };
        return view;
    }



    public void getnotification() {
        RetrofitInterface dataService = APIService.getService();
        Call<Model_Notification> callback = dataService.getnotification(AppLayout.id_user);
        callback.enqueue(new Callback<Model_Notification>() {
            @Override
            public void onResponse(Call<Model_Notification> call, Response<Model_Notification> response) {
                if(response.code() ==200) {
                    List<Model_Notification>  UserModelListss =  response.body().getModel_Notification();
                    notificationAdapter = new Adapter_Notification(getContext(), UserModelListss, itemOnclickListenner_itemNoti);
                    recyclerViewnotification.setAdapter(notificationAdapter);
                    notificationAdapter.notifyDataSetChanged();

//                    Toast.makeText(getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();

                } else {

//                    Toast.makeText(getContext(), "That bai", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model_Notification> call, Throwable t) {
                ///   Toast.makeText(getContext(), "Rest api faill"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void reload(){
        getnotification();
    }

}