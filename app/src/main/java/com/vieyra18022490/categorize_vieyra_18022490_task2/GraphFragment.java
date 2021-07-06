package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class GraphFragment extends Fragment {

    private static final String TAG = "GraphFragment";
    View v;
    BarChart barChart;
    Spinner chooseCategory;
    ArrayList<BarEntry> barEntries;
    ArrayList<String> theHeadings;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_mapgraph,container,false);
        chooseCategory = (Spinner)v.findViewById(R.id.spChooseGraphGoal);


        SetUpSpinner();
        HandleBarGraph();

        chooseCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HandleBarGraph();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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

        String item = chooseCategory.getSelectedItem().toString();
        Log.i(TAG,item + "on item selected");
        if(Singleton.getInstance().getCatagories().containsKey(item))
        {
            ArrayList<Item> tempItem = Singleton.getInstance().getCatagories().get(item);
            if(tempItem.size() < 0) {
                DoBarEntries(0, Singleton.getInstance().getGoals().get(chooseCategory.getSelectedItemPosition()));

            }
            else{
                DoBarEntries(tempItem.size(), Singleton.getInstance().getGoals().get(chooseCategory.getSelectedItemPosition()));
            }
            Log.i(TAG,(tempItem.size()) + "on item selected");
            //Log.i(TAG,(tempItem.size()+1) + "on item selected");
            BarDataSet barDataSet = new BarDataSet(barEntries, "Type");
            BarData theData = new BarData(theHeadings,barDataSet);
            barChart.setData(theData);
            barChart.notifyDataSetChanged();
        }


        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
    }

    public void SetUpSpinner(){
        ArrayList<String> options=new ArrayList<String>();
        if(Singleton.getInstance() != null)
        {
            options = new ArrayList<String>(Singleton.getInstance().getCategoryNames());
            //ArrayList<String> data = savedInstanceState.getStringArrayList("itemsArrayList");
            //options = data;
        }
        else
        {
            options = new ArrayList<String>(new ArrayList<>());
        }
        //options = new ArrayList<>(Singleton.getInstance().getCategories());



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,options);
        adapter.notifyDataSetChanged();
        chooseCategory.setAdapter(adapter);

    }



}
