package com.alok.cogz.request;

import com.alok.cogz.constants.ApiConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.alok.cogz.constants.ApiConstants.CONNECTION_TIMEOUT;
import static com.alok.cogz.constants.ApiConstants.READ_TIMEOUT;
import static com.alok.cogz.constants.ApiConstants.WRITE_TIMEOUT;

public class ServiceGenerator {
    private static ServiceGenerator instance;
    private DropboxAPI dropboxAPI;


    private static OkHttpClient client = new OkHttpClient.Builder()

            // establish connection to server
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)

            // time between each byte read from the server
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)

            // time between each byte sent to server
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

            .retryOnConnectionFailure(false)

            .build();


    public synchronized static ServiceGenerator getInstance() {
        if (instance == null) {
            instance = new ServiceGenerator();
        }
        return instance;
    }

    private ServiceGenerator() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dropboxAPI = retrofit.create(DropboxAPI.class);
    }

    public DropboxAPI getDropboxApi() {
        return dropboxAPI;
    }

}
