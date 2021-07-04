package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private TextView tv_RecieveText3;
    Button btnPublish_;

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
        tv_RecieveText3 = v.findViewById(R.id.tvRecieveText3);

        if(Singleton.getInstance().getTestVar2() != null)
        {
            tv_RecieveText3.setText(Singleton.getInstance().getTestVar2());
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://categorize-app-poe-prototype-default-rtdb.europe-west1.firebasedatabase.app/");
        btnPublish_ = v.findViewById(R.id.btnPublish);

        Log.i(TAG, "HELLO! ");
        btnPublish_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference myRef = database.getReference("message");
                myRef.setValue("Goodbye, Cruel World!");
                Log.i(TAG, "onClick: ");
            }
        });

        return v;
    }
}
