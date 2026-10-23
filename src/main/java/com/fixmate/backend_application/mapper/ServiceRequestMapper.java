package com.fixmate.backend_application.mapper;
import com.fixmate.backend_application.dto.ServiceRequestCreateDTO;
import com.fixmate.backend_application.dto.ServiceRequestResponseDTO;
import com.fixmate.backend_application.entity.ServiceRequest;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceRequestMapper {

    public ServiceRequest toEntity(ServiceRequestCreateDTO dto){
        if(dto==null){return null;}
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setTitle(dto.getTitle());
        serviceRequest.setDescription(dto.getDescription());
        serviceRequest.setCategory(dto.getCategory());
        serviceRequest.setLocation(dto.getLocation());
        serviceRequest.setScheduledDate(dto.getScheduledDate());
        serviceRequest.setBudget(dto.getBudget());
        serviceRequest.setUrgency(dto.getUrgency());
        serviceRequest.setJobStatus(dto.getJobStatus());
        serviceRequest.setServiceReviewStatus(dto.getServiceReviewStatus());
        return serviceRequest;
    }

    public ServiceRequestResponseDTO toResponseDTO(ServiceRequest entity) {
        if(entity==null){return null;}
        ServiceRequestResponseDTO dto = new ServiceRequestResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCategory(entity.getCategory());
        dto.setLocation(entity.getLocation());
        dto.setStatus(entity.getStatus() != null ? entity.getStatus().name() : null);
        dto.setScheduledDate(entity.getScheduledDate());
        dto.setBudget(entity.getBudget());
        dto.setUrgency(entity.getUrgency());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setJobStatus(entity.getJobStatus());
        dto.setServiceReviewStatus(entity.getServiceReviewStatus());
        if (entity.getCustomer() != null) {
            dto.setCustomerId(entity.getCustomer().getId());
            dto.setCustomerName(entity.getCustomer().getFullName());
        }
        if (entity.getAssignedPro() != null) {
            dto.setProId(entity.getAssignedPro().getId());
            dto.setProName(entity.getAssignedPro().getFullName());
        }
        return dto;
    }
    
    public List<ServiceRequestResponseDTO> toDTOList(List<ServiceRequest> requests){
        if(requests.isEmpty()){return Collections.emptyList();}
        return requests.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }
}
