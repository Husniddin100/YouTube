package com.example.YouTube.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "email_send_history")
public class EmailSendHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "new_email")
    private String new_email;
    @Column(name = "visible")
    private Boolean visible=true;
    @Column(name = "createdDate")
    private LocalDateTime createdDate = LocalDateTime.now();
}