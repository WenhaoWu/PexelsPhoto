package wenhao.practice.bingwallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class View_ImageFullActivity extends AppCompatActivity {

    public static final String Tag_imgUrl = "ImageUrl";
    public static final String Tag_imgDate = "ImageDate";

    private WallpaperManager wallpaperManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__image_full_view);

        //hiding the status bar
        View decorView = getWindow().getDecorView();
        int uiOption =View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOption);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBitmapFromUrl(getIntent().getStringExtra(Tag_imgUrl), 1,new imgReqCallback() {
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

        FloatingActionButton fab_dl = (FloatingActionButton)findViewById(R.id.fab_download);
        fab_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBitmapFromUrl(getIntent().getStringExtra(Tag_imgUrl), 2, new imgReqCallback() {
                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        File dir = new File(Environment.getExternalStorageDirectory()+"/BingWallpaper"+"/");
                        if (!dir.exists()){
                            dir.mkdirs();
                        }

                        OutputStream fOut = null;
                        File file = new File(dir, getIntent().getStringExtra(Tag_imgDate)+".jpg"); // the File to save to
                        if (file.exists()){
                            Toast.makeText(getBaseContext(),"Downloaded already",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            try {
                                fOut = new FileOutputStream(file);
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
                                fOut.flush();
                                fOut.close(); // do not forget to close the stream
                                MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
                                Toast.makeText(getBaseContext(), "Downloaded", Toast.LENGTH_SHORT).show();
                            } catch (FileNotFoundException e) {
                                Log.e("intoFNFE",e.toString());
                            } catch (IOException e) {
                                Log.e("intoIOE", e.toString());
                            }
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
    private void getBitmapFromUrl(String src, final int mode, final imgReqCallback ircb) {
        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        ImageRequest imgReq = new ImageRequest(src,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        ircb.onSuccess(response);
                        if (mode==1){
                            Toast.makeText(getBaseContext(), "Wallpaper is set", Toast.LENGTH_SHORT).show();
                        }

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
