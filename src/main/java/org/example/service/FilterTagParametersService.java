package org.example.service;

import java.util.List;
import java.util.Set;
import org.example.model.Tag;

public class FilterTagParametersService {
    private static final Set<String> TAGS_LIST = Set.of("java", ".net", "docker", "c#");

    public boolean doFilter(List<Tag> inputTagsList) {
        return inputTagsList.stream()
                .map(t -> t.getName().toLowerCase())
                .anyMatch(TAGS_LIST::contains);
    }
}
