package com.intechtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public class Track extends AbstractModel {
    public static final String BUNDLE_KEY = "track";

    @JsonProperty("artistName")
    private String artistName;

    @JsonProperty("trackName")
    private String trackName;

    @JsonProperty("artworkUrl60")
    private String albumImage;

    @JsonProperty("trackTimeMillis")
    private int trackTime;

    @JsonProperty("previewUrl")
    private String trackUrl;

    @JsonProperty("artworkUrl100")
    private String imageUrl;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public int getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(int trackTime) {
        this.trackTime = trackTime;
    }

    public String getTrackUrl() {
        return trackUrl;
    }

    public void setTrackUrl(String previewUrl) {
        this.trackUrl = previewUrl;
    }

    @Override
    protected String getBundleKey() {
        return BUNDLE_KEY;
    }
}
