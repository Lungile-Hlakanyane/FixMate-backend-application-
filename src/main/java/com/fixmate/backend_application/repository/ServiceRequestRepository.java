package com.fixmate.backend_application.repository;
import com.fixmate.backend_application.entity.ServiceRequest;
import com.fixmate.backend_application.enums.JobStatus;
import com.fixmate.backend_application.enums.RequestStatus;
import com.fixmate.backend_application.enums.ServiceCategory;
import com.fixmate.backend_application.enums.ServiceReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByCustomerId(Long customerId);
    List<ServiceRequest> findByAssignedProId(Long id);
    List<ServiceRequest> findByStatus(RequestStatus status);
    List<ServiceRequest> findByCategory(ServiceCategory category);
    Optional<ServiceRequest> findByServiceReviewStatus(ServiceReviewStatus reviewStatus);
    long countByJobStatus(JobStatus jobStatus); //also use this method to count paid jobs
    long countByCategory(ServiceCategory category);
    long countByAssignedPro_IdAndJobStatus(Long proId, JobStatus jobStatus);
    @Query("SELECT COALESCE(SUM(sr.budget), 0) FROM ServiceRequest sr WHERE sr.assignedPro.id = :proId AND sr.jobStatus = :jobStatus")
    Double getTotalPaidAmountByProId(@Param("proId") Long proId, @Param("jobStatus") JobStatus jobStatus);
    List<ServiceRequest> findByAssignedProIdAndJobStatus(Long proId, JobStatus jobStatus);

    @Query("SELECT COALESCE(SUM(s.budget), 0) FROM ServiceRequest s " +
            "WHERE s.assignedPro.id = :proId " +
            "AND s.jobStatus = com.fixmate.backend_application.enums.JobStatus.PAID " +
            "AND YEAR(s.updatedAt) = YEAR(CURRENT_DATE) " +
            "AND MONTH(s.updatedAt) = MONTH(CURRENT_DATE)")
    Double getTotalEarningsThisMonth(@Param("proId") Long proId);

    @Query("SELECT COALESCE(SUM(s.budget), 0) FROM ServiceRequest s " +
            "WHERE s.assignedPro.id = :proId " +
            "AND s.jobStatus = :status " +
            "AND s.updatedAt BETWEEN :start AND :end")
    Double sumBudgetByProIdAndStatusBetween(
            @Param("proId") Long proId,
            @Param("status") JobStatus status,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    // count how many are awaiting payment
    @Query("SELECT COUNT(s) FROM ServiceRequest s WHERE s.assignedPro.id = :proId AND s.jobStatus = com.fixmate.backend_application.enums.JobStatus.COMPLETED")
    Long countAwaitingPayment(@Param("proId") Long proId);

    // sum amount awaiting
    @Query("SELECT COALESCE(SUM(s.budget), 0) FROM ServiceRequest s WHERE s.assignedPro.id = :proId AND s.jobStatus = com.fixmate.backend_application.enums.JobStatus.COMPLETED")
    Double sumAwaitingPayment(@Param("proId") Long proId);
}
