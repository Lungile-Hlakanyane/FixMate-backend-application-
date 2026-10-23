package com.fixmate.backend_application.mapper;
import com.fixmate.backend_application.dto.ReviewRequestDTO;
import com.fixmate.backend_application.dto.ReviewResponseDTO;
import com.fixmate.backend_application.entity.Review;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {

    public Review toEntity(ReviewRequestDTO dto){
        if(dto==null) return null;
        Review review = new Review();
        review.setProId(dto.getProId());
        review.setCustomerId(dto.getCustomerId());
        review.setStars(dto.getStars());
        review.setComment(dto.getComment());
        review.setServiceRequestId(dto.getServiceRequestId());
        review.setExperience(dto.getExperience());
        return review;
    }

    public ReviewResponseDTO toDTO(Review review){
        if(review==null) return null;
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setId(review.getId());
        dto.setProId(review.getProId());
        dto.setCustomerId(review.getCustomerId());
        dto.setStars(review.getStars());
        dto.setComment(review.getComment());
        dto.setServiceRequestId(review.getServiceRequestId());
        dto.setExperience(review.getExperience());
        dto.setCreated(review.getCreated());
        return dto;
    }

    public List<ReviewResponseDTO> toDTOList(List<Review> reviews){
        if(reviews==null || reviews.isEmpty()) return Collections.emptyList();
        return reviews.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
