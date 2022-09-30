package org.example.util;

import org.example.model.Tag;
import org.example.model.User;
import org.example.wrapper.ListWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StackOverflowApi {
    String BASE_URL = "https://api.stackexchange.com/2.3/";
    String USER_FILTER_NAME = "!BTeB3PZ3AJzgj3Lh0pzRTiwkh(izBE";
    String TAG_FILTER_NAME = "!bDgUL)RZ-BC_pA";
    String API_KEY = "XlrUpnkCrxN*jDfgveIlcw((";

    @GET("/users?pagesize=100&order=desc&sort=reputation&site=stackoverflow"
            + "&filter=" + USER_FILTER_NAME + "&key=" + API_KEY)
    Call<ListWrapper<User>> getUsers(@Query("page") int pageId);

    @GET("/users/{id}/top-tags?pagesize=100&site=stackoverflow"
            + "&filter=" + TAG_FILTER_NAME + "&key=" + API_KEY)
    Call<ListWrapper<Tag>> getTags(@Path("id") long id);
}
