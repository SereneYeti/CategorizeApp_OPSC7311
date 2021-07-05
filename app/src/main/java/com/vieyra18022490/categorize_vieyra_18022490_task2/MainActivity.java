package com.vieyra18022490.categorize_vieyra_18022490_task2;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public String testVar;
    Runnable testRunnable;
    Handler testHandler = new Handler();



    public String getTestVar() {
        return testVar;
    }

    public void setTestVar(String testVar) {
        this.testVar = testVar;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottonNav = findViewById(R.id.bottom_navigation);
        bottonNav.setOnNavigationItemSelectedListener(navListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        FirebaseApp.initializeApp(this);
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
    public static void downloadList(){
        FirebaseDatabase database = MainActivity.getFirebaseDatabase();
        DatabaseReference myRef = database.getReference().child("Category Name");
        ArrayList<String> testGoals = new ArrayList<>();
        ArrayList<String> testNames = new ArrayList<>();


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    testGoals.clear();
                    testNames.clear();
                    for(DataSnapshot dss: snapshot.getChildren())
                    {
                        Log.i(TAG, "onClick: " + dss.getValue());
                        String catName = dss.getValue().toString();
                        testNames.add(catName);
                        for(DataSnapshot s : dss.getChildren()){

                            String goals = dss.child(s.getKey()).getValue().toString();
                            testGoals.add(goals);
                        }

                    }


                    for(int i = 0; i < testGoals.size();i++){
                        Singleton.getInstance().stringBuilder.append("NAME: " +testNames.get(i)+ ",");
                        Singleton.getInstance().stringBuilder.append("Goal: " +testGoals.get(i)+ ",");
                    }

                }
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