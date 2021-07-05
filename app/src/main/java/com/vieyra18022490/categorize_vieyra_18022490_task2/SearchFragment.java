package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private TextView tv_RecieveText3;
    Button btnPublish_;
    Button btnTest;

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

        btnPublish_ = v.findViewById(R.id.btnPublish);

        Log.i(TAG, "HELLO! ");
        btnPublish_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SetDatabaseValue("Category Name", "Named Category");
                //SetDatabaseValue("Goal", "10");
                //SetDatabaseValue("message", " Test message, from cruel cruel reality!");
                //Log.i(TAG, "onClick: ");
                uploadList();
            }
        });

        btnTest = v.findViewById(R.id.btnDisplay);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.downloadList();
                Toast.makeText(v.getContext(),Singleton.getInstance().stringBuilder.toString(),Toast.LENGTH_LONG).show();
                Singleton.getInstance().stringBuilder.replace(0, Singleton.getInstance().stringBuilder.length(),"");
            }
        });

        return v;
    }

    public void uploadList(){
        FirebaseDatabase database = MainActivity.getFirebaseDatabase();
        DatabaseReference myRef = database.getReference().child("Category Name");
        //Log.i(TAG, "onClick: " + myRef.getKey();
        myRef.setValue(Singleton.getInstance().getCategoryNames()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(v.getContext(),"List Uploaded Successfully!",Toast.LENGTH_LONG).show();
                    addGoals();
                    addItems();
                }
            }
        });
    }

    public void addGoals(){
        FirebaseDatabase database = MainActivity.getFirebaseDatabase();

         for(int i = 0; i < Singleton.getInstance().getCategoryNames().size(); i++){
            DatabaseReference myRef = database.getReference().child("Category Name").child(Singleton.getInstance().categoryNames.get(i)).child("Goal");
            myRef.setValue(Singleton.getInstance().Goals.get(i));
        }
    }

    public void addItems(){
        FirebaseDatabase database = MainActivity.getFirebaseDatabase();

        for(int i = 0; i < Singleton.getInstance().getCategoryNames().size(); i++){
            DatabaseReference myRef = database.getReference().child("Category Name").child(Singleton.getInstance().categoryNames.get(i)).child("Items");
            for(int j =0; j < Singleton.getInstance().categoryNames.get(i).length(); j ++){
                Item item = Singleton.getInstance().Catagories.get(Singleton.getInstance().categoryNames.get(i)).get(j);
                String key = item.key;
                String name = item.getName();
                String date = item.getDate();
                myRef = database.getReference().child("Category Name").child(Singleton.getInstance().categoryNames.get(i)).child("Items").child("Key");
                myRef.setValue(key);
                myRef = database.getReference().child("Category Name").child(Singleton.getInstance().categoryNames.get(i)).child("Items").child("Name");
                myRef.setValue(name);
                myRef = database.getReference().child("Category Name").child(Singleton.getInstance().categoryNames.get(i)).child("Items").child("Date");
                myRef.setValue(date);
            }

        }
    }





    private void SetDatabaseValue(String path, String value) {
        FirebaseDatabase database = MainActivity.getFirebaseDatabase();
        DatabaseReference myRef = database.getReference(path);
        //Log.i(TAG, "onClick: " + myRef.getKey());
        myRef.setValue(value);
    }


}
