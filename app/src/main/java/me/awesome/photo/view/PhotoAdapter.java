package me.awesome.photo.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import me.awesome.photo.databinding.PhotoItemBinding;
import me.awesome.photo.model.Photo;

class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    public static final int ITEM_HEIGHT_MAX = 650;
    public static final int ITEM_HEIGHT_MIN = 450;

    private List<Photo> mItems;
    private Random mRandom;

    PhotoAdapter(List<Photo> photos) {
        mItems = new ArrayList<>();
        mItems.addAll(photos);
        mRandom = new Random();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());

        PhotoItemBinding binding =
                PhotoItemBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Photo photo = mItems.get(position);
        PhotoItemBinding binding = holder.binding;

        ImageView image = binding.image;

        image.getLayoutParams().height = getRandomIntInRange();

        int height = image.getLayoutParams().height;

        Picasso.get()
                .load(photo.getSrc().getPortrait())
                .resize(0, height)
                .centerInside()
                .into(binding.image);
    }


    private int getRandomIntInRange() {
        return mRandom.nextInt(ITEM_HEIGHT_MAX - ITEM_HEIGHT_MIN + ITEM_HEIGHT_MIN) + ITEM_HEIGHT_MIN;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    void addAll(Collection<Photo> images) {

        if (images.isEmpty()) return;

        int start = mItems.size();
        mItems.addAll(images);
        for (int i = start; i < mItems.size(); i++)
            this.notifyItemInserted(start);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private PhotoItemBinding binding;

        private ViewHolder(PhotoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
