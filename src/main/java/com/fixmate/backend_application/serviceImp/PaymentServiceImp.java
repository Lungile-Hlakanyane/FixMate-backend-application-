package com.fixmate.backend_application.serviceImp;
import com.fixmate.backend_application.dto.PaymentRequestDTO;
import com.fixmate.backend_application.dto.PaymentResponseDTO;
import com.fixmate.backend_application.entity.Payment;
import com.fixmate.backend_application.entity.ServiceRequest;
import com.fixmate.backend_application.entity.User;
import com.fixmate.backend_application.enums.JobStatus;
import com.fixmate.backend_application.exceptions.PaymentNotFoundException;
import com.fixmate.backend_application.exceptions.RequestServiceNotFoundException;
import com.fixmate.backend_application.exceptions.UserNotFoundException;
import com.fixmate.backend_application.mapper.PaymentMapper;
import com.fixmate.backend_application.repository.PaymentRepository;
import com.fixmate.backend_application.repository.ServiceRequestRepository;
import com.fixmate.backend_application.repository.UserRepository;
import com.fixmate.backend_application.service.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    @Transactional
    public PaymentResponseDTO createPayment(PaymentRequestDTO dto) {
        Payment payment = paymentMapper.toEntity(dto);
        if(payment.getDateOfPayment()==null){
            payment.setDateOfPayment(LocalDateTime.now());
        }
        Payment createdPayment = paymentRepository.save(payment);
        ServiceRequest serviceRequest = serviceRequestRepository.findById(dto.getJobPaidId()).
                orElseThrow(()-> new RequestServiceNotFoundException(dto.getJobPaidId()));
        serviceRequest.setJobStatus(JobStatus.PAID);
        serviceRequestRepository.save(serviceRequest);
        return paymentMapper.toDTO(createdPayment);
    }

    @Override
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentMapper.toDTOList(paymentRepository.findAll());
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByProId(Long proId) {
        User user = userRepository.findById(proId).orElseThrow(()->new UserNotFoundException(proId));
        return paymentMapper.toDTOList(paymentRepository.findPaymentByProId(proId));
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByCustomerId(Long customerId) {
        User user = userRepository.findById(customerId).orElseThrow(()->new UserNotFoundException(customerId));
        return paymentMapper.toDTOList(paymentRepository.findPaymentByCustomerId(customerId));
    }

    @Override
    public void deleteById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()->new PaymentNotFoundException(paymentId));
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public PaymentResponseDTO updatePaymentById(Long paymentId, PaymentRequestDTO dto) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()->new PaymentNotFoundException(paymentId));
        payment.setTypeOfPayment(dto.getTypeOfPayment());
        Payment updatedPayment = paymentRepository.save(payment);
        return paymentMapper.toDTO(updatedPayment);
    }
}
