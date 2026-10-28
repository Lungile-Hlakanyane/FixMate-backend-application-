package com.fixmate.backend_application.controller;
import com.fixmate.backend_application.dto.ServiceRequestCreateDTO;
import com.fixmate.backend_application.dto.ServiceRequestResponseDTO;
import com.fixmate.backend_application.entity.ServiceRequest;
import com.fixmate.backend_application.enums.JobStatus;
import com.fixmate.backend_application.enums.RequestStatus;
import com.fixmate.backend_application.enums.ServiceCategory;
import com.fixmate.backend_application.enums.ServiceReviewStatus;
import com.fixmate.backend_application.service.ServiceRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/service-requests")
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestService service;

    @PostMapping
    public ResponseEntity<ServiceRequestResponseDTO> createRequest(@Valid @RequestBody ServiceRequestCreateDTO dto){
        return new ResponseEntity<>(service.createRequest(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequestResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ServiceRequestResponseDTO>> getAllRequests(){
        return ResponseEntity.ok(service.getAllRequests());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ServiceRequestResponseDTO>> getRequestsByCustomer(@PathVariable Long customerId){
        return ResponseEntity.ok(service.getRequestByCustomer(customerId));
    }

    @GetMapping("/pro/{proId}")
    public ResponseEntity<List<ServiceRequestResponseDTO>> getRequestsByPro(@PathVariable Long proId){
        return ResponseEntity.ok(service.getRequestsByPro(proId));
    }

    @PutMapping("/{requestId}/assign/{proId}")
    public ResponseEntity<ServiceRequestResponseDTO> assignPro(
            @PathVariable Long requestId,
            @PathVariable Long proId){
        return ResponseEntity.ok(service.assignPro(requestId, proId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ServiceRequestResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam RequestStatus status){
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalRequests(){
        return ResponseEntity.ok(service.countTotalRequests());
    }

    @PutMapping("/{id}/reviewStatus/{reviewStatus}")
    public ResponseEntity<ServiceRequestResponseDTO> changeReviewStatus(@PathVariable Long id,
                                                                       @PathVariable ServiceReviewStatus serviceReviewStatus){
        return ResponseEntity.ok(service.changeReviewStatus(id,serviceReviewStatus));
    }

    @GetMapping("/countByJobStatus/{jobStatus}")
    public ResponseEntity<Long> countByJobStatus(@PathVariable JobStatus jobStatus){
        return ResponseEntity.ok(service.countByJobStatus(jobStatus));
    }

    @GetMapping("/countByJobCategory/{jobCategory}")
    public ResponseEntity<Long> countByJobCategory(@PathVariable ServiceCategory jobCategory){
        return ResponseEntity.ok(service.countByServiceCategory(jobCategory));
    }

    @PutMapping("/requests/{requestId}")
    public ResponseEntity<ServiceRequestResponseDTO> completeJob(@PathVariable Long requestId,
                                                                 @PathVariable JobStatus status){
        return ResponseEntity.ok(service.completeJob(requestId, status));
    }

    @PutMapping("/pay/{requestId}")
    public ResponseEntity<ServiceRequestResponseDTO> payPro(@PathVariable Long requestId,
                                                            @PathVariable JobStatus status){
        return ResponseEntity.ok(service.paidRequest(requestId, status));
    }

    @GetMapping("/total-completed-jobs/{proId}/{jobStatus}")
    public ResponseEntity<Long> getTotalCompletedJobByProId(@PathVariable Long proId,
                                                            @PathVariable JobStatus jobStatus){
        return ResponseEntity.ok(service.countByAssignedProAndJobStatus(proId, jobStatus));
    }

    @GetMapping("/active-jobs/{proId}/{jobStatus}")
    public ResponseEntity<Long> getActiveJobsForPro(@PathVariable Long proId,
                                                    @PathVariable JobStatus jobStatus){
        return ResponseEntity.ok(service.countProActiveJobs(proId, jobStatus));
    }

    @GetMapping("/pro/{proId}/total-paid")
    public ResponseEntity<Double> getTotalPaid(@PathVariable Long proId){
        Double total = service.getProTotalEarnings(proId);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/pro/{proId}/this-month")
    public ResponseEntity<Double> getThisMonth(@PathVariable Long proId) {
        Double total = service.getTotalEarningsThisMonth(proId);
        return ResponseEntity.ok(total); // returns 1000, not {totalThisMonth: 1000}
    }

    @GetMapping("/pro/{proId}/status/{jobStatus}")
    public ResponseEntity<List<ServiceRequestResponseDTO>> getPaidServiceRequestForPro(
            @PathVariable Long proId, @PathVariable JobStatus jobStatus){
        return ResponseEntity.ok(service.getPaidServiceRequestByPro(proId, jobStatus));
    }

    @GetMapping("/pro/{proId}/awaiting-payment/count")
    public ResponseEntity<Long> getAwaitingCount(@PathVariable Long proId){
        return ResponseEntity.ok(service.getAwaitingPaymentCount(proId));
    }

    @GetMapping("/pro/{proId}/awaiting-payment/total")
    public ResponseEntity<Double> getAwaitingTotal(@PathVariable Long proId){
        return ResponseEntity.ok(service.getAwaitingPaymentTotal(proId));
    }

    @GetMapping("/pro/{proId}/awaiting-payment")
    public ResponseEntity<List<ServiceRequestResponseDTO>> getAwaitingJobs(@PathVariable Long proId){
        return ResponseEntity.ok(service.getAwaitingPaymentJobs(proId));
    }
}
