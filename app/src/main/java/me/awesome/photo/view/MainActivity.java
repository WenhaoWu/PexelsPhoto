package me.awesome.photo.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.Collections;
import java.util.List;

import me.awesome.photo.R;
import me.awesome.photo.contract.PhotoContract;
import me.awesome.photo.databinding.ActivityMainBinding;
import me.awesome.photo.infra.BaseView;
import me.awesome.photo.model.Photo;
import me.awesome.photo.presenter.PhotoPresenter;

public class MainActivity
        extends BaseView<PhotoContract, PhotoPresenter>
        implements PhotoContract {

    private PhotoAdapter mAdapter;

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
        list.addItemDecoration(new PhotoDecoration(10, 2));

        mAdapter = new PhotoAdapter(Collections.emptyList());

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
    protected PhotoPresenter createPresenter() {
        return new PhotoPresenter();
    }

    @Override
    public void onWallpaperFetch(List<Photo> photos) {
        mAdapter.addAll(photos);
    }
}
