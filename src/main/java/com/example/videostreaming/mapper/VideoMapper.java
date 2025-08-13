package com.example.videostreaming.mapper;

import com.example.videostreaming.dto.VideoRequestDTO;
import com.example.videostreaming.dto.VideoResponseDTO;
import com.example.videostreaming.dto.VideoSubsetDTO;
import com.example.videostreaming.entity.Video;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoMapper {

    public Video dtoToEntity(VideoRequestDTO videoRequestDTO){
        Video video=new Video();

        video.setTitle(videoRequestDTO.getTitle());
        video.setSynopsis(videoRequestDTO.getSynopsis());
        video.setDirector(videoRequestDTO.getDirector());
        video.setMain_cast(String.join(",", videoRequestDTO.getMain_cast()));
        video.setYearOfRelease(videoRequestDTO.getYearOfRelease());
        video.setGenre(videoRequestDTO.getGenre());
        video.setRunningTime(videoRequestDTO.getRunningTime());
        return video;

    }

    public VideoResponseDTO entityToDTO(Video video){
        return new VideoResponseDTO(
                video.getId(),
                video.getTitle(),
                video.getSynopsis(),
                video.getDirector(),
                video.getMain_cast(),
                video.getYearOfRelease(),
                video.getGenre(),
                video.getRunningTime(),
                video.getImpressions(),
                video.getViews()
        );
    }

    public VideoSubsetDTO entityToSubsetDTO(Video video){
        List<String> listOfCast=List.of();
        if(video.getMain_cast()!=null && !video.getMain_cast().isEmpty()){
             listOfCast=List.of(video.getMain_cast().split(","));
        }

        return new VideoSubsetDTO(video.getTitle(), video.getDirector(), video.getGenre(), listOfCast.get(0), video.getRunningTime());
    }




}
