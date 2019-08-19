package wenhao.practice.bingwallpaper.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import wenhao.practice.bingwallpaper.R;
import wenhao.practice.bingwallpaper.contract.WallpaperContract;
import wenhao.practice.bingwallpaper.databinding.ActivityMainBinding;
import wenhao.practice.bingwallpaper.infra.BaseView;
import wenhao.practice.bingwallpaper.model.Wallpaper;
import wenhao.practice.bingwallpaper.presenter.WallpaperPresenter;

public class MainActivity
        extends BaseView<WallpaperContract, WallpaperPresenter>
        implements WallpaperContract {

    private RecyclerView mList;
    private WallpaperAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Wallpaper> mWallpapers = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

        mList = binding.list;

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mList.setLayoutManager(mLayoutManager);
        mList.addItemDecoration(new WallpaperDecoration(10, 2));

        mAdapter = new WallpaperAdapter(mWallpapers);
        mList.setAdapter(mAdapter);

        mPresenter.fetchWallpaper(mAdapter.getItemCount());
    }

    @Override
    protected WallpaperPresenter createPresenter() {
        return new WallpaperPresenter();
    }

    @Override
    public void onWallpaperFetch(List<Wallpaper> wallpapers) {
        mWallpapers.addAll(wallpapers);
        mAdapter.addAll(mWallpapers);
    }
}
