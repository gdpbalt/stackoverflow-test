package org.example.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    @SerializedName("user_id")
    private Long userId;
    @SerializedName("display_name")
    private String username;
    private String location;
    @SerializedName("answer_count")
    private Long answerCount;
    @SerializedName("question_count")
    private Long questionCount;
    private Long reputation;
    @SerializedName("link")
    private String linkProfile;
    @SerializedName("profile_image")
    private String linkAvatar;
    private List<Tag> tags;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Long answerCount) {
        this.answerCount = answerCount;
    }

    public Long getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Long questionCount) {
        this.questionCount = questionCount;
    }

    public Long getReputation() {
        return reputation;
    }

    public void setReputation(Long reputation) {
        this.reputation = reputation;
    }

    public String getLinkProfile() {
        return linkProfile;
    }

    public void setLinkProfile(String linkProfile) {
        this.linkProfile = linkProfile;
    }

    public String getLinkAvatar() {
        return linkAvatar;
    }

    public void setLinkAvatar(String linkAvatar) {
        this.linkAvatar = linkAvatar;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        String tagsString = tags.stream()
                .map(Tag::getName)
                .collect(Collectors.joining(", "));
        return "User{"
                + "username='" + username + '\''
                + ", location='" + location + '\''
                + ", answerCount=" + answerCount
                + ", questionCount=" + questionCount
                + ", reputation=" + reputation
                + ", linkProfile='" + linkProfile + '\''
                + ", linkAvatar='" + linkAvatar + '\''
                + ", tags='" + tagsString.substring(0, 200) + "...'"
                + '}';
    }
}
