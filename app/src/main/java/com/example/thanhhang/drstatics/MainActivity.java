package com.example.thanhhang.drstatics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ViewPager viewPager;
    DayAdapter adapter;
    ArrayList<Fragment> listFragment;
    DaysFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        listFragment = getFragment();
        adapter = new DayAdapter(getSupportFragmentManager(), listFragment);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

    }

    public ViewPager.OnPageChangeListener onSlideViewPager = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position >= 0 && position < 7) {
                viewPager.setCurrentItem(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private ArrayList<Fragment> getFragment() {
        ArrayList<Fragment> lstFrag = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            fragment = new DaysFragment();
            fragment.setPage(i);
            lstFrag.add(fragment);
        }
        return lstFrag;
    }
}

 class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}
