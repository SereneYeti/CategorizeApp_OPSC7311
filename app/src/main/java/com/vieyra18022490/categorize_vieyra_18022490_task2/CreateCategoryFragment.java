package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

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
    //private TextView tv_RecieveText2;
    public Button btn_Capture;
    public Button btn_Create;
    public EditText et_ItemName;
    public EditText et_GoalAmount;
    ImageView iv_CategoryPicture;
    Spinner mSpinner;
    public EditText et_ItemDate;
    private Bitmap bitmap;
    private DatabaseReference root_Images = MainActivity.getFirebaseDatabase().getReference();
    private StorageReference reference = FirebaseStorage.getInstance("gs://categorize-app-poe-prototype.appspot.com").getReference();

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
        //tv_RecieveText2 = v.findViewById(R.id.tvRecieveText2);

        btn_Capture = v.findViewById(R.id.btnCapture);
        btn_Create = v.findViewById(R.id.btnCreateItem);
        iv_CategoryPicture = v.findViewById(R.id.ivCategoryPicture);
        et_ItemName = v.findViewById(R.id.etItemName);
        et_ItemDate = v.findViewById(R.id.etItemDate);
        //et_GoalAmount = v.findViewById(R.id.etGoalNumItems);

        btn_Capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 5);
            }
        });
        btn_Create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                try {

                    Context context = getContext();
                    String tempDate = et_ItemDate.getText().toString();
                    Item item;


                    if(et_ItemName.getText().equals(""))
                    {
                        Toast.makeText(context,"Enter an item name.", Toast.LENGTH_LONG).show();
                    }
                    else if(mSpinner.getSelectedItem() == null)
                    {
                        Toast.makeText(context,"Choose or create a catagory.", Toast.LENGTH_LONG).show();
                    }
                    else if(iv_CategoryPicture.getDrawable() == null)
                    {
                        Toast.makeText(context,"Take a photo to go with your item!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if(tempDate.contains("dd/mm/yyyy"))
                        {
                            String x = String.valueOf(Singleton.getInstance().Catagories.get(mSpinner.getSelectedItem().toString()).size());

                            item = new Item("Item" + x,et_ItemName.getText().toString(),0,bitmap);
                            Toast.makeText(context,"Item Created!", Toast.LENGTH_LONG).show();
                            Log.i(TAG,"HERE1: " + mSpinner.getSelectedItem().toString());
                            try {
                                if(Singleton.getInstance().Catagories.containsKey(mSpinner.getSelectedItem().toString())){
                                    ArrayList<Item> tempItems = Singleton.getInstance().Catagories.get(mSpinner.getSelectedItem().toString());
                                    Log.i(TAG,"HERE3: " + mSpinner.getSelectedItem().toString());
                                    item.itemNum = tempItems.size();
                                    tempItems.add(tempItems.size(),item);
                                    Singleton.getInstance().Catagories.replace(mSpinner.getSelectedItem().toString(),tempItems);
                                    Toast.makeText(context,"Item Saved!", Toast.LENGTH_LONG).show();

                                }
                            }
                            catch (Exception e)
                            {
                                Log.e(TAG, e.toString());
                            }
                            et_ItemName.setText("Item Name");
                            mSpinner.setSelection(0);
                            iv_CategoryPicture.setImageBitmap(null);
                            uploadList();
                        }
                        else
                        {
                            String x = String.valueOf(Singleton.getInstance().Catagories.get(mSpinner.getSelectedItem().toString()).size());
                            item = new Item("Item" + x,et_ItemName.getText().toString(),0,bitmap, tempDate);
                            Toast.makeText(context,"Item Created!", Toast.LENGTH_SHORT).show();
                            Log.i(TAG,"HERE2: " + mSpinner.getSelectedItem().toString());
                            try {
                                if(Singleton.getInstance().Catagories.containsKey(mSpinner.getSelectedItem().toString())){
                                    ArrayList<Item> tempItems = Singleton.getInstance().Catagories.get(mSpinner.getSelectedItem().toString());
                                    Log.i(TAG,"HERE3: " + mSpinner.getSelectedItem().toString());
                                    item.itemNum = tempItems.size();
                                    tempItems.add(tempItems.size(),item);
                                    Singleton.getInstance().Catagories.replace(mSpinner.getSelectedItem().toString(),tempItems);
                                    Toast.makeText(context,"Item Saved!", Toast.LENGTH_SHORT).show();

                                }
                            }
                            catch (Exception e)
                            {
                                Log.e(TAG, e.toString());
                            }
                            et_ItemName.setText("Item Name");
                            mSpinner.setSelection(0);
                            iv_CategoryPicture.setImageBitmap(null);
                            et_ItemDate.setText("dd/mm/yyyy");
                            uploadList();
                        }


                    }

                    //Log.i(TAG, mSpinner.getSelectedItem().toString());

                }
                catch (NumberFormatException nfe){
                    System.out.println("could not parse " + nfe);
                }

                //tv_RecieveText2.setText("The Item has been created!");

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
            options = new ArrayList<String>(Singleton.getInstance().getCategoryNames());
        }
        //options = new ArrayList<>(Singleton.getInstance().getCategories());



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,options);
        adapter.notifyDataSetChanged();
        mSpinner.setAdapter(adapter);



        //if(((MainActivity)getActivity()).getTestVar() != null)
        {
            //tv_RecieveText2.setText(((MainActivity)(getActivity())).getTestVar());
        }

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode != 0)
        {
            bitmap = (Bitmap)data.getExtras().get("data");
            iv_CategoryPicture.setImageBitmap(bitmap);
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }

    public void uploadList(){
        FirebaseDatabase database = MainActivity.getFirebaseDatabase();
        DatabaseReference myRef = database.getReference().child("Category Name");
        myRef.removeValue();

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

        for(int i = 0; i < Singleton.getInstance().Catagories.size(); i++){
            DatabaseReference myRef = database.getReference().child("Category Name").child(Singleton.getInstance().categoryNames.get(i)).child("Items");
            for(int j =0; j < Singleton.getInstance().Catagories.get(Singleton.getInstance().categoryNames.get(i)).size(); j ++){
                Item item = Singleton.getInstance().Catagories.get(Singleton.getInstance().categoryNames.get(i)).get(j);
                String key = item.key;
                String name = item.getName();
                String date = item.getDate();
                myRef = database.getReference().child("Category Name").child(Singleton.getInstance().categoryNames.get(i)).child("Items").child("Key");
                myRef.setValue(key);
                myRef = database.getReference().child("Category Name").child(Singleton.getInstance().categoryNames.get(i)).child("Items").child(key).child("Name");
                myRef.setValue(name);
                myRef = database.getReference().child("Category Name").child(Singleton.getInstance().categoryNames.get(i)).child("Items").child(key).child("Date");
                myRef.setValue(date);

            }

        }
    }

    private void UploadUriToFirebase(Uri uri) {
        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Toast.makeText(v.getContext(),"Upload Success",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(v.getContext(),"Upload Failed",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private String getFileExtension(Uri mUri) {
        ContentResolver cr = v.getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

    public Uri getImageUri(Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(v.getContext().getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }



    private void SetDatabaseValue(String path, String value) {
        FirebaseDatabase database = MainActivity.getFirebaseDatabase();
        DatabaseReference myRef = database.getReference(path);
        //Log.i(TAG, "onClick: " + myRef.getKey());
        myRef.setValue(value);
    }
}
