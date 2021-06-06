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

public class AddItemsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView tv_RecieveText;
    private Button btn_GetInfo;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View v;

    public AddItemsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddItemsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddItemsFragment newInstance(String param1, String param2) {
        AddItemsFragment fragment = new AddItemsFragment();
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

        v = inflater.inflate(R.layout.fragment_add,container,false);


        tv_RecieveText = v.findViewById(R.id.tvRecieveText);
        btn_GetInfo = v.findViewById(R.id.btnGetInfo);
        //Bundle bundle = this.getArguments();
        Bundle bundle = getArguments();
        if(bundle != null)
        {
            String data = bundle.getString("key");
            ((MainActivity)(getActivity())).setTestVar(data);
            tv_RecieveText.setText(((MainActivity)(getActivity())).getTestVar());
        }
        btn_GetInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });


        return v;
    }


}
