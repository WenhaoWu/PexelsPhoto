package wenhao.practice.wallpaper.view;


import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WallpaperDecoration extends RecyclerView.ItemDecoration {

    private final int mSizeGridSpacingPx;
    private final int mGridSize;

    private boolean mNeedLeftSpacing = false;

    WallpaperDecoration(int mSizeGridSpacingPx, int mGridSize) {
        this.mSizeGridSpacingPx = mSizeGridSpacingPx;
        this.mGridSize = mGridSize;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect,
                               @NonNull View view,
                               @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int frameWidth = ((parent.getWidth() - mSizeGridSpacingPx * (mGridSize - 1)) / mGridSize);
        int padding = parent.getWidth() / mGridSize - frameWidth;

        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();

        if (itemPosition < mGridSize)
            outRect.top = 0;
        else
            outRect.top = mSizeGridSpacingPx;

        if (itemPosition % mGridSize == 0) {
            outRect.left = 0;
            outRect.right = padding;
            mNeedLeftSpacing = true;
        } else if ((itemPosition + 1) % mGridSize == 0) {
            mNeedLeftSpacing = false;
            outRect.right = 0;
            outRect.left = padding;
        } else if (mNeedLeftSpacing) {
            mNeedLeftSpacing = false;
            outRect.left = mSizeGridSpacingPx - padding;
            if ((itemPosition + 2) % mGridSize == 0)
                outRect.right = mSizeGridSpacingPx - padding;
            else
                outRect.right = mSizeGridSpacingPx / 2;
        } else if ((itemPosition + 2) % mGridSize == 0) {
            outRect.left = mSizeGridSpacingPx / 2;
            outRect.right = mSizeGridSpacingPx - padding;
        } else {
            outRect.left = mSizeGridSpacingPx / 2;
            outRect.right = mSizeGridSpacingPx / 2;
        }
        outRect.bottom = 0;
    }
}



