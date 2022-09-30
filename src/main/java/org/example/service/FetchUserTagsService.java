package org.example.service;

import java.io.IOException;
import java.util.List;
import org.example.exception.EmptyResponseException;
import org.example.model.Tag;
import org.example.util.StackOverflowApi;
import org.example.wrapper.ListWrapper;
import retrofit2.Call;
import retrofit2.Response;

public class FetchUserTagsService {
    static final int TIMEOUT_SECONDS = 120;
    static final int REQUEST_EVERY_SECONDS = 5;
    static final int HTTP_RETRY_STATUS_CODE = 400;

    public List<Tag> doFetch(Long userId) {
        StackOverflowApi api = StackOverflowService.getStackOverflowApi();
        List<Tag> tags = null;

        Call<ListWrapper<Tag>> tagsRequest = api.getTags(userId, 1);
        try {
            int count = 0;
            Response<ListWrapper<Tag>> execute = tagsRequest.execute();
            while (execute.code() == HTTP_RETRY_STATUS_CODE && count < TIMEOUT_SECONDS) {
                count += REQUEST_EVERY_SECONDS;
                writeDelayMessage(1, count);
                Thread.sleep(1000 * REQUEST_EVERY_SECONDS);
                execute = tagsRequest.clone().execute();
            }
            System.out.printf("%s", "\r".repeat(20));

            ListWrapper<Tag> body = execute.body();
            if (body == null) {
                throw new EmptyResponseException("API didn't get any information");
            }
            tags = body.getItems();
        } catch (IOException | EmptyResponseException | InterruptedException e) {
            System.out.println("Error get information from util. " + e);
        }
        return tags;
    }

    private static void writeDelayMessage(int page, int counter) {
        System.out.print("\r".repeat(100));
        System.out.printf("Processing tags: page %d, waiting... %02d seconds", page, counter);
    }
}
