package org.example;

import org.example.service.FetchingTagService;
import org.example.service.FetchingUserService;
import org.example.service.FilteringTagService;
import org.example.service.FilteringUserService;
import org.example.service.GettingOneApiPageService;
import org.example.service.StackOverflowApiService;
import org.example.util.StackOverflowApi;

public class Main {

    public static void main(String[] args) {
        StackOverflowApi api = StackOverflowApiService.getStackOverflowApi();
        GettingOneApiPageService gettingApiPageService = new GettingOneApiPageService();
        FetchingUserService fetchUserService = new FetchingUserService(
                api,
                new FetchingTagService(api, gettingApiPageService),
                new FilteringUserService(),
                new FilteringTagService(),
                gettingApiPageService);

        System.out.println("\nUsers list with concrete criteria according to task:");
        fetchUserService.doFetchUsers();
    }
}
