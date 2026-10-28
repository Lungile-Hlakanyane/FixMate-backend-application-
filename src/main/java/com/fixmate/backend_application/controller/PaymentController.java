package com.fixmate.backend_application.controller;
import com.fixmate.backend_application.dto.PaymentRequestDTO;
import com.fixmate.backend_application.dto.PaymentResponseDTO;
import com.fixmate.backend_application.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    //TESTED
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@Valid @RequestBody PaymentRequestDTO dto){
        return new ResponseEntity<>(paymentService.createPayment(dto), HttpStatus.CREATED);
    }

    //TESTED
    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments(){
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    //TESTED
    @GetMapping("/customer-payments/{customerId}")
    public ResponseEntity<List<PaymentResponseDTO>> getAllPaymentsByCustomerId(@PathVariable Long customerId){
        return ResponseEntity.ok(paymentService.getPaymentsByCustomerId(customerId));
    }

    //TESTED
    @GetMapping("/pro-payments/{proId}")
    public ResponseEntity<List<PaymentResponseDTO>> getAllPaymentsByProId(@PathVariable Long proId){
        return ResponseEntity.ok(paymentService.getPaymentsByProId(proId));
    }

    //TESTED
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long paymentId){
        paymentService.deleteById(paymentId);
        return ResponseEntity.noContent().build();
    }

    //TESTED
    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDTO> updatePaymentById(@PathVariable Long paymentId,
                                                                @RequestBody PaymentRequestDTO dto){
        return ResponseEntity.ok(paymentService.updatePaymentById(paymentId, dto));
    }
}
