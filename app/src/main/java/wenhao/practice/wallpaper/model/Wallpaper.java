package wenhao.practice.wallpaper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("SpellCheckingInspection")
public class Wallpaper {

    @SerializedName("startdate")
    @Expose
    private String startdate;
    @SerializedName("fullstartdate")
    @Expose
    private String fullstartdate;
    @SerializedName("enddate")
    @Expose
    private String enddate;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("urlbase")
    @Expose
    private String urlbase;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("copyrightlink")
    @Expose
    private String copyrightlink;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("quiz")
    @Expose
    private String quiz;
    @SerializedName("wp")
    @Expose
    private boolean wp;
    @SerializedName("hsh")
    @Expose
    private String hsh;
    @SerializedName("drk")
    @Expose
    private int drk;
    @SerializedName("top")
    @Expose
    private int top;
    @SerializedName("bot")
    @Expose
    private int bot;

    public String getUrl() {
        return "https://www.bing.com" + url.replace("1920x1080", "1080x1920");
    }
}
