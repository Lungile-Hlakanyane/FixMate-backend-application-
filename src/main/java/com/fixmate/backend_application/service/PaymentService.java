package com.fixmate.backend_application.service;
import com.fixmate.backend_application.dto.PaymentRequestDTO;
import com.fixmate.backend_application.dto.PaymentResponseDTO;
import java.util.List;

public interface PaymentService {

    PaymentResponseDTO createPayment(PaymentRequestDTO dto);
    List<PaymentResponseDTO> getAllPayments();
    List<PaymentResponseDTO> getPaymentsByProId(Long proId);
    List<PaymentResponseDTO> getPaymentsByCustomerId(Long customerId);
    void deleteById(Long paymentId);
    PaymentResponseDTO updatePaymentById(Long paymentId, PaymentRequestDTO dto);
}
