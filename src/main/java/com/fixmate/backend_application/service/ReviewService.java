package com.fixmate.backend_application.service;
import com.fixmate.backend_application.dto.ReviewRequestDTO;
import com.fixmate.backend_application.dto.ReviewResponseDTO;
import java.util.List;

public interface ReviewService {

    //create
    ReviewResponseDTO createReview(ReviewRequestDTO dto);

    //getReviewById
    ReviewResponseDTO getReviewById(Long id);

    //getAllReviews
    List<ReviewResponseDTO> getAllReviews();

    //deleteReviewById
    void deleteReviewById(Long id);

    //updateReviewById
    ReviewResponseDTO updateReviewById(Long id, ReviewRequestDTO dto);
}
