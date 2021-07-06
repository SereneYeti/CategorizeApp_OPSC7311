package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "SearchFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View v;
    Button btnSearch_;
    Button btnClear_;
    Spinner chooseCategory2;
    EditText etSearrchItem_;
    TextView tvIKey_;
    TextView tvIName_;
    TextView tvIDate_;



    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_search,container,false);


        btnSearch_ = v.findViewById(R.id.btnSearch);
        chooseCategory2 = (Spinner)v.findViewById(R.id.spChooseCategory);
        etSearrchItem_ = v.findViewById(R.id.etSearrchItem);
        tvIDate_ = v.findViewById(R.id.tvIDate);
        tvIName_ = v.findViewById(R.id.tvIName);
        tvIKey_ = v.findViewById(R.id.tvIKey);
        btnClear_ = v.findViewById(R.id.btnClear);
        SetUpSpinner();

        btnSearch_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Item tempItem = findItem(etSearrchItem_.getText().toString());
                tvIKey_.setText(tempItem.key);
                tvIName_.setText(tempItem.Name);
                tvIDate_.setText(tempItem.Date);
            }
        });

        btnClear_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                chooseCategory2.setSelection(0);
                etSearrchItem_.setText("Item Name or Item Key");

                tvIDate_.setText("Item Date: dd/mm/yy");
                tvIName_.setText("Item Name: Name");
                tvIKey_.setText("Item Key: Key");

            }
        });

        return v;
    }

    public void SetUpSpinner(){
        ArrayList<String> options2=new ArrayList<String>();
        if(Singleton.getInstance() != null)
        {
            options2 = new ArrayList<String>(Singleton.getInstance().getCategoryNames());
            //ArrayList<String> data = savedInstanceState.getStringArrayList("itemsArrayList");
            //options = data;
        }
        else
        {
            options2 = new ArrayList<String>(new ArrayList<>());
        }
        //options = new ArrayList<>(Singleton.getInstance().getCategories());



        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,options2);
        adapter2.notifyDataSetChanged();
        chooseCategory2.setAdapter(adapter2);


    }

    public Item findItem(String i){
        Item item = new Item();
        ArrayList<Item> tempList = Singleton.getInstance().Catagories.get(chooseCategory2.getSelectedItem().toString());
        for(Item it : tempList){
            if(it.Name.equals(i)||it.key.equals(i))
            {
                item = it;
                break;
            }
            else
            {
                item  = new Item("Key","Name","dd/mm/yy");
            }
        }
        return  item;
    }

}
