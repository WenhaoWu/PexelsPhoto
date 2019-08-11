package wenhao.practice.bingwallpaper.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import wenhao.practice.bingwallpaper.databinding.WallpaperItemBinding;
import wenhao.practice.bingwallpaper.model.Wallpaper;

class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.ViewHolder> {

    private List<Wallpaper> mItems;

    WallpaperAdapter(List<Wallpaper> wallpapers) {
        mItems = new ArrayList<>();
        mItems.addAll(wallpapers);
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
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private WallpaperItemBinding binding;

        private ViewHolder(WallpaperItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
