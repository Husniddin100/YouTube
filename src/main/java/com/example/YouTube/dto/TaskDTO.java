package com.example.YouTube.dto;

import com.example.YouTube.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
    private String id;
    private String title;
    private String content;
    private TaskStatus status;
}
