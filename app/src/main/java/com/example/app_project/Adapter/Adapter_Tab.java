package com.example.app_project.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.app_project.Profile_Fragment.MyProject_Fragment;
import com.example.app_project.Profile_Fragment.Save_Fragment;

public class Adapter_Tab extends FragmentStatePagerAdapter {

    public Adapter_Tab(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MyProject_Fragment();
            case 1:
                return new Save_Fragment();
            default:
                return new MyProject_Fragment();
        }

    }

    public int getCount() {
        return 2;
    }

    @Nullable
    public CharSequence getPageTitle(int position) {
         String Title ="";
         switch (position){
             case 0:
                 Title = "My Project";
                 break;
             case 1:
                 Title ="Saved";
                 break;
         }
         return Title;

    }

}
