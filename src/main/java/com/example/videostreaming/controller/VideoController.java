package com.example.videostreaming.controller;

import com.example.videostreaming.dto.*;
import com.example.videostreaming.entity.Video;
import com.example.videostreaming.service.EngagementService;
import com.example.videostreaming.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;
    private final EngagementService engagementService;

    public VideoController(VideoService videoService,EngagementService engagementService) {
        this.videoService = videoService;
        this.engagementService=engagementService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> publish(@RequestBody VideoRequestDTO videoRequestDTO) {
        Video publishedVideo=videoService.publishVideo(videoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Video Published Successfully",publishedVideo.getId()));

    }

    @PutMapping("/{id}/metadata")
    public ResponseEntity<VideoResponseDTO> updateVideoMetadata(
            @PathVariable Long id,
            @RequestBody VideoRequestDTO dto) {
        VideoResponseDTO updatedVideo = videoService.createOrUpdateVideoMetadata(id, dto);
        return ResponseEntity.ok(updatedVideo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("Video has been deleted successfully",id));
    }

    @GetMapping("/{id}/load")
    public ResponseEntity<VideoResponseDTO> loadVideo(@PathVariable Long id) {

        return ResponseEntity.ok(videoService.loadVideo(id));
    }

    @GetMapping("/{id}/play")
    public ResponseEntity<String> playVideo(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.playVideo(id));
    }


    @GetMapping
    public ResponseEntity<List<VideoSubsetDTO>> listVideos() {
        return ResponseEntity.ok(videoService.listAvailableVideos());
    }

    @GetMapping("/search")
    public ResponseEntity<List<VideoSubsetDTO>> searchVideosByDirector(
            @RequestParam String director) {
        return ResponseEntity.ok(videoService.searchByDirector(director));
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<EngagementResponseDTO> getEngagementStats(@PathVariable Long videoId) {
        return ResponseEntity.ok(engagementService.getEngagementStats(videoId));
    }


}
