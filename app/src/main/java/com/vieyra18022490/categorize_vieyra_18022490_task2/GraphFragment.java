package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class GraphFragment extends Fragment {

    View v;
    BarChart barChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_mapgraph,container,false);

        barChart = (BarChart)v.findViewById((R.id.barGraph));
        //Essentially the y axis data
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(44f,0));
        barEntries.add(new BarEntry(56f,1));
        barEntries.add(new BarEntry(65f,2));

        //X Headings
        ArrayList<String> theDates = new ArrayList<>();;
        theDates.add("April");
        theDates.add("May");
        theDates.add("June");

        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");

        BarData theData = new BarData(theDates,barDataSet);
        barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);

        return v;
    }



}
