package org.example.service;

import java.io.IOException;
import java.util.List;
import org.example.exception.EmptyResponseException;
import org.example.model.User;
import org.example.util.StackOverflowApi;
import org.example.wrapper.ListWrapper;
import retrofit2.Call;
import retrofit2.Response;

public class FetchUserService {
    static final int TIMEOUT_SECONDS = 120;
    static final int REQUEST_EVERY_SECONDS = 5;
    static final int HTTP_RETRY_STATUS_CODE = 400;
    static final int START_PAGE = 3;
    static final int FINISH_PAGE = 10;

    public void doFetch() {
        StackOverflowApi api = StackOverflowService.getStackOverflowApi();
        FetchUserTagsService fetchUserTagsService = new FetchUserTagsService();
        FilterTagParametersService filterTagParametersService = new FilterTagParametersService();

        for (int page = START_PAGE; page <= FINISH_PAGE; page++) {
            Call<ListWrapper<User>> usersRequest = api.getUsers(page);
            try {
                int count = 0;
                Response<ListWrapper<User>> execute = usersRequest.execute();
                while (execute.code() == HTTP_RETRY_STATUS_CODE && count < TIMEOUT_SECONDS) {
                    count += REQUEST_EVERY_SECONDS;
                    writeDelayMessage(page, count);
                    Thread.sleep(1000 * REQUEST_EVERY_SECONDS);
                    execute = usersRequest.clone().execute();
                }
                System.out.printf("%s", "\r".repeat(20));

                ListWrapper<User> body = execute.body();
                if (body == null) {
                    throw new EmptyResponseException("API didn't get any information");
                }
                List<User> users = body.getItems();
                users = new FilterUserParametersService().doFilter(users);

                for (User user : users) {
                    user.setTags(fetchUserTagsService.doFetch(user.getUserId()));
                    if (filterTagParametersService.doFilter(user.getTags())) {
                        System.out.println(user);
                    }
                }
            } catch (IOException | EmptyResponseException | InterruptedException e) {
                System.out.println("Error get information from util. " + e);
            }
        }
    }

    private static void writeDelayMessage(int page, int counter) {
        System.out.print("\r".repeat(100));
        System.out.printf("Processing users: page %d, waiting... %02d seconds", page, counter);
    }
}
