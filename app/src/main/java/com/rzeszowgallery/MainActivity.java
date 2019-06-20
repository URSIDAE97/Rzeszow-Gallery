package com.rzeszowgallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.rzeszowgallery.fragment.FragmentGalleryCastle;
import com.rzeszowgallery.fragment.FragmentGalleryCenter;
import com.rzeszowgallery.fragment.FragmentGalleryOther;
import com.rzeszowgallery.fragment.FragmentGallerySquare;
import com.rzeszowgallery.fragment.FragmentHome;
import com.rzeszowgallery.fragment.FragmentLinks;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    // --- Create --- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    // --- Drawer menu handler --- //

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentHome()).commit();
                break;
            case R.id.nav_gallery_square:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentGallerySquare()).commit();
                break;
            case R.id.nav_gallery_center:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentGalleryCenter()).commit();
                break;
            case R.id.nav_gallery_castle:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentGalleryCastle()).commit();
                break;
            case R.id.nav_gallery_other:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentGalleryOther()).commit();
                break;
            case R.id.nav_links:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentLinks()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
                Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // --- Phone buttons handler --- //

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
