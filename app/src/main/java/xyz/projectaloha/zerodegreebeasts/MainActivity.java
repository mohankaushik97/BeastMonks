package xyz.projectaloha.zerodegreebeasts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import xyz.projectaloha.zerodegreebeasts.Database.BeastDatabase;
import xyz.projectaloha.zerodegreebeasts.Fragments.CatelogFragment;
import xyz.projectaloha.zerodegreebeasts.Fragments.HomeFragment;
import xyz.projectaloha.zerodegreebeasts.Fragments.TimerFragment;

public class MainActivity extends AppCompatActivity {

    public BeastDatabase dbMain;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context mContext = this;

        dbMain = BeastDatabase.getInstance(mContext);

        BottomNavigationView navView = findViewById(R.id.bottomNavView);

        navView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.catelog:
                            selectedFragment = new CatelogFragment();
                            break;

                        case R.id.home:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.timer:
                            selectedFragment = new TimerFragment();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };

}