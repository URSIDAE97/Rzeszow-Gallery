package com.rzeszowgallery.recyclerView;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rzeszowgallery.R;

public class RecyclerAdapter extends RecyclerView.Adapter< ImageViewHolder > {

    private int[] images;
    private ImageView img;
    private TextView txt;
    private Resources res;
    private LinearLayout gridLayout;
    private LinearLayout singleLayout;

    public RecyclerAdapter(int[] images, ImageView img, TextView txt, Resources res, LinearLayout gridLayout, LinearLayout singleLayout) {
        this.images = images;
        this.img = img;
        this.txt = txt;
        this.res = res;
        this.gridLayout = gridLayout;
        this.singleLayout = singleLayout;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {
        holder.img.setImageResource(images[position]);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(images[position]);
                txt.setText(res.getString(R.string.square_img_counter, position + 1, images.length));
                gridLayout.setVisibility(View.GONE);
                singleLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}

class ImageViewHolder extends RecyclerView.ViewHolder {

    ImageView img;
    CardView cardView;

    ImageViewHolder(View view) {
        super(view);
        img = view.findViewById(R.id.recycler_img);
        cardView = view.findViewById(R.id.card_recycler);
    }
}
