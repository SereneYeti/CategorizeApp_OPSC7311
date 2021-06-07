package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CreateCategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "CreateItem";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View v;
    private TextView tv_RecieveText2;
    public Button btn_Capture;
    public Button btn_Create;
    public EditText et_ItemName;
    public EditText et_GoalAmount;
    ImageView iv_CategoryPicture;
    Spinner mSpinner;

    public CreateCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateCategoryFragment newInstance(String param1, String param2) {
        CreateCategoryFragment fragment = new CreateCategoryFragment();
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

        v = inflater.inflate(R.layout.fragment_categories,container,false);
        tv_RecieveText2 = v.findViewById(R.id.tvRecieveText2);

        btn_Capture = v.findViewById(R.id.btnCapture);
        btn_Create = v.findViewById(R.id.btnCreateItem);
        iv_CategoryPicture = v.findViewById(R.id.ivCategoryPicture);
        et_ItemName = v.findViewById(R.id.etItemName);
        //et_GoalAmount = v.findViewById(R.id.etGoalNumItems);

        btn_Capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 5);
            }
        });
        btn_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Item item = new Item(mSpinner.getSelectedItem().toString(),et_ItemName.getText().toString(),0,iv_CategoryPicture);


                    //Log.i(TAG, mSpinner.getSelectedItem().toString());

                }
                catch (NumberFormatException nfe){
                    System.out.println("could not parse " + nfe);
                }

                tv_RecieveText2.setText("The Item has been created!");

            }
        });

        mSpinner = v.findViewById(R.id.spinner);

        ArrayList<String> options=new ArrayList<String>();

        if(savedInstanceState != null)
        {
            ArrayList<String> data = savedInstanceState.getStringArrayList("itemsArrayList");
            options = data;
        }
        else
        {
            options = new ArrayList<String>(Singleton.getInstance().getCategories());
        }
        //options = new ArrayList<>(Singleton.getInstance().getCategories());



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,options);
        adapter.notifyDataSetChanged();
        mSpinner.setAdapter(adapter);



        if(((MainActivity)getActivity()).getTestVar() != null)
        {
            tv_RecieveText2.setText(((MainActivity)(getActivity())).getTestVar());
        }

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode != 0)
        {
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            iv_CategoryPicture.setImageBitmap(bitmap);
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }
}
