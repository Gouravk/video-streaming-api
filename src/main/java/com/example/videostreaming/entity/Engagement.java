package com.example.videostreaming.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Engagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "video_id", nullable = false, unique = true)
    private Video video;

    private long impressions = 0;
    private long views = 0;

    public void incrementImpressions() {
        this.impressions++;
    }

    public void incrementViews() {
        this.views++;
    }
}
