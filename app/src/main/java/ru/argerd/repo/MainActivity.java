package ru.argerd.repo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private BottomNavigationView bottomNavigation;
    private NavController navController;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "inOnCreate");
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        bottomNavigation = findViewById(R.id.bottom_navigation_view);
        bottomNavigation.getMenu().getItem(2).setChecked(true);

        fab = findViewById(R.id.help_button);
        fab.setOnClickListener((view) -> {
            Log.d(TAG, "fab clicked");
            navController.navigate(R.id.categoriesOfHelpFragment);
            bottomNavigation.getMenu().getItem(2).setChecked(true);
        });

        bottomNavigation.setOnNavigationItemSelectedListener((menuItem) -> {
            switch (menuItem.getItemId()) {
                case R.id.help_item:
                    fab.callOnClick();
                    break;
                case R.id.item_profile:
                    navController.navigate(R.id.profileFragment);
                    break;
                case R.id.search_item:
                    navController.navigate(R.id.searchFragment);
                    break;
                case R.id.news_item:
                    navController.navigate(R.id.newsFragment);
                    break;
            }

            return true;
        });
    }

    @Override
    public void onBackPressed() {
        bottomNavigation.getMenu().getItem(2).setChecked(true);
        super.onBackPressed();
    }
}
