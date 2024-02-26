package com.example.YouTube.entity;

import com.example.YouTube.enums.ChannelStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "channel")
public class ChannelEntity {
    @Id
    @GeneratedValue(generator = "generator_uuid")
    @GenericGenerator(name = "generator_uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(unique = true)
    private String name;

    @Column(name = "photo_id")
    private String photoId;

    @ManyToOne
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    private AttachEntity photo;

    @Column(columnDefinition = "text")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column
    private ChannelStatus status;

    @Column(name = "banner_id")
    private String bannerId;

    @OneToOne
    @JoinColumn(name = "banner_id", insertable = false, updatable = false)
    private AttachEntity banner;

    @Column(name = "profile_id")
    private Integer profileId;

    @OneToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "subscription_count")
    private Integer subscriptionCount;
}
