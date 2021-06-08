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

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                    }
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                            return true;
                        }
            };

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