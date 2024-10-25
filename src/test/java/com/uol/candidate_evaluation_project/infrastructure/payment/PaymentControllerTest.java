package com.uol.candidate_evaluation_project.infrastructure.payment;

import com.uol.candidate_evaluation_project.application.payment.PaymentUseCase;
import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import com.uol.candidate_evaluation_project.infrastructure.payment.payload.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @MockBean
    private PaymentUseCase paymentUseCase;

    @MockBean
    private PaymentDTOMapper paymentDTOMapper;

    @InjectMocks
    private PaymentController paymentController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Test POST /payments - Create Payment")
    void testCreatePayment() throws Exception {
        Payment payment = new Payment("billingCode123", BigDecimal.valueOf(100.00), PaymentStatus.EXCESS);
        CreatePaymentResponse createPaymentResponse = new CreatePaymentResponse("billingCode123",
                BigDecimal.valueOf(2.2));

        when(paymentDTOMapper.toPayment(any(CreatePaymentRequest.class))).thenReturn(payment);
        when(paymentUseCase.create(payment)).thenReturn(payment);
        when(paymentDTOMapper.toCreateResponse(payment)).thenReturn(createPaymentResponse);

        MockHttpServletRequestBuilder content = post("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"billingCode\": \"billingCode123\", \"amount\": 100.00}");

        ResultActions resultActions = mockMvc.perform(content)
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.billingCode").value("billingCode123"));
    }

    @Test
    @DisplayName("Test GET /payments/{billingCode} - Find Payment by ID")
    void testFindPaymentById() throws Exception {
        String billingCode = "billingCode123";
        Payment payment = new Payment(billingCode, BigDecimal.valueOf(100.00), PaymentStatus.EXCESS);
        PaymentResponse paymentResponse = new PaymentResponse(billingCode, BigDecimal.valueOf(100.00),
                PaymentStatus.EXCESS);

        when(paymentUseCase.findById(billingCode)).thenReturn(payment);
        when(paymentDTOMapper.toResponse(payment)).thenReturn(paymentResponse);

        ResultActions resultActions = mockMvc.perform(get("/api/v1/payments/{billingCode}", billingCode))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.billingCode").value(billingCode));
    }

    @Test
    @DisplayName("Test PUT /payments/{billingCode} - Update Payment")
    void testUpdatePayment() throws Exception {
        String billingCode = "billingCode123";
        Payment payment = new Payment(billingCode, BigDecimal.valueOf(150.00), PaymentStatus.EXCESS);
        UpdatePaymentResponse updatePaymentResponse = new UpdatePaymentResponse(billingCode, BigDecimal.valueOf(150.00),
                PaymentStatus.EXCESS);

        when(paymentDTOMapper.toPayment(any(UpdatePaymentRequest.class))).thenReturn(payment);
        when(paymentUseCase.update(eq(billingCode), any(Payment.class))).thenReturn(payment);
        when(paymentDTOMapper.toUpdateResponse(payment)).thenReturn(updatePaymentResponse);

        MockHttpServletRequestBuilder content = put("/api/v1/payments/{billingCode}", billingCode)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\": 150.00}");

        ResultActions resultActions = mockMvc.perform(content)
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.billingCode").value(billingCode));
    }

    @Test
    @DisplayName("Test DELETE /payments/{billingCode} - Delete Payment")
    void testDeletePayment() throws Exception {
        String billingCode = "billingCode123";

        doNothing().when(paymentUseCase).delete(billingCode);

        MockHttpServletRequestBuilder delete = delete("/api/v1/payments/{billingCode}", billingCode);

        mockMvc.perform(delete)
                .andExpect(status().isNoContent());
    }
}