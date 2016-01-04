package wenhao.practice.bingwallpaper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by wenhaowu on 29/12/15.
 */
public class View_RVAdapter extends RecyclerView.Adapter<View_RVAdapter.mViewHolder> {

    private ArrayList<Object_Wallpaper> dataList;
    private Context mContext;

    public View_RVAdapter(ArrayList<Object_Wallpaper> dataList,Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    public View_RVAdapter(Context mContext) {
        this.dataList = new ArrayList<Object_Wallpaper>();
        this.mContext = mContext;
    }

    public void setDataList(ArrayList<Object_Wallpaper> dataList) {
        if (dataList.equals(this.dataList)){
            Toast.makeText(mContext,"That's all :)",Toast.LENGTH_SHORT).show();
        }
        else {
            this.dataList.clear();
            this.dataList.addAll(dataList);
            notifyDataSetChanged();
        }

    }

    public static class mViewHolder extends RecyclerView.ViewHolder{
        public SimpleDraweeView imgV;
        public CardView cv;

        public mViewHolder(View itemView) {
            super(itemView);
            this.imgV=(SimpleDraweeView)itemView.findViewById(R.id.RV_CV_img);
            this.cv = (CardView)itemView.findViewById(R.id.card_view);
        }

    }

    @Override
    public View_RVAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cardview, parent, false);
        mViewHolder vh = new mViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(final View_RVAdapter.mViewHolder holder, final int position) {
        Log.e("Try", dataList.get(position).getImg_url());
        holder.imgV.setImageURI(Uri.parse(dataList.get(position).getImg_url()));

        holder.imgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(View_ImageFullActivity.Tag_imgUrl, dataList.get(position).getImg_url());
                intent.putExtra(View_ImageFullActivity.Tag_imgDate, dataList.get(position).getDate());
                intent.setClass(mContext, View_ImageFullActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        //set height in proportion to screen size
        int proportionalHeight = containerHeight();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, proportionalHeight); // (width, height)
        holder.cv.setLayoutParams(params);
    }

    public int containerHeight() {
        //getting the screen size
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);

        //Greater ratio smaller proportion
        double ratio = 2.7;

        return (int) (dm.heightPixels / ratio);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void clearAll(){
        dataList.clear();
    }
}
