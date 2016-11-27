package com.fiap.carango.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fiap.carango.fragments.CarrosFragment;

/**
 * Created by rm31014 on 19/11/2016.
 */
public class TabAdapter extends FragmentStatePagerAdapter {


    public static final int TOTAL_TABS = 2;

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Bundle args = new Bundle();

        if (position == 0) {
            args.putString("tipo","classicos");
        } else if (position == 1) {
            args.putString("tipo","esportivos");
        }

        CarrosFragment f = new CarrosFragment();

        f.setArguments(args);

        return f;
    }

    @Override
    public int getCount() {
        return TOTAL_TABS;
    }
}
