package com.intechtest;

import com.intechtest.controller.newtwork.model.JSONResponse;
import com.intechtest.model.Track;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public interface RetrofitApi {

    @GET("/search")
    JSONResponse<List<Track>> getTracks(@Query("term") String search);
}
