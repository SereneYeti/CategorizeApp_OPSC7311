package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button button;
    private EditText editText;
    private EditText goalText;
    private ArrayList<String> items;
    private ArrayAdapter itemsadapter;
    private ListView listView;

    List<String> ITEM_LIST;
    ArrayAdapter<String> arrayadapter;


    ArrayList<Recycling> recyclingArrayList = new ArrayList<Recycling>();
    RecyclerView recyclerView;

    View v;
    String names [];

    //ListView listView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //initialiseList();
        //setUpRecycler();
    }




    private void initialiseList() {

        Recycling unknown = new Recycling("0", "Unknown",R.drawable.ic_baseline_insert_photo_24, "2");
        recyclingArrayList.add(unknown);
        Recycling things = new Recycling("1", "things",R.drawable.ic_baseline_insert_photo_24, "3");
        recyclingArrayList.add(things);
        Recycling destinations = new Recycling("2", "destinations",R.drawable.ic_baseline_insert_photo_24, "5");
        recyclingArrayList.add(destinations);
    }
    private void setUpRecycler() {
        //recyclerView = (RecyclerView)v.findViewById(R.id.recycleView);
        RecycleAdapter recycleAdapter = new RecycleAdapter(recyclingArrayList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_home,container,false);
        button = v.findViewById(R.id.btnCreateCategory_);
        editText = v.findViewById(R.id.et_CategoryName_);
        goalText = v.findViewById(R.id.etGoalNumItems2);

        button.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key",editText.getText().toString());

                AddItemsFragment itemsFragment = new AddItemsFragment();
                itemsFragment.setArguments(bundle);
                Singleton.getInstance().setTestVar2(editText.getText().toString());

                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,itemsFragment).addToBackStack("itemsFragment").commit();
            }
        });

        listView = v.findViewById(R.id.listView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                additem(v);

                Bundle bundle = new Bundle();
                bundle.putStringArrayList("itemsArrayList",items);

                CreateCategoryFragment createCategoryFragment = new CreateCategoryFragment();
                createCategoryFragment.setArguments(bundle);
                Singleton.getInstance().setTestVar2(editText.getText().toString());

                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,createCategoryFragment).addToBackStack("CreateCategoryFragment").commit();
            }
        });

        items= new ArrayList<>();
        itemsadapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1,items);
        listView.setAdapter(itemsadapter);
        setUpListViewListner();
        return v;
    }

    private void setUpListViewListner() {

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getContext();
                Toast.makeText(context,"Item Removed", Toast.LENGTH_LONG).show();

                items.remove(position);
                itemsadapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void additem(View v) {
        String itemText = editText.getText().toString();
        int goalAmount = Integer.parseInt(goalText.getText().toString());

        if(!(itemText.equals(""))){
            itemsadapter.add(itemText);
            Singleton.getInstance().Categories.add(itemText);
            Singleton.getInstance().Goals.add(goalAmount);
            editText.setText("");
        }
        else
        {
            Toast.makeText(getContext(),"Please enter text...",Toast.LENGTH_LONG).show();
        }

    }

}
