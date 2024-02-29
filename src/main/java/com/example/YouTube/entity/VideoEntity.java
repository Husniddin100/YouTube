package com.example.YouTube.entity;

import com.example.YouTube.dto.ChannelDTO;
import com.example.YouTube.enums.TypeStatus;
import com.example.YouTube.enums.VideoStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "video")
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "title")
    private String title;
    @Column
    private Integer categoryId;
    @ManyToOne
    @JoinColumn(name = "category", updatable = false, insertable = false)
    private CategoryEntity category;
    @Column
    private String attachId;
    @OneToOne
    @JoinColumn(name = "attach", insertable = false, updatable = false)
    private AttachEntity attach;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "publishedDate")
    private LocalDateTime publishedDate;
    @Column(name = "video_status")
    private VideoStatus status;
    @Column(name = "type_status")
    private TypeStatus typeStatus;
    @Column(name = "view_count")
    private Integer viewCount;
    @Column(name = "share_count")
    private Integer shareCount;
    @Column(name = "description")
    private String description;
    @Column
    private String channelId;
    @ManyToOne
    @JoinColumn(name = "chanel", insertable = false, updatable = false)
    private ChannelEntity channel;
}
