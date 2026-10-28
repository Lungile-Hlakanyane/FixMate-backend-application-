package com.fixmate.backend_application.mapper;
import com.fixmate.backend_application.dto.PaymentRequestDTO;
import com.fixmate.backend_application.dto.PaymentResponseDTO;
import com.fixmate.backend_application.entity.Payment;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {

    //1. toEntity
    public Payment toEntity(PaymentRequestDTO dto){
        if(dto==null){return null;}
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setTypeOfPayment(dto.getTypeOfPayment());
        payment.setAmount(dto.getAmount());
        payment.setProId(dto.getProId());
        payment.setCustomerId(dto.getCustomerId());
        payment.setJobPaidId(dto.getJobPaidId());
        payment.setDateOfPayment(dto.getDateOfPayment());
        return payment;
    }

    //2. toDTO
    public PaymentResponseDTO toDTO(Payment payment){
        if(payment==null){return null;}
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(payment.getId());
        dto.setTypeOfPayment(payment.getTypeOfPayment());
        dto.setAmount(payment.getAmount());
        dto.setProId(payment.getProId());
        dto.setCustomerId(payment.getCustomerId());
        dto.setJobPaidId(payment.getJobPaidId());
        dto.setDateOfPayment(payment.getDateOfPayment());
        return dto;
    }

    //3. toDTOList
    public List<PaymentResponseDTO> toDTOList(List<Payment> payments){
        if(payments.isEmpty()){
            return Collections.emptyList();
        }
        return payments.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
