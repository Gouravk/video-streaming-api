package com.example.videostreaming.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EngagementResponseDTO {
    private Long videoId;
    private String title;
    private long impressions;
    private long views;
}
