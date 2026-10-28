package com.fixmate.backend_application.repository;
import com.fixmate.backend_application.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findPaymentByProId(Long proId);
    List<Payment> findPaymentByCustomerId(Long customerId);
}
