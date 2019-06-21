package com.rzeszowgallery.fragment;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rzeszowgallery.listiner.OnSwipeTouchListener;
import com.rzeszowgallery.R;
import com.rzeszowgallery.recyclerView.RecyclerAdapter;

public class FragmentGalleryCastle extends Fragment {

    private Button btn_first, btn_left, btn_right, btn_last;
    private int img_index = 0;
    private ImageView img;
    private TextView txt;
    private Resources res;
    private View view;
    private LinearLayout gridLayout;
    private LinearLayout singleLayout;
    private int[] images = { R.drawable.castle_gallery_1,
            R.drawable.castle_gallery_2,
            R.drawable.castle_gallery_3,
            R.drawable.castle_gallery_4,
            R.drawable.castle_gallery_5,
            R.drawable.castle_gallery_6,
            R.drawable.castle_gallery_7,
            R.drawable.castle_gallery_8,
            R.drawable.castle_gallery_9,
            R.drawable.castle_gallery_10 };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gallery_castle, container, false);
        gridLayout = view.findViewById(R.id.gallery_castle_grid);
        singleLayout = view.findViewById(R.id.gallery_castle_single);
        img = view.findViewById(R.id.castle_img);
        txt = view.findViewById(R.id.castle_txt);
        res = getResources();
        txt.setText(res.getString(R.string.castle_img_counter, img_index + 1, images.length));

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.castle_toolbar_title);
        setHasOptionsMenu(true);
        // set recycler view
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(images, img, txt, res, gridLayout, singleLayout);
        recyclerView.setAdapter(recyclerAdapter);
        // set navigation control
        ButtonsControl();
        SwipeControl();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_galleries, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_grid:
                singleLayout.setVisibility(View.GONE);
                gridLayout.setVisibility(View.VISIBLE);
                return true;
            case R.id.action_single:
                gridLayout.setVisibility(View.GONE);
                singleLayout.setVisibility(View.VISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void ButtonsControl() {
        btn_first = view.findViewById(R.id.castle_btn_first);
        btn_left = view.findViewById(R.id.castle_btn_left);
        btn_right = view.findViewById(R.id.castle_btn_right);
        btn_last = view.findViewById(R.id.castle_btn_last);

        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_index = 0;
                img.setImageResource(images[img_index]);
                img.setTag(img_index);
                txt.setText(res.getString(R.string.castle_img_counter, img_index + 1, images.length));
            }
        });

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_index --;
                if(img_index < 0) img_index = images.length - 1;
                img.setImageResource(images[img_index]);
                img.setTag(img_index);
                txt.setText(res.getString(R.string.castle_img_counter, img_index + 1, images.length));
            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_index ++;
                img_index %= images.length;
                img.setImageResource(images[img_index]);
                img.setTag(img_index);
                txt.setText(res.getString(R.string.castle_img_counter, img_index + 1, images.length));
            }
        });

        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_index = images.length - 1;
                img.setImageResource(images[img_index]);
                img.setTag(img_index);
                txt.setText(res.getString(R.string.castle_img_counter, img_index + 1, images.length));
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void SwipeControl() {
        img.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeLeft() { btn_right.callOnClick(); }

            @Override
            public void onSwipeRight() { btn_left.callOnClick(); }
        });
    }
}
