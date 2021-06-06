package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CreateCategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View v;
    private TextView tv_RecieveText2;
    public Button btn_Capture;
    public Button btn_Create;
    public EditText et_CategoryName;
    public EditText et_GoalAmount;
    ImageView iv_CategoryPicture;

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
        btn_Create = v.findViewById(R.id.btnCreate);
        iv_CategoryPicture = v.findViewById(R.id.ivCategoryPicture);
        et_CategoryName = v.findViewById(R.id.etCategoryName);
        et_GoalAmount = v.findViewById(R.id.etGoalNumItems);

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
                //ArrayList<Category> tempList = Singleton.getInstance().getCategoryList();
                List<String> tempNamesList = Singleton.getInstance().getCategories();
                List<Integer> tempGoalsList = Singleton.getInstance().getGoals();
                //ArrayList<Item> tempItemList = new ArrayList<Item>();
                int goal = 0;
                try {
                    goal = Integer.parseInt(et_GoalAmount.getText().toString());
                    tempGoalsList.add(goal);
                    tempNamesList.add(et_CategoryName.getText().toString());
                }
                catch (NumberFormatException nfe){
                    System.out.println("could not parse " + nfe);
                }
                //Category newCategory = new Category(et_CategoryName.getText().toString() + tempList.size()+1,et_CategoryName.getText().toString(),goal,iv_CategoryPicture ,tempItemList);
                //tempList.add(newCategory);
                //Singleton.getInstance().setCategoryList(tempList);
                tv_RecieveText2.setText("The category has been created!");
                Singleton.getInstance().setCategories(tempNamesList);
            }
        });

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
