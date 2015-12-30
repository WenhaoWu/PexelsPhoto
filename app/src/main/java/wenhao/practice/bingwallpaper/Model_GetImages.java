package wenhao.practice.bingwallpaper;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by wenhaowu on 28/12/15.
 */
public class Model_GetImages {
    public static final String Wallpaper_Url = "http://www.bing.com/HPImageArchive.aspx?format=js&n=8&idx=";//1";

    public interface volleyCallback{
        void onSuccess(ArrayList<Object_Wallpaper> items);
        void onFail(Throwable error);
    }

    public static void getImages(final volleyCallback callback, int idx, Context mContext){
        RequestQueue queue = Volley.newRequestQueue(mContext);
        final ProgressDialog PD = createProgressDialog(mContext);

        final String req_url = Model_GetImages.Wallpaper_Url+idx;

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, req_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<Object_Wallpaper> result = new ArrayList<>();
                        String img_url = null, img_copy = null, realURL;
                        try {
                            for (int i = 0; i < response.getJSONArray("images").length(); i++){
                                img_url = response.getJSONArray("images").getJSONObject(i).getString("url");
                                img_copy = response.getJSONArray("images").getJSONObject(i).getString("copyright");
                                img_url = img_url.replace("1920x1080","1080x1920");
                                realURL = "http://www.bing.com"+img_url;
                                result.add(new Object_Wallpaper(realURL, img_copy));
                            }
                        }
                        catch (JSONException e) {
                            Log.e("ResponseErr", e.toString());
                        }
                        callback.onSuccess(result);
                        PD.dismiss();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VolleyReqErr", error.toString());
                        callback.onFail(error);
                        PD.dismiss();
                    }
                });

        queue.add(req);
        PD.show();
    }

    public static ProgressDialog createProgressDialog(Context mContext) {
        ProgressDialog result = new ProgressDialog(mContext);
        try {
            result.show();
        }
        catch (WindowManager.BadTokenException e){

        }
        result.setCancelable(true);
        //result.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        result.setContentView(R.layout.layout_progress_dialog);
        result.dismiss();
        return result;
    }
}
