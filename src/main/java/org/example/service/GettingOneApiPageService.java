package org.example.service;

import java.io.IOException;
import org.example.wrapper.ListWrapper;
import retrofit2.Call;
import retrofit2.Response;

public class GettingOneApiPageService {
    static final int TIMEOUT_SECONDS = 180;
    static final int REQUEST_EVERY_SECONDS = 5;
    static final int HTTP_RETRY_STATUS_CODE = 400;
    static final int ERASE_REPEAT_SYMBOL = 100;

    public <T> ListWrapper<T> getOnePage(Call<ListWrapper<T>> request, int page)
            throws InterruptedException, IOException {
        Response<ListWrapper<T>> execute = request.execute();
        for (int count = 1; count < TIMEOUT_SECONDS; count += REQUEST_EVERY_SECONDS) {
            if (execute.code() != HTTP_RETRY_STATUS_CODE) {
                if (count > 1) {
                    System.out.printf("%s", "\r".repeat(ERASE_REPEAT_SYMBOL));
                }
                break;
            }
            writeDelayMessage(page, count);
            Thread.sleep(1000 * REQUEST_EVERY_SECONDS);
            execute = request.clone().execute();
        }
        return execute.body();
    }

    private void writeDelayMessage(int page, int counter) {
        System.out.printf("%s", "\r".repeat(ERASE_REPEAT_SYMBOL));
        System.out.printf("Getting page %d. Waiting... %d sec.", page, counter);
    }
}
