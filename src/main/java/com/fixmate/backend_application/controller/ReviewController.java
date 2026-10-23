package com.fixmate.backend_application.controller;
import com.fixmate.backend_application.dto.ReviewRequestDTO;
import com.fixmate.backend_application.dto.ReviewResponseDTO;
import com.fixmate.backend_application.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    //create
    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(@Valid @RequestBody ReviewRequestDTO dto){
        return new ResponseEntity<>(reviewService.createReview(dto), HttpStatus.CREATED);
    }

    //getById
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> getReviewById(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    //get all reviews
    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviews(){
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    //update by id
    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReviewById(@Valid @PathVariable Long id,
                                                              @RequestBody ReviewRequestDTO dto){
        return ResponseEntity.ok(reviewService.updateReviewById(id, dto));
    }

    //delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReviewById(@PathVariable Long id){
        reviewService.deleteReviewById(id);
        return ResponseEntity.noContent().build();
    }
}
