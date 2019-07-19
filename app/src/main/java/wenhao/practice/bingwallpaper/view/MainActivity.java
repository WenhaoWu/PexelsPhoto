package wenhao.practice.bingwallpaper.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import wenhao.practice.bingwallpaper.R;
import wenhao.practice.bingwallpaper.databinding.ActivityMainBinding;
import wenhao.practice.bingwallpaper.model.Image;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Image> mImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

        mLayoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(mLayoutManager);

        mAdapter = new WallpaperAdapter(mImages);
        mList.setAdapter(mAdapter);
    }
}
