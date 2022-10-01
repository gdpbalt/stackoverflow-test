package org.example.service;

import java.io.IOException;
import java.util.List;
import org.example.exception.EmptyResponseException;
import org.example.model.User;
import org.example.util.StackOverflowApi;
import org.example.wrapper.ListWrapper;
import retrofit2.Call;

public class FetchingUserService {
    static final int START_PAGE = 3;
    static final int FINISH_PAGE = START_PAGE + 25;
    private final StackOverflowApi api;
    private final FetchingTagService fetchUserTagsService;
    private final FilteringTagService filterTagParametersService;
    private final FilteringUserService filterUserParametersService;
    private final GettingOneApiPageService gettingApiPageService;

    public FetchingUserService(StackOverflowApi api,
                               FetchingTagService fetchUserTagsService,
                               FilteringUserService filterUserParametersService,
                               FilteringTagService filterTagParametersService,
                               GettingOneApiPageService gettingApiPageService) {
        this.api = api;
        this.fetchUserTagsService = fetchUserTagsService;
        this.filterTagParametersService = filterTagParametersService;
        this.filterUserParametersService = filterUserParametersService;
        this.gettingApiPageService = gettingApiPageService;
    }

    public void doFetchUsers() {
        for (int page = START_PAGE; page <= FINISH_PAGE; page++) {
            Call<ListWrapper<User>> usersRequest = api.getUsers(page);
            try {
                ListWrapper<User> body = gettingApiPageService.getOnePage(usersRequest, page);
                if (body == null) {
                    throw new EmptyResponseException("API didn't get any information");
                }

                List<User> users = body.getItems();
                users = filterUserParametersService.doFilterForUsers(users);
                for (User user : users) {
                    user.setTags(fetchUserTagsService.doFetchTagsForUser(user.getUserId()));
                    if (filterTagParametersService.doFilterForTags(user.getTags())) {
                        System.out.println(user);
                    }
                }
            } catch (IOException | EmptyResponseException | InterruptedException e) {
                System.out.println("Error get information from util. " + e);
            }
        }
    }
}
