package com.example.thanhhang.drstatics;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ThanhHang on 11/11/2016.
 */

public class DayAdapter extends FragmentPagerAdapter{
    ArrayList<Fragment> lstFrag;
    FragmentManager fm;

    public DayAdapter(FragmentManager fm, ArrayList<Fragment> lstFrag) {
        super(fm);
        this.lstFrag = lstFrag;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return lstFrag.get(position);
    }

    @Override
    public int getCount() {
        return lstFrag.size();
    }
}
