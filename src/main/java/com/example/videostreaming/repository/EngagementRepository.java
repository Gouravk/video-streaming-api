package com.example.videostreaming.repository;

import com.example.videostreaming.entity.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EngagementRepository extends JpaRepository<Engagement,Long> {

    Optional<Engagement> findByVideoId(Long videoId);
}
