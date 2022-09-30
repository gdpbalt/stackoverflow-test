package org.example;

import org.example.service.FetchingTagService;
import org.example.service.FetchingUserService;
import org.example.service.FilteringTagService;
import org.example.service.FilteringUserService;
import org.example.service.GettingApiPageService;
import org.example.service.StackOverflowService;
import org.example.util.StackOverflowApi;

public class Main {

    public static void main(String[] args) {
        StackOverflowApi api = StackOverflowService.getStackOverflowApi();
        GettingApiPageService gettingApiPageService = new GettingApiPageService();
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
