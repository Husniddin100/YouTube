package com.example.YouTube.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
/*
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
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
   //join video entity
    @Column(name = "tag_id")
    private Integer tagId;
    @ManyToOne
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    // join tag entity
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}*/
//// soon
