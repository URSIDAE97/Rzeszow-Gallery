package com.rzeszowgallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.rzeszowgallery.fragment.FragmentGalleryCastle;
import com.rzeszowgallery.fragment.FragmentGalleryCenter;
import com.rzeszowgallery.fragment.FragmentGalleryOther;
import com.rzeszowgallery.fragment.FragmentGallerySquare;
import com.rzeszowgallery.fragment.FragmentHome;
import com.rzeszowgallery.fragment.FragmentLinks;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;

    // --- Create --- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();
        }
    }

    // --- Drawer menu handler --- //

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_home:
                uncheckAllItems(navigationView.getMenu());
                menuItem.setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentHome()).commit();
                break;
            case R.id.nav_gallery_square:
                uncheckAllItems(navigationView.getMenu());
                menuItem.setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentGallerySquare()).commit();
                break;
            case R.id.nav_gallery_center:
                uncheckAllItems(navigationView.getMenu());
                menuItem.setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentGalleryCenter()).commit();
                break;
            case R.id.nav_gallery_castle:
                uncheckAllItems(navigationView.getMenu());
                menuItem.setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentGalleryCastle()).commit();
                break;
            case R.id.nav_gallery_other:
                uncheckAllItems(navigationView.getMenu());
                menuItem.setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentGalleryOther()).commit();
                break;
            case R.id.nav_links:
                uncheckAllItems(navigationView.getMenu());
                menuItem.setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentLinks()).commit();
                break;
            case R.id.nav_exit:
                finish();
                moveTaskToBack(true);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void uncheckAllItems(Menu menu) {
        MenuItem item;
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            item = menu.getItem(i);
            if(item.hasSubMenu()) { uncheckAllItems(item.getSubMenu()); }
            else { item.setChecked(false); }
        }
    }

    // --- Toolbar menu handler --- //

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_exit:
                finish();
                moveTaskToBack(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // --- Phone buttons handler --- //

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(!(fragment instanceof FragmentHome)) {
            uncheckAllItems(navigationView.getMenu());
            navigationView.getMenu().getItem(0).setChecked(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FragmentHome()).commit();
        } else {
            super.onBackPressed();
        }
    }
}
