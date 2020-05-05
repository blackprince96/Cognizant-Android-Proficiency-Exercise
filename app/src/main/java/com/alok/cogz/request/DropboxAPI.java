package com.alok.cogz.request;

import com.alok.cogz.request.responses.Page;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DropboxAPI {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<Page> getPageData();
}
