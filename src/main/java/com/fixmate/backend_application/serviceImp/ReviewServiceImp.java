package com.fixmate.backend_application.serviceImp;
import com.fixmate.backend_application.dto.ReviewRequestDTO;
import com.fixmate.backend_application.dto.ReviewResponseDTO;
import com.fixmate.backend_application.entity.Review;
import com.fixmate.backend_application.entity.User;
import com.fixmate.backend_application.exceptions.ReviewNotFoundException;
import com.fixmate.backend_application.mapper.ReviewMapper;
import com.fixmate.backend_application.repository.ReviewRepository;
import com.fixmate.backend_application.repository.UserRepository;
import com.fixmate.backend_application.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO dto) {
        // validate users exist
        userRepository.findById(dto.getProId()).orElseThrow(()->
                new RuntimeException("Pro with id: "+ dto.getProId() + " not found"));
        userRepository.findById(dto.getCustomerId()).orElseThrow(()->
                new RuntimeException("Customer not found"));
        Review review = reviewMapper.toEntity(dto);
        Review saved = reviewRepository.save(review);
        return reviewMapper.toDTO(saved); // use toDTO
    }

    @Override
    public ReviewResponseDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(()->new ReviewNotFoundException(id));
        return reviewMapper.toDTO(review);
    }

    @Override
    public List<ReviewResponseDTO> getAllReviews() {
        return reviewMapper.toDTOList(reviewRepository.findAll());
    }

    @Override
    public void deleteReviewById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(()->new ReviewNotFoundException(id));
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewResponseDTO updateReviewById(Long id, ReviewRequestDTO dto) {
        Review review = reviewRepository.findById(id).orElseThrow(()->new ReviewNotFoundException(id));
        review.setStars(dto.getStars());
        review.setComment(dto.getComment());
        review.setExperience(dto.getExperience());
        Review updated = reviewRepository.save(review);
        return reviewMapper.toDTO(updated);
    }
}
