package com.example.videostreaming.service;

import com.example.videostreaming.dto.EngagementResponseDTO;
import com.example.videostreaming.entity.Engagement;
import com.example.videostreaming.repository.EngagementRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EngagementService {

    private final EngagementRepository engagementRepository;

    public EngagementService(EngagementRepository engagementRepository) {
        this.engagementRepository = engagementRepository;
    }

    public EngagementResponseDTO getEngagementStats(Long videoId) {
        Engagement engagement = engagementRepository.findByVideoId(videoId)
                .orElseThrow(() -> new NoSuchElementException("Engagement data not found for video id " + videoId));

        return new EngagementResponseDTO(
                engagement.getVideo().getId(),
                engagement.getVideo().getTitle(),
                engagement.getImpressions(),
                engagement.getViews()
        );
    }
}
