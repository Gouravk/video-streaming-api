package com.example.videostreaming;

import com.example.videostreaming.entity.Engagement;
import com.example.videostreaming.service.EngagementService;
import org.mockito.InjectMocks;
import com.example.videostreaming.dto.EngagementResponseDTO;
import com.example.videostreaming.entity.Video;
import com.example.videostreaming.repository.EngagementRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

        import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class EngagementServiceTest {

    @Mock
    private EngagementRepository engagementRepository;

    @InjectMocks
    private EngagementService engagementService;

    @Test
    void getEngagementStats_ShouldReturnCorrectData() {
        Video video = new Video();
        video.setId(1L);
        video.setTitle("Inception");

        Engagement engagement = new Engagement();
        engagement.setVideo(video);
        engagement.setImpressions(5);
        engagement.setViews(3);

        when(engagementRepository.findByVideoId(1L)).thenReturn(Optional.of(engagement));

        EngagementResponseDTO dto = engagementService.getEngagementStats(1L);

        assertThat(dto.getVideoId()).isEqualTo(1L);
        assertThat(dto.getImpressions()).isEqualTo(5);
        assertThat(dto.getViews()).isEqualTo(3);
    }

    @Test
    void getEngagementStats_ShouldThrowException_WhenVideoIdNotFound() {

        Long videoId = 99L;
        when(engagementRepository.findByVideoId(videoId))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> engagementService.getEngagementStats(videoId))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Engagement data not found for video id " + videoId);

        verify(engagementRepository, times(1)).findByVideoId(videoId);
    }


}