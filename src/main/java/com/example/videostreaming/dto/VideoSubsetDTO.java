package com.example.videostreaming.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoSubsetDTO {
    private String title;
    private String director;
    private String genre;
    private String mainActor;
    private int runningTime;

}