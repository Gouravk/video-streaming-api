package com.example.videostreaming.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length=2000)
    private String synopsis;
    private String director;
    @Column(name="main_cast")
    private String main_cast;
    private String genre;
    private int runningTime;
    private int yearOfRelease;
    private long impressions;
    private long views;
    private boolean deleted = false;


}
