package com.example.YouTube.entity;

import com.example.YouTube.enums.ProfileRole;
import com.example.YouTube.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity extends BaseEntity{
    @Column
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    /// attach join column see soon
    @Column(name = "photo_id")
    private String photoId;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ProfileRole role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProfileStatus status;
}
