package com.rzeszowgallery.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.rzeszowgallery.R;

public class RecyclerAdapter extends RecyclerView.Adapter< ImageViewHolder > {

    private Context ctx;
    private int[] images;

    public RecyclerAdapter(Context ctx, int[] images) {
        this.ctx = ctx;
        this.images = images;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        holder.img.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}

class ImageViewHolder extends RecyclerView.ViewHolder {

    ImageView img;

    ImageViewHolder(View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.recycler_img);
    }
}
