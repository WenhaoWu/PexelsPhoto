package wenhao.practice.wallpaper.view;

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

import wenhao.practice.wallpaper.databinding.WallpaperItemBinding;
import wenhao.practice.wallpaper.model.Wallpaper;

class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.ViewHolder> {

    public static final int ITEM_HEIGHT_MAX = 600;
    public static final int ITEM_HEIGHT_MIN = 400;
    
    private List<Wallpaper> mItems;
    private Random mRandom;

    WallpaperAdapter(List<Wallpaper> wallpapers) {
        mItems = new ArrayList<>();
        mItems.addAll(wallpapers);
        mRandom = new Random();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());

        WallpaperItemBinding binding =
                WallpaperItemBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Wallpaper wallpaper = mItems.get(position);
        WallpaperItemBinding binding = holder.binding;

        ImageView image = binding.image;

        image.getLayoutParams().height = getRandomIntInRange();

        int height = image.getLayoutParams().height;

        Picasso.get()
                .load(wallpaper.getUrl())
                .resize(0, height)
                .centerCrop()
                .into(binding.image);
    }


    private int getRandomIntInRange() {
        return mRandom.nextInt(ITEM_HEIGHT_MAX - ITEM_HEIGHT_MIN + ITEM_HEIGHT_MIN) + ITEM_HEIGHT_MIN;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    void addAll(Collection<Wallpaper> images) {
        mItems.addAll(images);
        this.notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private WallpaperItemBinding binding;

        private ViewHolder(WallpaperItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
