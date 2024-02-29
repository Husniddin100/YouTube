package com.example.YouTube.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "video_tag")
public class VideoTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "video_id")
    private String videoId;
    @ManyToOne
    @JoinColumn(name = "video", insertable = false, updatable = false)
    private VideoEntity video;
    @Column(name = "tag_id")
    private Integer tagId;
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private TagEntity tag;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}