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
    ArrayList<BarEntry> barEntries;
    ArrayList<String> theHeadings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_mapgraph,container,false);

        HandleBarGraph();


        return v;
    }

    public void DoBarEntries(float data1, float data2){

        barEntries.add(new BarEntry(data1,0));
        barEntries.add(new BarEntry(data2,1));
        //barEntries.add(new BarEntry(65f,2));

        //X Headings

        theHeadings.add("Items in Category");
        theHeadings.add("Goal");
        //theDates.add("June");
    }

    public void HandleBarGraph(){
        barChart = (BarChart)v.findViewById((R.id.barGraph));
        //Essentially the y axis data
        barEntries = new ArrayList<>();
        theHeadings = new ArrayList<>();;

        DoBarEntries(2f,10f);;
        BarDataSet barDataSet = new BarDataSet(barEntries, "Type");
        BarData theData = new BarData(theHeadings,barDataSet);
        barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
    }



}
