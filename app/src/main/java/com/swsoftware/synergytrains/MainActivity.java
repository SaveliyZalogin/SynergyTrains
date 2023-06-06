package com.swsoftware.synergytrains;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Ticket> tickets = new ArrayList<>();
    Toolbar toolbar;
    MenuItem themeItem;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TabLayout tabLayout;

    FloatingActionButton buyFab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tickets.add(new Ticket(1, "asd", "qwe", "12.06.2023 12:00", null, 3000));

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        tabLayout = findViewById(R.id.tabLayout);
        buyFab = findViewById(R.id.buyFab);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, new MyTicketsFragment())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();

    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    //    @Override
//    protected void onNightModeChanged(int mode) {
//        super.onNightModeChanged(mode);
//        if (mode == AppCompatDelegate.MODE_NIGHT_NO) {
//            themeItem.setIcon(R.drawable.baseline_dark_mode_24);
//        } else {
//            themeItem.setIcon(R.drawable.baseline_light_mode_24);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        themeItem = menu.findItem(R.id.theme);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            themeItem.setIcon(R.drawable.baseline_dark_mode_24);
        } else {
            themeItem.setIcon(R.drawable.baseline_light_mode_24);
        }
        return super.onCreateOptionsMenu(menu);
    }

//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.theme:
//                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                }
//                break;
//            case R.id.language:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}