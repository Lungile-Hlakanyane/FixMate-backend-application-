package com.fixmate.backend_application.serviceImp;
import com.fixmate.backend_application.dto.ServiceRequestCreateDTO;
import com.fixmate.backend_application.dto.ServiceRequestResponseDTO;
import com.fixmate.backend_application.entity.ServiceRequest;
import com.fixmate.backend_application.entity.User;
import com.fixmate.backend_application.enums.JobStatus;
import com.fixmate.backend_application.enums.RequestStatus;
import com.fixmate.backend_application.enums.ServiceCategory;
import com.fixmate.backend_application.enums.ServiceReviewStatus;
import com.fixmate.backend_application.exceptions.*;
import com.fixmate.backend_application.mapper.ServiceRequestMapper;
import com.fixmate.backend_application.repository.ServiceRequestRepository;
import com.fixmate.backend_application.repository.UserRepository;
import com.fixmate.backend_application.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceRequestServiceImp implements ServiceRequestService {

    @Autowired
    private ServiceRequestMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Override
    public ServiceRequestResponseDTO createRequest(ServiceRequestCreateDTO dto) {
        User customer = userRepository.findById(dto.getCustomerId()).orElseThrow(() ->
                new RuntimeException("Customer not found with id: " + dto.getCustomerId()));
        ServiceRequest entity = mapper.toEntity(dto);
        entity.setCustomer(customer);
        entity.setStatus(RequestStatus.PENDING);
        entity.setServiceReviewStatus(ServiceReviewStatus.APPROVE); //make sure when creating a request this is always APPROVED
        ServiceRequest saved = serviceRequestRepository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    @Override
    public ServiceRequestResponseDTO getById(Long id) {
        ServiceRequest serviceRequest = serviceRequestRepository.findById(id).orElseThrow(()->new RequestServiceNotFoundException(id));
        return mapper.toResponseDTO(serviceRequest);
    }

    @Override
    public List<ServiceRequestResponseDTO> getAllRequests() {
        return mapper.toDTOList(serviceRequestRepository.findAll());
    }

    @Override
    public List<ServiceRequestResponseDTO> getRequestByCustomer(Long customerId) {
        if(customerId==null){
            throw new UserNotFoundException(customerId);
        }
        return mapper.toDTOList(serviceRequestRepository.findByCustomerId(customerId));
    }

    @Override
    public List<ServiceRequestResponseDTO> getRequestsByPro(Long proId) {
        if(proId==null){
            throw new UserNotFoundException(proId);
        }
        return mapper.toDTOList(serviceRequestRepository.findByAssignedProId(proId));
    }

    @Override
    public ServiceRequestResponseDTO assignPro(Long requestId, Long proId) {
        User pro = userRepository.findById(proId).orElseThrow(()->new UserNotFoundException(proId));
        ServiceRequest request = serviceRequestRepository.findById(requestId).orElseThrow(()->new RequestServiceNotFoundException(proId));
        request.setAssignedPro(pro); //assign pro
        request.setStatus(RequestStatus.ASSIGNED); // assign field
        request.markAsConfirmed(); // mark as confirmed
        ServiceRequest assignedRequest = serviceRequestRepository.save(request); // then save
        return mapper.toResponseDTO(assignedRequest);
    }

    @Override
    public ServiceRequestResponseDTO updateStatus(Long requestId, RequestStatus status) {
        ServiceRequest request = serviceRequestRepository.findById(requestId).orElseThrow(()->new RequestServiceNotFoundException(requestId));
        request.setStatus(status);
        ServiceRequest updatedRequest = serviceRequestRepository.save(request);
        return mapper.toResponseDTO(updatedRequest);
    }

    @Override
    public void deleteRequest(Long id) {
        ServiceRequest request = serviceRequestRepository.findById(id).orElseThrow(()->new RequestServiceNotFoundException(id));
        serviceRequestRepository.deleteById(id);
    }

    @Override
    public long countTotalRequests() {
        return serviceRequestRepository.count();
    }

    @Override
    public ServiceRequestResponseDTO changeReviewStatus(Long id, ServiceReviewStatus reviewStatus) {
        ServiceRequest serviceRequest = serviceRequestRepository.findByServiceReviewStatus(reviewStatus).
                orElseThrow(()->new InvalidReviewStatusException(reviewStatus));
        serviceRequest.setServiceReviewStatus(reviewStatus);
        ServiceRequest updatedReviewStatus = serviceRequestRepository.save(serviceRequest);
        return mapper.toResponseDTO(updatedReviewStatus);
    }

    @Override
    public long countByJobStatus(JobStatus jobStatus) {
        return serviceRequestRepository.countByJobStatus(jobStatus);
    }

    @Override
    public ServiceRequestResponseDTO completeJob(Long requestId, JobStatus status) {
        ServiceRequest serviceRequest = serviceRequestRepository.findById(requestId).orElseThrow(()->new RequestServiceNotFoundException(requestId));
        serviceRequest.markAsComplete();
        ServiceRequest updated = serviceRequestRepository.save(serviceRequest);
        return mapper.toResponseDTO(updated);
    }

    @Override
    public long countByServiceCategory(ServiceCategory category) {
        return serviceRequestRepository.countByCategory(category);
    }

    @Override
    public ServiceRequestResponseDTO paidRequest(Long requestId, JobStatus status) {
        ServiceRequest serviceRequest = serviceRequestRepository.findById(requestId).orElseThrow(()->new RequestServiceNotFoundException(requestId));
        serviceRequest.markAsPaid();
        ServiceRequest paidRequest = serviceRequestRepository.save(serviceRequest);
        return mapper.toResponseDTO(paidRequest);
    }

    @Override
    public long countByAssignedProAndJobStatus(Long proId, JobStatus jobStatus) {
        User user = userRepository.findById(proId).orElseThrow(()->
                new UserNotFoundException(proId));
        //null check first
        if(jobStatus==null){
            throw new JobStatusNotFoundException("NULL");
        }
        // use == for enums, and safe equals
        if(jobStatus!=JobStatus.COMPLETED){
            throw new JobStatusNotFoundException(jobStatus);
        }
        return serviceRequestRepository.countByAssignedPro_IdAndJobStatus(proId,jobStatus);
    }

    @Override
    public long countProActiveJobs(Long proId, JobStatus jobStatus) {
        //check if the user exists
        User user = userRepository.findById(proId).orElseThrow(()->
                new UserNotFoundException(proId));
        //null check
        if(jobStatus ==null){
            throw new JobStatusNotFoundException("NULL");
        }
        //check current job status if these conditions are met we set to Active
        if(jobStatus==JobStatus.OPEN || jobStatus==JobStatus.CONFIRMED || jobStatus==JobStatus.PENDING || jobStatus==JobStatus.IN_PROGRESS){
            ServiceRequest serviceRequest = new ServiceRequest();
            serviceRequest.setJobStatus(JobStatus.ACTIVE);
            ServiceRequest updatedRequest = serviceRequestRepository.save(serviceRequest);
            mapper.toResponseDTO(updatedRequest);
        }
        return serviceRequestRepository.countByAssignedPro_IdAndJobStatus(proId, jobStatus);
    }

    @Override
    public Double getProTotalEarnings(Long proId) {
        User user = userRepository.findById(proId).orElseThrow(()->
                new UserNotFoundException(proId));
        return serviceRequestRepository.getTotalPaidAmountByProId(proId,JobStatus.PAID);
    }

    @Override
    public List<ServiceRequestResponseDTO> getPaidServiceRequestByPro(Long proId, JobStatus jobStatus) {
        User user = userRepository.findById(proId).orElseThrow(()->new UserNotFoundException(proId));
        return mapper.toDTOList(serviceRequestRepository.findByAssignedProIdAndJobStatus(proId, jobStatus));
    }

    @Override
    public Double getAmountAccumulatedThisMonth(Long proId) {
        LocalDate now = LocalDate.now();
        LocalDateTime start = now.withDayOfMonth(1).atStartOfDay();
        LocalDateTime end = now.withDayOfMonth(now.lengthOfMonth()).atTime(23,59,59);
        return serviceRequestRepository.sumBudgetByProIdAndStatusBetween(
                proId, JobStatus.COMPLETED, start, end
        );
    }

    @Override
    public List<ServiceRequestResponseDTO> getRequestsByProAndJobStatus(Long proId, JobStatus jobStatus) {
        User user = userRepository.findById(proId).orElseThrow(()->new UserNotFoundException(proId));
        return mapper.toDTOList(serviceRequestRepository.findByAssignedProIdAndJobStatus(proId, jobStatus));
    }

    @Override
    public Double getTotalEarningsThisMonth(Long proId) {
        User user = userRepository.findById(proId).orElseThrow(()->new UserNotFoundException(proId));
        return serviceRequestRepository.getTotalEarningsThisMonth(proId);
    }

    @Override
    public long getAwaitingPaymentCount(Long proId) {
        return serviceRequestRepository.countAwaitingPayment(proId);
    }

    @Override
    public Double getAwaitingPaymentTotal(Long proId) {
        return serviceRequestRepository.sumAwaitingPayment(proId);
    }

    @Override
    public List<ServiceRequestResponseDTO> getAwaitingPaymentJobs(Long proId) {
        return serviceRequestRepository.findByAssignedProIdAndJobStatus(proId, JobStatus.COMPLETED)
                .stream().map(mapper::toResponseDTO).toList();
    }
}
