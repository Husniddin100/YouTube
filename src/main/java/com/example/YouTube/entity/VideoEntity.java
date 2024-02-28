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
    @Column(name="title")
    private String title;
    @OneToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryId;
    @ManyToOne
    @JoinColumn(name = "attach_id")
    private AttachEntity attachId;
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
    @ManyToOne
    @JoinColumn(name = "chanell_id")
    private ChannelEntity channel;
    /*  private String previewAttachId;
    private String title;
    private Integer categoryId;
    private String attachId;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private VideoStatus status;*/
}
