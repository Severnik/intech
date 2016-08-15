package com.intechtest.controller.newtwork.event;


import com.intechtest.Environment;
import com.intechtest.RetrofitApi;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public abstract class AbstractRestService<T> extends AbstractService<T> {
    private static RetrofitApi irestService;


    protected static RetrofitApi getAPI() {
        if (irestService == null) {
            irestService = new RestAdapter.Builder()
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade request) {
                            request.addHeader("Content-Type", "application/json");
                        }
                    })
                    .setEndpoint(Environment.SERVER_REST)
                    .setConverter(new JacksonConverter(getObjectMapper()))
                    .setLogLevel(Environment.LOG_LEVEL)
                    .build()
                    .create(RetrofitApi.class);
        }
        return irestService;
    }

    @Override
    public void run() {
        run(getAPI());
    }

    protected abstract void run(RetrofitApi API);


}
