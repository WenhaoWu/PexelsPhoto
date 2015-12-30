package wenhao.practice.bingwallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;

public class View_ImageFullActivity extends AppCompatActivity {

    public static final String Tag_imgUrl = "ImageUrl";

    private WallpaperManager wallpaperManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__image_full_view);

        //hiding the status bar
        View decorView = getWindow().getDecorView();
        int uiOption = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOption);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBitmapFromUrl(getIntent().getStringExtra(Tag_imgUrl), new imgReqCallback() {
                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        wallpaperManager = WallpaperManager.getInstance(View_ImageFullActivity.this);
                        try {
                            wallpaperManager.setBitmap(bitmap);
                        } catch (IOException e) {
                            Log.e("UrlToBitmapErr", e.toString());
                        }

                    }
                });
            }
        });

        SimpleDraweeView sdv = (SimpleDraweeView)findViewById(R.id.full_img);
        if (getIntent().getStringExtra(Tag_imgUrl)!=null){
            sdv.setImageURI(Uri.parse(getIntent().getStringExtra(Tag_imgUrl)));
        }

    }

    private interface imgReqCallback{
        void onSuccess(Bitmap bitmap);
    }
    private void getBitmapFromUrl(String src, final imgReqCallback ircb) {
        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        ImageRequest imgReq = new ImageRequest(src,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        ircb.onSuccess(response);
                        Toast.makeText(getBaseContext(),"Wallpaper is set", Toast.LENGTH_SHORT).show();
                    }
                }, 0, 0, null, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VolleyImgErr", error.toString());
                    }
                });
        queue.add(imgReq);

    }


}
