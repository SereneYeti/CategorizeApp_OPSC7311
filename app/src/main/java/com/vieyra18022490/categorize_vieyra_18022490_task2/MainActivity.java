package com.vieyra18022490.categorize_vieyra_18022490_task2;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileDescriptor;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public String testVar;
    Runnable testRunnable;
    Handler testHandler = new Handler();
    static Context context;

    public String getTestVar() {
        return testVar;
    }

    public void setTestVar(String testVar) {
        this.testVar = testVar;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        Log.i(TAG,"ELLLLOOOENFEOIS"+context);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        BottomNavigationView bottonNav = findViewById(R.id.bottom_navigation);
        bottonNav.setOnNavigationItemSelectedListener(navListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListner = item -> {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.nav_home:
                selectedFragment = new HomeFragment();
                break;
            //case R.id.nav_add:
                //selectedFragment = new AddItemsFragment();
                //break;
            case R.id.nav_AddItem:
                selectedFragment = new CreateCategoryFragment();
                break;
            case R.id.nav_search:
                selectedFragment = new SearchFragment();
                break;
            case R.id.nav_showGraph:
                selectedFragment = new GraphFragment();
                break;
        }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                return true;
            };
    public static FirebaseDatabase getFirebaseDatabase() {
        return FirebaseDatabase.getInstance("https://categorize-app-poe-prototype-default-rtdb.europe-west1.firebasedatabase.app/");
    }
    public static Bitmap getBitmapFromUri(Context context, Uri uri) {
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            parcelFileDescriptor =
                    context.getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();
            return image;
        } catch (Exception e) {
            Log.e(TAG, "Failed to load image.", e);
            return null;
        } finally {
            try {
                if (parcelFileDescriptor != null) {
                    parcelFileDescriptor.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Error closing ParcelFile Descriptor");
            }
        }
    }
    public void downloadList(){
        FirebaseDatabase database = MainActivity.getFirebaseDatabase();
        DatabaseReference myRef = database.getReference().child("Category Name");
        ArrayList<String> testGoals = new ArrayList<>();
        ArrayList<String> testNames = new ArrayList<>();
        Hashtable<String,ArrayList<Item>> tempDict = new Hashtable<>();


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    int count = 0;
                    int dCnt = -1;
                    int startCount = (int)snapshot.getChildrenCount();
                    //Log.i(TAG,"COUNT: " + String.valueOf(startCount));
                    testGoals.clear();
                    testNames.clear();

                    for(DataSnapshot dss: snapshot.getChildren())
                    {

                        //namestart
                        String catName = new String();
                        if(count < startCount/2){
                            //Log.i(TAG, "DSS VALUE: " + dss.getValue());
                            catName = dss.getValue().toString();
                            testNames.add(catName);
                            tempDict.put(catName, new ArrayList<>());
                            Collator.getInstance().setStrength(Collator.TERTIARY);
                            testNames.sort(Collator.getInstance());
                        }

                        if(count >= startCount/2)
                        {
                            int innnerCount = 0;
                            for(DataSnapshot dss2 : dss.getChildren())
                            {
                                //goals start
                                String goals = new String();
                                Item item = new Item();

                                if(innnerCount == 0)
                                {
                                    goals = dss2.getValue().toString();
                                }
                                if(innnerCount == 1){

                                    if(dss2.getKey().equals("Items"))
                                    {
                                        for(DataSnapshot dss3: dss2.getChildren())
                                        {
                                            //items start

                                            String key =  new String();
                                            String name =  new String();
                                            String date =  new String();
                                            String uri;
                                            key = dss3.getKey();
                                            if(key.contains("Item0"))
                                            {
                                                dCnt++;
                                            }
                                            if(dss3.getChildrenCount() > 0)
                                            {
                                                name = dss3.child("Name").getValue().toString();
                                                date = dss3.child("Date").getValue().toString();
                                                uri = dss3.child("Uri").getValue().toString();


                                                //testItems.add(new Item(key,name,date));

                                                tempDict.get(testNames.get(dCnt)).add(new Item(key,name,date,uri));
                                            }
                                        }

                                    }
                                }
                                innnerCount++;

                                if(!goals.equals(""))
                                {
                                    testGoals.add(goals);
                                }
                                for(int g = 0; g < goals.length();g++){
                                    Log.i(TAG,"Goals:"  + testGoals.get(g) + ",");
                                }
                            }
                        }
                        count++;
                        Log.i(TAG,"COUNT: " + String.valueOf(count));
                    }
                }
                Singleton.getInstance().Catagories = tempDict;
                Singleton.getInstance().categoryNames = testNames;
                //testNames.clear();
                for(int i = 0; i < testGoals.size();i++){
                    try{
                        Singleton.getInstance().Goals.add(Integer.parseInt(testGoals.get(i)));
                    } catch (NumberFormatException numberFormatException){
                        Log.e(TAG,"Error: " + numberFormatException);

                    }
                }
                //testGoals.clear();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



//    public void OpenItemsListFragment(String key, int goal)
//    {
//        try {
//            Fragment selectedFragment = null;
//
//            //Log.i(TAG, "I'm in!");
//            //Log.i(TAG, "SIZE: " + Singleton.getInstance().Catagories.size());
//            //Log.i(TAG, "KEY: " + key);
//
//            //Log.i(TAG, String.valueOf(Singleton.getInstance().Catagories.containsKey(key)));
//            if(Singleton.getInstance().Catagories.containsKey(key))
//            {
//                selectedFragment = new HandleItemsList();
//
//                //selectedFragment;
//
//                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).setPrimaryNavigationFragment(this.getSupportFragmentManager().getPrimaryNavigationFragment()).commit();
//
//                //setContentView(R.layout.activity_items_list);

//           }

//       }
//       catch (Exception e)
//       {
//           Log.e(TAG, "Error: " + e);
//           e.printStackTrace();
//       }
//   }

//   public void setTestRunnable(String key, int goal)
//   {
//       testRunnable = new Runnable() {
//           @Override
//           public void run() {
//               OpenItemsListFragment(key, goal);
//           }
//       };
//       new Thread(testRunnable).start();
//   }
}