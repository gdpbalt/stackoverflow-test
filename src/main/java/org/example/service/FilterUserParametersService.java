package org.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.example.model.User;

public class FilterUserParametersService {
    private static final int MIN_REPUTATION = 223;
    private static final int MIN_ANSWERS = 1;
    private static final Set<String> LOCATION_LIST = Set.of("romania", "moldova");

    public List<User> doFilter(List<User> inputUserList) {
        List<User> output = new ArrayList<>();
        for (User user : inputUserList) {
            if (doFilterLocation(user) && doFilterReputation(user) && doFilterAnswers(user)) {
                output.add(user);
            }
        }
        return output;
    }

    private boolean doFilterLocation(User user) {
        String location = user.getLocation();
        if (location == null || location.length() == 0) {
            return false;
        }
        return Arrays.stream(location.toLowerCase().split("\\W+"))
                .anyMatch(LOCATION_LIST::contains);
    }

    private boolean doFilterReputation(User user) {
        return user.getReputation() >= MIN_REPUTATION;
    }

    private boolean doFilterAnswers(User user) {
        return user.getAnswerCount() >= MIN_ANSWERS;
    }
}
