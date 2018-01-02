package com.kgames.james.nerdybook;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AdventureSheetActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar mToolbar;

    String mHeroID;
    int mCurrentChapter;
    int mTotalChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_sheet);

        mHeroID = getIntent().getExtras().getString("HeroID");
        mCurrentChapter = getIntent().getExtras().getInt("CurrentChapter");
        mTotalChapters = getIntent().getExtras().getInt("TotalChapters");

        // Toolbar
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.nav_adventure_sheet));
        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(mToolbar);

        // Drawer Menu
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_adventure_sheet).setVisible(false);
        View headerview = navigationView.getHeaderView(0);
        headerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdventureTalisman.class));
            }
        });

    }


    // Drawer Menu
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_adventure_sheet) {
            Intent intent = new Intent(getApplicationContext(), AdventureSheetActivity.class);
            intent.putExtra("HeroID", String.valueOf(mHeroID));
            intent.putExtra("CurrentChapter", mCurrentChapter);
            intent.putExtra("TotalChapters", mTotalChapters);
            startActivity(intent);
        } else if (id == R.id.nav_current_adventure) {
            Intent intent = new Intent(getApplicationContext(), AdventureTalisman.class);
            intent.putExtra("HeroID", String.valueOf(mHeroID));
            intent.putExtra("CurrentChapter", mCurrentChapter);
            intent.putExtra("TotalChapters", mTotalChapters);
            startActivity(intent);
        } else if (id == R.id.nav_statistics) {
            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            startActivity(intent);
        } else if (id == R.id.nav_menu) {
            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
