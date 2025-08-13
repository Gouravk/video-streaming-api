package com.example.videostreaming.repository;

import com.example.videostreaming.dto.VideoSubsetDTO;
import com.example.videostreaming.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByDeletedFalse();
}
