package com.example.videostreaming.service;

import com.example.videostreaming.dto.VideoRequestDTO;
import com.example.videostreaming.dto.VideoResponseDTO;
import com.example.videostreaming.dto.VideoSubsetDTO;
import com.example.videostreaming.entity.Engagement;
import com.example.videostreaming.entity.Video;
import com.example.videostreaming.mapper.VideoMapper;
import com.example.videostreaming.repository.EngagementRepository;
import com.example.videostreaming.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {
    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    private final EngagementRepository engagementRepository;

    public VideoService(VideoRepository videoRepository, VideoMapper videoMapper,EngagementRepository engagementRepository) {
        this.videoRepository = videoRepository;
        this.videoMapper=videoMapper;
        this.engagementRepository=engagementRepository;
    }

    public Video publishVideo(VideoRequestDTO videoRequestDTO) {
        Video video=videoMapper.dtoToEntity(videoRequestDTO);

        return videoRepository.save(video);
    }

    public VideoResponseDTO createOrUpdateVideoMetadata(Long id,VideoRequestDTO videoRequestDTO) {
        Video video;
        if(id!=null){
             video=videoMapper.dtoToEntity(videoRequestDTO);
        }else{
             video=new Video();
        }
        Video savedVideo= videoRepository.save(video);

        return videoMapper.entityToDTO(savedVideo);


    }

    public VideoResponseDTO loadVideo(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        Engagement engagement = engagementRepository.findByVideoId(id)
                .orElseThrow(() -> new RuntimeException("Engagement record not found"));

        engagement.incrementImpressions();
        engagementRepository.save(engagement);

        return videoMapper.entityToDTO(video);
    }

    public String playVideo(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        Engagement engagement = engagementRepository.findByVideoId(id)
                .orElseThrow(() -> new RuntimeException("Engagement record not found"));

        engagement.incrementViews();
        engagementRepository.save(engagement);

        return "Streaming content for: " + video.getTitle();
    }


    public List<VideoSubsetDTO> listAvailableVideos() {
        List<Video> listOfVideos=videoRepository.findByDeletedFalse();
        List<VideoSubsetDTO> listOfVideoSubset = new ArrayList<>();
        for(Video video:listOfVideos){
            listOfVideoSubset.add(videoMapper.entityToSubsetDTO(video));
        }
        return listOfVideoSubset;
    }

    public List<VideoSubsetDTO> searchByDirector(String director) {
        List<VideoSubsetDTO> videos = videoRepository.findAll().stream()
                .filter(v -> !v.isDeleted())
                .filter(v -> v.getDirector().toLowerCase().contains(director.toLowerCase()))
                .map(v -> new VideoSubsetDTO(
                        v.getTitle(),
                        v.getDirector(),
                        v.getMain_cast() != null && !v.getMain_cast().isEmpty()
                                ? v.getMain_cast().split(",")[0].trim()
                                : null,
                        v.getGenre(),
                        v.getRunningTime()
                ))
                .toList();

        return videos;
    }

    public void deleteVideo(Long id) {
        Video v = videoRepository.findById(id).orElse(null);
        if (v != null) {
            v.setDeleted(true);
            videoRepository.save(v);
        }
    }


}
