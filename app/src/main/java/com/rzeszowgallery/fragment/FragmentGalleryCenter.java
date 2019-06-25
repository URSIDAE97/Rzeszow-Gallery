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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
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

import java.util.Timer;
import java.util.TimerTask;

public class FragmentGalleryCenter extends Fragment implements AdapterView.OnItemSelectedListener {

    private Button btn_first, btn_left, btn_right, btn_last;
    private int img_index = 0;
    private ImageView img;
    private TextView txt;
    private Resources res;
    private View view;
    private LinearLayout gridLayout;
    private LinearLayout singleLayout;
    private Spinner timeList;
    private Switch slideSwitch;
    private int time;
    private Timer timer;
    private int[] images = { R.drawable.center_gallery_1,
            R.drawable.center_gallery_2,
            R.drawable.center_gallery_3,
            R.drawable.center_gallery_4,
            R.drawable.center_gallery_5,
            R.drawable.center_gallery_6,
            R.drawable.center_gallery_7,
            R.drawable.center_gallery_8,
            R.drawable.center_gallery_9,
            R.drawable.center_gallery_10 };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gallery_center, container, false);
        gridLayout = view.findViewById(R.id.gallery_center_grid);
        singleLayout = view.findViewById(R.id.gallery_center_single);
        timeList = view.findViewById(R.id.gallery_time_list);
        slideSwitch = view.findViewById(R.id.gallery_switch);
        img = view.findViewById(R.id.center_img);
        txt = view.findViewById(R.id.center_txt);
        res = getResources();
        txt.setText(res.getString(R.string.center_img_counter, img_index + 1, images.length));

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.center_toolbar_title);
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
        // set slide show
        slideSwitch.setChecked(true);
        slideSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked) startSlideShow();
                else stopSlideShow();
            }
        });
        ArrayAdapter<CharSequence> timeListAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.gallery_time_list_items, android.R.layout.simple_spinner_item);
        timeListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeList.setAdapter(timeListAdapter);
        timeList.setSelection(2);
        timeList.setOnItemSelectedListener(this);

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
                slideSwitch.setChecked(false);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        stopSlideShow();
        switch (position) {
            case 0:
                time = 1000;
                break;
            case 1:
                time = 2000;
                break;
            case 2:
                time = 3000;
                break;
            case 3:
                time = 4000;
                break;
            case 4:
                time = 5000;
                break;
        }
        startSlideShow();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        stopSlideShow();
        time = 1000;
        startSlideShow();
    }

    private void startSlideShow() {
        if(slideSwitch.isChecked()) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    if(getActivity() != null)
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                img_index ++;
                                img_index %= images.length;
                                img.setImageResource(images[img_index]);
                                img.setTag(img_index);
                                txt.setText(res.getString(R.string.center_img_counter, img_index + 1, images.length));
                            }
                        });
                    timer.cancel();
                    startSlideShow();
                }
            }, time);
        }
    }

    private void stopSlideShow() {
        if(timer != null) { timer.cancel(); }
    }

    private void ButtonsControl() {
        btn_first = view.findViewById(R.id.center_btn_first);
        btn_left = view.findViewById(R.id.center_btn_left);
        btn_right = view.findViewById(R.id.center_btn_right);
        btn_last = view.findViewById(R.id.center_btn_last);

        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSlideShow();
                img_index = 0;
                img.setImageResource(images[img_index]);
                img.setTag(img_index);
                txt.setText(res.getString(R.string.center_img_counter, img_index + 1, images.length));
                startSlideShow();
            }
        });

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSlideShow();
                img_index --;
                if(img_index < 0) img_index = images.length - 1;
                img.setImageResource(images[img_index]);
                img.setTag(img_index);
                txt.setText(res.getString(R.string.center_img_counter, img_index + 1, images.length));
                startSlideShow();
            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSlideShow();
                img_index ++;
                img_index %= images.length;
                img.setImageResource(images[img_index]);
                img.setTag(img_index);
                txt.setText(res.getString(R.string.center_img_counter, img_index + 1, images.length));
                startSlideShow();
            }
        });

        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSlideShow();
                img_index = images.length - 1;
                img.setImageResource(images[img_index]);
                img.setTag(img_index);
                txt.setText(res.getString(R.string.center_img_counter, img_index + 1, images.length));
                startSlideShow();
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
