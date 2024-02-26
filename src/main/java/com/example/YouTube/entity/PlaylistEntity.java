package com.example.YouTube.entity;

import com.example.YouTube.enums.PlaylistStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "playlist")
public class PlaylistEntity extends BaseEntity{
 @OneToMany()
 @JoinColumn(updatable = false,nullable = false)
   private  ChannelEntity channel;
 @Column(name = "channel_id")
   private String channel_id;
    @Column(name = "name")
    private  String name;
    @Column(name = "description")
    private  String description;
    @OneToMany()
    @JoinColumn(name = "status")
    private PlaylistStatus status;
    @Column(name = "order_num")
    private  Integer order_num;
}
