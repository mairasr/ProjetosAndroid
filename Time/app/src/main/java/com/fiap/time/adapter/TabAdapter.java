package com.fiap.time.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fiap.time.fragments.TimesFragment;

/**
 * Created by rm31014 on 19/11/2016.
 */
public class TabAdapter extends FragmentStatePagerAdapter {


    public static final int TOTAL_TABS = 1;

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

//        Bundle args = new Bundle();

        TimesFragment f = new TimesFragment();

//        f.setArguments(args);

        return f;
    }

    @Override
    public int getCount() {
        return TOTAL_TABS;
    }
}
