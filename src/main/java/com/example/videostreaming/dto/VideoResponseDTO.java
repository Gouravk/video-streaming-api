package com.example.videostreaming.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoResponseDTO {
    private Long id;
    private String title;
    private String synopsis;
    private String director;
    private String main_cast;
    private int yearOfRelease;
    private String genre;
    private int runningTime;
    private long impressions;
    private long views;

}