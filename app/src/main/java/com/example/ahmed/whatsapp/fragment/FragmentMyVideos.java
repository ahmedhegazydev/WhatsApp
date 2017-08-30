package com.example.ahmed.whatsapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmed.whatsapp.R;
import com.example.ahmed.whatsapp.adapters.ViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ahmed on 22/08/17.
 */

public class FragmentMyVideos extends Fragment implements ViewPager.OnPageChangeListener {

    View viewRoot = null;
    Context context = null;
    ViewPager viewPager = null;
    private ArrayList<Fragment> listFragments = new ArrayList<Fragment>();
    ActionBar actionBar = null;
    String[] indicators = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getContext();
        context = getActivity();
        context = container.getContext();


        viewRoot = inflater.inflate(R.layout.frag_my_videos, container, false);


        TabLayout tabLayout = (TabLayout) viewRoot.findViewById(R.id.tabs);
//       for (int i = 0; i < indicators.length; i++){
//           tabLayout.addTab(tabLayout.newTab().setText(indicators[i]));
//       }


        listFragments.add(new FragmentVidLiked());
        listFragments.add(new FragmentVidUnlocked());
        listFragments.add(new FragmentVidUploads());

        viewPager = (ViewPager) viewRoot.findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), listFragments, new String[]{"LIKED", "UNLOCKED", "UPLOADS"}));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        return viewRoot;

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
