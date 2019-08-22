package me.awesome.wallpaper.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.Collections;
import java.util.List;

import me.awesome.wallpaper.R;
import me.awesome.wallpaper.contract.WallpaperContract;
import me.awesome.wallpaper.databinding.ActivityMainBinding;
import me.awesome.wallpaper.infra.BaseView;
import me.awesome.wallpaper.model.Wallpaper;
import me.awesome.wallpaper.presenter.WallpaperPresenter;

public class MainActivity
        extends BaseView<WallpaperContract, WallpaperPresenter>
        implements WallpaperContract {

    private WallpaperAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

        RecyclerView list = binding.list;

        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
        );

        list.setLayoutManager(manager);
        list.addItemDecoration(new WallpaperDecoration(10, 2));

        mAdapter = new WallpaperAdapter(Collections.emptyList());

        list.setAdapter(mAdapter);
        list.addOnScrollListener(bottomListener());

        mPresenter.fetchWallpaper(mAdapter.getItemCount());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    private RecyclerView.OnScrollListener bottomListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1))
                    mPresenter.fetchWallpaper(mAdapter.getItemCount());
            }
        };
    }

    @Override
    protected WallpaperPresenter createPresenter() {
        return new WallpaperPresenter();
    }

    @Override
    public void onWallpaperFetch(List<Wallpaper> wallpapers) {
        mAdapter.addAll(wallpapers);
    }
}
