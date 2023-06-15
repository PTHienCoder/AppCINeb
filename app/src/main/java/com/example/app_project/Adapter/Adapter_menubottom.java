package com.example.app_project.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.app_project.Fragment.HomeFragment;
import com.example.app_project.Fragment.NotificationFragment;
import com.example.app_project.Fragment.ProfileFragment;
import com.example.app_project.Fragment.WatchFragment;


public class Adapter_menubottom extends FragmentStatePagerAdapter {


    public Adapter_menubottom(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new WatchFragment();
            case 2:
                return new NotificationFragment();
            case 3:
                return new ProfileFragment();
            default:
                return new HomeFragment();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}