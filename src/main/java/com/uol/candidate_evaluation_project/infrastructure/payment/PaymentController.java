package com.uol.candidate_evaluation_project.infrastructure.payment;

import com.uol.candidate_evaluation_project.application.payment.PaymentUseCase;
import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.infrastructure.payment.payload.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("PaymentController")
@RequestMapping("${api.candidate.evaluation.project.base.path}/${api.candidate.evaluation.project.base.version}/payments")
@Tag(name = "Payment")
public class PaymentController {
    private final PaymentUseCase paymentUseCase;
    private final PaymentDTOMapper paymentDTOMapper;

    public PaymentController(PaymentUseCase paymentUseCase, PaymentDTOMapper paymentDTOMapper) {
        this.paymentUseCase = paymentUseCase;
        this.paymentDTOMapper = paymentDTOMapper;
    }

    @PostMapping
    public ResponseEntity<CreatePaymentResponse> create(@RequestBody CreatePaymentRequest createPaymentRequest) {
        Payment payment = paymentUseCase.create(paymentDTOMapper.toPayment(createPaymentRequest));
        return ResponseEntity.ok().body(paymentDTOMapper.toCreateResponse(payment));
    }

    @GetMapping("/{billingCode}")
    public ResponseEntity<PaymentResponse> findById(@PathVariable("billingCode") String billingCode) {
        Payment payment = paymentUseCase.findById(billingCode);
        return ResponseEntity.ok(paymentDTOMapper.toResponse(payment));
    }

    @PutMapping("/{billingCode}")
    public ResponseEntity<UpdatePaymentResponse> update(@PathVariable("billingCode") String billingCode,
                                                        @RequestBody UpdatePaymentRequest updatePaymentRequest) {
        Payment payment = paymentUseCase.update(billingCode, paymentDTOMapper.toPayment(updatePaymentRequest));
        return ResponseEntity.ok(paymentDTOMapper.toUpdateResponse(payment));
    }

    @DeleteMapping("/{billingCode}")
    public ResponseEntity<Void> delete(@PathVariable("billingCode") String billingCode) {
        paymentUseCase.delete(billingCode);
        return ResponseEntity.noContent().build();
    }
}
