package com.example.videostreaming;


import com.example.videostreaming.dto.VideoRequestDTO;
import com.example.videostreaming.entity.Video;
import com.example.videostreaming.mapper.VideoMapper;
import com.example.videostreaming.repository.VideoRepository;
import com.example.videostreaming.service.VideoService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.dao.DataIntegrityViolationException;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private VideoService videoService;

    @Mock
    private VideoMapper videoMapper;

    @Test
    void publishVideo_ShouldSaveAndReturnVideo() {
        VideoRequestDTO dto = new VideoRequestDTO("ABC", "Action-packed thriller",
                "Priyadarshini", "Akshay Kumar,Govinda", 2000, "thriller", 120);


        Video video = new Video();
        video.setTitle("ABC");
        video.setDirector("Priyadarshini");
        when(videoRepository.save(any(Video.class))).thenAnswer(i -> i.getArgument(0));
        when(videoMapper.dtoToEntity(dto)).thenReturn(video);
        Video saved = videoService.publishVideo(dto);

        ArgumentCaptor<Video> captor = ArgumentCaptor.forClass(Video.class);
        verify(videoRepository).save(captor.capture());
        assertThat(captor.getValue().getTitle()).isEqualTo("ABC");
        assertThat(saved.getDirector()).isEqualTo("Priyadarshini");
    }

    @Test
    void publishVideo_ShouldThrowException_WhenRepositoryFails() {

        VideoRequestDTO dto = new VideoRequestDTO("ABC", "Action-packed thriller",
                "Priyadarshini", "Akshay Kumar,Govinda", 2000, "thriller", 120);

        Video video = new Video();
        video.setTitle("ABC");
        video.setDirector("Priyadarshini");

        when(videoMapper.dtoToEntity(dto)).thenReturn(video);
        when(videoRepository.save(any(Video.class)))
                .thenThrow(new DataIntegrityViolationException("Database error occurred"));

        assertThatThrownBy(() -> videoService.publishVideo(dto))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("Database error occurred");

        verify(videoMapper).dtoToEntity(dto);
        verify(videoRepository).save(any(Video.class));
    }
}

