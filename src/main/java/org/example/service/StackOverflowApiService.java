package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.util.StackOverflowApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StackOverflowApiService {
    private static StackOverflowApi api;

    private StackOverflowApiService() {
    }

    public static synchronized StackOverflowApi getStackOverflowApi() {
        if (api == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
            GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(StackOverflowApi.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            api = retrofit.create(StackOverflowApi.class);
        }
        return api;
    }
}
