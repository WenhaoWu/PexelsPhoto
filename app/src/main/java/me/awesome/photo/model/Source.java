package me.awesome.photo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {
    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("large2x")
    @Expose
    private String large2x;
    @SerializedName("large")
    @Expose
    private String large;
    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("small")
    @Expose
    private String small;
    @SerializedName("portrait")
    @Expose
    private String portrait;
    @SerializedName("landscape")
    @Expose
    private String landscape;
    @SerializedName("tiny")
    @Expose
    private String tiny;

    public String getPortrait() {
        return portrait;
    }
}
