package com.example.YouTube.mapper;

import java.time.LocalDateTime;

public interface VideoShortInfoPaginationMapper {
    public String getId();

    public String geTitle();

    public String getPreviewAttachId();

    public LocalDateTime getPublishedDate();

    public Long getViewCount();

    public Integer getChannelId();

    public String getChannelName();

    public String getPhotoId();

    Integer getProfileId();

    String getProfileName();

    String getProfileSurname();

    String getPlayListJson();

}
