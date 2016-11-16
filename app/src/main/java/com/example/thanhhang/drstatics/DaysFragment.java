package com.example.thanhhang.drstatics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */


public class DaysFragment extends Fragment {

    private TextView txtDay, txtDate, txtWeek;
    int page;
    static final int ONE_DAY = 1000 * 60 * 60 * 24;
    static final int ONE_WEEK = ONE_DAY * 7;
    // đây.
    long ms = System.currentTimeMillis();
    Calendar a = Calendar.getInstance();
    int thuindex = a.get(Calendar.DAY_OF_WEEK);

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public DaysFragment() {
        // Required empty public constructor
    } // e muốn lặp nó ở đâu


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_days, container, false);

        txtDay = (TextView) view.findViewById(R.id.txtDay);
        txtDate = (TextView) view.findViewById(R.id.txtDate);
        txtWeek = (TextView) view.findViewById(R.id.txtWeek);
        init(page);
        return view;
    }


    public void init(int page) {
        txtDay.setText(getDay((thuindex + page) % 7));
        txtDate.setText(convertMStoDate(ms + page * ONE_DAY));
        txtWeek.setText(convertMStoWeek(ms + page * ONE_WEEK));
    }

    public String convertMStoDate(long ms) {
        Date date = new Date();
        date.setTime(ms);
        String f = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.S").format(date);
        return f;
    }

    public String convertMStoWeek(long ms) {

        String d = "";
        int first = thuindex - 2;
        int last = 7 - thuindex + 1;
        long firstMs = ms - first * ONE_DAY;
        long lastMs = ms + last * ONE_DAY;
        d = " Từ  " + convertMStoDate(firstMs) + "\n Đến " + convertMStoDate(lastMs);
        return d;
    }

    public String getDay(int d) {
        String day = "";
        switch (d) {
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 0:
                day = "Saturday";
                break;
            case 1:
                day = "Sunday";
                break;
        }

        return day;
    }
}




