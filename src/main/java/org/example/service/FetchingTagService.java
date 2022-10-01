package org.example.service;

import java.io.IOException;
import java.util.List;
import org.example.exception.EmptyResponseException;
import org.example.model.Tag;
import org.example.util.StackOverflowApi;
import org.example.wrapper.ListWrapper;
import retrofit2.Call;

public class FetchingTagService {
    private final StackOverflowApi api;
    private final GettingOneApiPageService gettingApiPageService;

    public FetchingTagService(StackOverflowApi api,
                              GettingOneApiPageService gettingApiPageService) {
        this.api = api;
        this.gettingApiPageService = gettingApiPageService;
    }

    public List<Tag> doFetchTagsForUser(Long userId) {
        List<Tag> userTags = null;
        Call<ListWrapper<Tag>> tagsRequest = api.getTags(userId);
        try {
            ListWrapper<Tag> body = gettingApiPageService.getOnePage(tagsRequest, 1);
            if (body == null) {
                throw new EmptyResponseException("API didn't get any information");
            }
            userTags = body.getItems();
        } catch (IOException | EmptyResponseException | InterruptedException e) {
            System.out.println("Error get information from util. " + e);
        }
        return userTags;
    }
}
