package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "HomeFragment";

    private Button button;
    private EditText editText;
    private EditText goalText;
    private TextView goalAmountText;
    private TextView tvCategoryName_HomeFrag;
    private TextView tvCategoryGoal_Remaining;

    private ArrayList<String> items;
    private ArrayAdapter itemsadapter;
    private ListView listView;

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
       Recycling music = new Recycling("3", "music",R.drawable.ic_baseline_insert_photo_24, "5");
       recyclingArrayList.add(music);
       Recycling drawings = new Recycling("4", "drawings",R.drawable.ic_baseline_insert_photo_24, "5");
       recyclingArrayList.add(drawings);
       Recycling moives = new Recycling("5", "moives",R.drawable.ic_baseline_insert_photo_24, "5");
       recyclingArrayList.add(moives);
       Recycling games = new Recycling("6", "games",R.drawable.ic_baseline_insert_photo_24, "5");
       recyclingArrayList.add(games);
       Recycling clothes = new Recycling("7", "clothes",R.drawable.ic_baseline_insert_photo_24, "5");
       recyclingArrayList.add(clothes);
   }
   private void setUpRecycler() {
       recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
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
        goalAmountText = v.findViewById(R.id.tvCategoryGoalAmount2);
        tvCategoryGoal_Remaining = v.findViewById(R.id.tvCategoryGoal);
        tvCategoryName_HomeFrag = v.findViewById(R.id.tvCategoryName_HomeFragment);


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
                Singleton.getInstance().setCategoryNames(items);


                //getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,createCategoryFragment).addToBackStack("CreateCategoryFragment").commit();
            }
        });

        items= new ArrayList<>();
        itemsadapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1,items);
        listView.setAdapter(itemsadapter);
        setUpListViewListner();

        //Log.i(TAG, " Is this empty: " + String.valueOf(Singleton.getInstance().Categories.isEmpty()));

        if(Singleton.getInstance().categoryNames.isEmpty() == false && Singleton.getInstance().categoryNames.size() != 0)
        {
            List<String> test = Singleton.getInstance().getCategoryNames();
            //Log.i(TAG, "I'm in!");
            //Log.i(TAG, Singleton.getInstance().Categories.get(0));
            //Log.i(TAG, Singleton.getInstance().Categories.get(0));

            for (String s: test
            ) {
                repopluteList(v,s);

            }
            //ShowCreateCategoryUI();
            //HideItemListUI();
        }

        setUpRecycler();
        initialiseList();
        ShowCreateCategoryUI();
        HideItemListUI();

        return v;
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.nav_AddItem||item.getItemId()==R.id.nav_search)
        {
            ShowCreateCategoryUI();
            HideItemListUI();
        };
        return super.onContextItemSelected(item);
    }

    private void setUpListViewListner() {

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getContext();
                Toast.makeText(context,"Opening Category List", Toast.LENGTH_LONG).show();

                //MainActivity mainActivity  = new MainActivity();
                Log.i(TAG, "Position: " + position);
                Log.i(TAG, "Position: - CATEGORY NAME: " + Singleton.getInstance().categoryNames.get(position));
                //Log.i(TAG, "CONTAINS KEY? -: " + Singleton.getInstance().Catagories.containsKey(Singleton.getInstance().categoryNames.get(position)));
                //Log.i(TAG, "CONTAINS object relating to above key ^ -: " + Singleton.getInstance().Catagories.get(Singleton.getInstance().categoryNames.get(position)));
                //mainActivity.setTestRunnable(Singleton.getInstance().categoryNames.get(position), Singleton.getInstance().Goals.get(position));
                tvCategoryName_HomeFrag.setText("Category Name: " + Singleton.getInstance().getCategoryNames().get(position));
                int goalRemaining = (Singleton.getInstance().Goals.get(position) - Singleton.getInstance().Catagories.get(Singleton.getInstance().categoryNames.get(position)).size());
                Log.i(TAG, "Goal Remaining"+ String.valueOf(goalRemaining));
                tvCategoryGoal_Remaining.setText("Amount left until category goal is reached: " +  String.valueOf(goalRemaining));
                ShowItemListUI();
                setUpRecycler();
                initialiseList();
                HideCreateCategoryUI();



                //items.remove(position);
                //itemsadapter.notifyDataSetChanged();
                return true;
            }
        });
    }


    private void additem(View v) {
        String itemText = editText.getText().toString();
        int goalAmount = Integer.parseInt(goalText.getText().toString());

        if(!(itemText.equals(""))){
            itemsadapter.add(itemText);
            Singleton.getInstance().categoryNames.add(itemText);

            Singleton.getInstance().Catagories.put(itemText,new ArrayList<Item>());

            Log.i(TAG, Singleton.getInstance().categoryNames.get(Singleton.getInstance().categoryNames.size()-1));
            Singleton.getInstance().Goals.add(goalAmount);
            editText.setText("");
        }
        else
        {
            Toast.makeText(getContext(),"Please enter text...",Toast.LENGTH_LONG).show();
        }

    }

    private void repopluteList(View v, String itemText) {

        if(!(itemText.equals(""))){
            itemsadapter.add(itemText);
            editText.setText("");
        }
        else
        {
            Toast.makeText(getContext(),"Please enter text...",Toast.LENGTH_LONG).show();
        }

    }

    private void HideCreateCategoryUI(){
        button.setVisibility(View.INVISIBLE);
        editText.setVisibility(View.INVISIBLE);
        goalText.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.INVISIBLE);
        goalAmountText.setVisibility(View.INVISIBLE);
    }

    private void ShowCreateCategoryUI(){
        button.setVisibility(View.VISIBLE);
        editText.setVisibility(View.VISIBLE);
        goalText.setVisibility(View.VISIBLE);
        listView.setVisibility(View.VISIBLE);
        goalAmountText.setVisibility(View.VISIBLE);
    }

    private void HideItemListUI(){
        recyclerView.setVisibility(View.INVISIBLE);
        tvCategoryGoal_Remaining.setVisibility(View.INVISIBLE);
        tvCategoryName_HomeFrag.setVisibility(View.INVISIBLE);
    }
    private void ShowItemListUI(){
        recyclerView.setVisibility(View.VISIBLE);
        tvCategoryGoal_Remaining.setVisibility(View.VISIBLE);
        tvCategoryName_HomeFrag.setVisibility(View.VISIBLE);
    }



}
