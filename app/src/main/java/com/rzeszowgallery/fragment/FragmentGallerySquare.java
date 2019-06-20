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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.rzeszowgallery.listiner.OnSwipeTouchListener;
import com.rzeszowgallery.R;

public class FragmentGallerySquare extends Fragment {

    private Button btn_first, btn_left, btn_right, btn_last;
    private int img_index = 0;
    private ImageView img;
    private TextView txt;
    private Resources res;
    private int[] images = { R.drawable.square_gallery_1,
                            R.drawable.square_gallery_2,
                            R.drawable.square_gallery_3,
                            R.drawable.square_gallery_4,
                            R.drawable.square_gallery_5 };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_square, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.square_toolbar_title);
        setHasOptionsMenu(true);
        img = view.findViewById(R.id.square_img);
        txt = view.findViewById(R.id.square_txt);
        res = getResources();
        txt.setText(res.getString(R.string.square_img_counter, img_index + 1, images.length));
        ButtonsControl(view);
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
                Toast.makeText(getActivity(), "grid", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void ButtonsControl(View view) {
        btn_first = view.findViewById(R.id.square_btn_first);
        btn_left = view.findViewById(R.id.square_btn_left);
        btn_right = view.findViewById(R.id.square_btn_right);
        btn_last = view.findViewById(R.id.square_btn_last);

        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_index = 0;
                img.setImageResource(images[img_index]);
                txt.setText(res.getString(R.string.square_img_counter, img_index + 1, images.length));
            }
        });

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_index --;
                if(img_index < 0) img_index = images.length - 1;
                img.setImageResource(images[img_index]);
                txt.setText(res.getString(R.string.square_img_counter, img_index + 1, images.length));
            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_index ++;
                img_index %= images.length;
                img.setImageResource(images[img_index]);
                txt.setText(res.getString(R.string.square_img_counter, img_index + 1, images.length));
            }
        });

        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_index = images.length - 1;
                img.setImageResource(images[img_index]);
                txt.setText(res.getString(R.string.square_img_counter, img_index + 1, images.length));
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
