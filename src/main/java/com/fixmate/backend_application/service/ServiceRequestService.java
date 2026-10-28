package com.fixmate.backend_application.service;
import com.fixmate.backend_application.dto.ServiceRequestCreateDTO;
import com.fixmate.backend_application.dto.ServiceRequestResponseDTO;
import com.fixmate.backend_application.enums.JobStatus;
import com.fixmate.backend_application.enums.RequestStatus;
import com.fixmate.backend_application.enums.ServiceCategory;
import com.fixmate.backend_application.enums.ServiceReviewStatus;
import java.util.List;

public interface ServiceRequestService {
    ServiceRequestResponseDTO createRequest(ServiceRequestCreateDTO dto);
    ServiceRequestResponseDTO getById(Long id);
    List<ServiceRequestResponseDTO> getAllRequests();
    List<ServiceRequestResponseDTO> getRequestByCustomer(Long customerId);
    List<ServiceRequestResponseDTO> getRequestsByPro(Long proId);
    ServiceRequestResponseDTO assignPro(Long requestId, Long proId);
    ServiceRequestResponseDTO updateStatus(Long requestId, RequestStatus status);
    void deleteRequest(Long id);
    long countTotalRequests();
    ServiceRequestResponseDTO changeReviewStatus(Long id, ServiceReviewStatus reviewStatus);
    long countByJobStatus(JobStatus jobStatus);
    ServiceRequestResponseDTO completeJob(Long requestId, JobStatus status);
    long countByServiceCategory(ServiceCategory category);
    ServiceRequestResponseDTO paidRequest(Long requestId, JobStatus status);
    long countByAssignedProAndJobStatus(Long proId, JobStatus jobStatus);
    long countProActiveJobs(Long proId, JobStatus jobStatus);
    Double getProTotalEarnings(Long proId);
    List<ServiceRequestResponseDTO> getPaidServiceRequestByPro(Long proId, JobStatus jobStatus);
    Double getAmountAccumulatedThisMonth(Long proId);
    List<ServiceRequestResponseDTO> getRequestsByProAndJobStatus(Long proId, JobStatus jobStatus);
    Double getTotalEarningsThisMonth(Long proId);

    //NEW 3 METHODS
    long getAwaitingPaymentCount(Long proId);
    Double getAwaitingPaymentTotal(Long proId);
    List<ServiceRequestResponseDTO> getAwaitingPaymentJobs(Long proId);
}
