package com.example.YouTube.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoLikeInfoDTO {
    private Integer id;
    private VideoShortDTO videoShortDTO;
    private PreviewAttachDTO previewAttach;

}
