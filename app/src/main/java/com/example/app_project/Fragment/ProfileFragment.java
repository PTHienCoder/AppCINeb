package com.example.app_project.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.app_project.Adapter.Adapter_Tab;
import com.example.app_project.AppLayout;
import com.example.app_project.Cart_Activity;
import com.example.app_project.Model.ModelUser;
import com.example.app_project.Model.Model_GetQty_Flower;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.example.app_project.Shopping_Activity;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private TabLayout tabLayouth;
    private ViewPager viewPagerh;
    ImageView avata;
    TextView tvname, follower, following, saved, edit, cart, addstory;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String USERNAME_KEY = "username_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String usernames, password;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        /////////////////////////
        sharedpreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        usernames = sharedpreferences.getString("USERNAME_KEY", null);

        avata = view.findViewById(R.id.avatar_useruc);
        tvname = view.findViewById(R.id.tvname);

        follower = view.findViewById(R.id.tvfollower);
        following = view.findViewById(R.id.tvfollowing);
        saved = view.findViewById(R.id.tvsaved);


        tvname.setText(AppLayout.username);

        getinfo_user(AppLayout.id_user);
        Get_Qty_Follower(AppLayout.id_user);
        ///////////////////////////
        tabLayouth = view.findViewById(R.id.tab);
        viewPagerh = view.findViewById(R.id.tab_layout);
        Adapter_Tab adapter_tab = new Adapter_Tab(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerh.setAdapter(adapter_tab);
        tabLayouth.setupWithViewPager(viewPagerh);

        TextView addgiohang = view.findViewById(R.id.addgiohang);
        addgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Cart_Activity.class);
                startActivity(intent);
            }
        });

        return  view;
    }
    private void getinfo_user(int id_user){
        RetrofitInterface dataService = APIService.getService();
        Call<ModelUser> callback = dataService.GetIforUser(id_user);
        callback.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {

                if(response.code() == 200 ){
                    for(ModelUser item:response.body().getModelUser()){
                        try {
                            Picasso.get().load(APIService.IPIMAGE_USER+AppLayout.id_user+"/"+ item.getImage_user()).into(avata);
                        }catch (Exception e){

                        }
                    }
                }else{
                    Toast.makeText(getContext(), "id_userxxx :  "+id_user, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                Toast.makeText(getContext(), "Rest api faill"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Get_Qty_Follower(int id_user){
        RetrofitInterface dataService = APIService.getService();
        Call<Model_GetQty_Flower> callback = dataService.Get_Qty_Follower(id_user);
        callback.enqueue(new Callback<Model_GetQty_Flower>() {
            @Override
            public void onResponse(Call<Model_GetQty_Flower> call, Response<Model_GetQty_Flower> response) {

                if(response.code() == 200 ){
                    for(Model_GetQty_Flower item:response.body().getModel_GetQty_Flower()){
                        follower.setText(String.valueOf(item.getQty_Follower()));
                        following.setText(String.valueOf(item.getQty_Follow()));
                        saved.setText(String.valueOf(item.getQty_Save()));
                    }
                }else{
                    Toast.makeText(getContext(), "id_userxxx :  "+id_user, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Model_GetQty_Flower> call, Throwable t) {
                Toast.makeText(getContext(), "Rest api faill"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }



}