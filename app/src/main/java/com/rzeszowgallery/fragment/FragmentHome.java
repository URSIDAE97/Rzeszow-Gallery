package com.rzeszowgallery.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.rzeszowgallery.R;

public class FragmentHome extends Fragment {

    private NavigationView navigationView;

    // --- Create --- //

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.home_toolbar_title);
        navigationView = getActivity().findViewById(R.id.nav_menu);
        CardButtonsControl(view);

        return view;
    }

    // --- Card buttons handler --- //

    private void CardButtonsControl(View view) {
        CardView card_square = view.findViewById(R.id.card_square);
        CardView card_castle = view.findViewById(R.id.card_castle);
        CardView card_center = view.findViewById(R.id.card_center);
        CardView card_other = view.findViewById(R.id.card_other);

        card_square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uncheckAllItems(navigationView.getMenu());
                navigationView.getMenu().getItem(1).getSubMenu().getItem(0).setChecked(true);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentGallerySquare()).commit();
            }
        });

        card_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uncheckAllItems(navigationView.getMenu());
                navigationView.getMenu().getItem(1).getSubMenu().getItem(1).setChecked(true);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentGalleryCenter()).commit();
            }
        });

        card_castle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uncheckAllItems(navigationView.getMenu());
                navigationView.getMenu().getItem(1).getSubMenu().getItem(2).setChecked(true);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentGalleryCastle()).commit();
            }
        });

        card_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uncheckAllItems(navigationView.getMenu());
                navigationView.getMenu().getItem(1).getSubMenu().getItem(3).setChecked(true);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentGalleryOther()).commit();
            }
        });
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
}
