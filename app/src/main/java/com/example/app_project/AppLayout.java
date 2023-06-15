package com.example.app_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.app_project.Adapter.Adapter_menubottom;
import com.example.app_project.Fragment.HomeFragment;
import com.example.app_project.Fragment.NotificationFragment;
import com.example.app_project.Fragment.ProfileFragment;
import com.example.app_project.Fragment.WatchFragment;
import com.example.app_project.Model.ModelUser;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppLayout extends AppCompatActivity {
    public static String username, avata_user;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing id.
    public static final String IDUSER_KEY = "iduser_key";
    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    private String email_us, password;
    public static int id_user;

    private  TextView nametvdb, emailtvdb;
    private ImageView avatardb, coverimgdb;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView2;


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            finish();
        }else{
            super.onBackPressed();
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_layout);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        id_user = sharedpreferences.getInt(IDUSER_KEY, 0);

        email_us = sharedpreferences.getString(EMAIL_KEY, null);
        password = sharedpreferences.getString(PASSWORD_KEY, null);

        getinfo_user(id_user);



//        Intent intent = getIntent();
//        username = ""+intent.getStringExtra("usname");
//        avata_user = ""+intent.getStringExtra("img");
        ImageButton search = findViewById(R.id.search_icon);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AppLayout.this, Search_Post.class);
                startActivity(intent2);
            }
        });

        ///////////////////// navbar letf //////////////////////

        if (Build.VERSION.SDK_INT>=21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window  = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView2 = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.collapsingtoolbarlayout);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(AppLayout.this
                , mDrawerLayout, toolbar, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView2.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.pro1:
                        Intent intent = new Intent(AppLayout.this, Shopping_Activity.class);
                        startActivity(intent);
                        mDrawerLayout.close();
                        break;

                    case  R.id.pro2:
                        Intent intent1 = new Intent(AppLayout.this, Cart_Activity.class);
                        startActivity(intent1);
                        mDrawerLayout.close();
                        break;

                    case R.id.pro3:
                        Intent intent2 = new Intent(AppLayout.this, My_Order_Activity.class);
                        startActivity(intent2);
                        mDrawerLayout.close();
                        break;

                    case R.id.pro4:
                        Intent intent3 = new Intent(AppLayout.this, Infor_Shipping.class);
                        startActivity(intent3);
                        mDrawerLayout.close();
                        break;

                    case R.id.ab1:
//                        intent = new Intent(DashboardActivity.this, Setting_app.class);
//                        startActivity(intent);
                        mDrawerLayout.close();

                        break;
                    case R.id.ab2:

                        break;
                    case R.id.ab3:
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt(IDUSER_KEY, 0);
                        editor.putString(EMAIL_KEY, null);
                        editor.putString(PASSWORD_KEY, null);
                        // to save our data with key and value.
                        editor.commit();
                        editor.clear();
                        Intent inte = new Intent(AppLayout.this, Login_Activity.class);
                        startActivity(inte);
                        finish();
                        break;

                }
                return false;
            }
        });


        View header= navigationView2.getHeaderView(0);
        coverimgdb = header.findViewById(R.id.cat_img);
        emailtvdb = header.findViewById(R.id.emailtvdb);
        nametvdb = header.findViewById(R.id.nametvdb);


        //////////////////// navbar bottom
        BottomNavigationView navigationView = findViewById(R.id.nav_bottom);
        ViewPager viewPager = findViewById(R.id.viewpagerbottom);
        Adapter_menubottom adapter_ab0 = new Adapter_menubottom(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter_ab0);
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        HomeFragment homeFragment = (HomeFragment) viewPager.getAdapter().instantiateItem(viewPager,0);
                        homeFragment.reload();
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.nav_watch).setChecked(true);
                        WatchFragment WatchFragment = (WatchFragment) viewPager.getAdapter().instantiateItem(viewPager,1);

                        WatchFragment.reload();
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.nav_notification).setChecked(true);
                        NotificationFragment NotificationFragment = (NotificationFragment) viewPager.getAdapter().instantiateItem(viewPager,2);
                        NotificationFragment.reload();
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.nav_profile).setChecked(true);
                        ProfileFragment ProfileFragment = (ProfileFragment) viewPager.getAdapter().instantiateItem(viewPager,3);
                        //         ProfileFragment.reloadata();
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        viewPager.setCurrentItem(0);
                        onRestart();
                        break;
                    case R.id.nav_watch:
                        viewPager.setCurrentItem(1);
                        break;

                    case R.id.nav_add:
                        Toast.makeText(AppLayout.this, "add post ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_notification:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.nav_profile:
                        viewPager.setCurrentItem(3);
                        break;


                }
                return true;
            }
        });


    }
    private void getinfo_user(int id_user){
//      Toast.makeText(AppLayout.this, "id user : "+id_user, Toast.LENGTH_SHORT).show();

        RetrofitInterface dataService = APIService.getService();
        Call<ModelUser> callback = dataService.GetIforUser(id_user);
        callback.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {

                if(response.code() == 200 ){
                    for(ModelUser item:response.body().getModelUser()){

                        nametvdb.setText(item.getName());
                        emailtvdb.setText(item.getEmail());
                        username = item.getName();
                        avata_user = item.getImage_user();

                        try {
                            Picasso.get().load(APIService.IPIMAGE_USER +id_user+"/"+ item.getImage_user()).into(coverimgdb);
                        }catch (Exception e){
                            Picasso.get().load(R.drawable.hi).placeholder(R.drawable.hi).into(coverimgdb);
                        }
                    }


                }else{
                    Toast.makeText(AppLayout.this, "id_userxxx :  "+id_user, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                Toast.makeText(AppLayout.this, "Rest api faill"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        checklogin();

    }

    @Override
    protected void onResume() {
        checklogin();
        super.onResume();
    }

    @Override
    protected void onStop() {
        checklogin();
        super.onStop();
    }

    public void checklogin(){

        if (email_us == null && password == null) {
            Intent i = new Intent(AppLayout.this, Login_Activity.class);
            startActivity(i);
            finish();
        }
    }

}