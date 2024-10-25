package com.uol.candidate_evaluation_project.infrastructure.seller;

import com.uol.candidate_evaluation_project.application.seller.SellerUseCase;
import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import com.uol.candidate_evaluation_project.domain.seller.Seller;
import com.uol.candidate_evaluation_project.infrastructure.seller.payload.*;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SellerController.class)
class SellerControllerTest {

    @MockBean
    private SellerUseCase sellerUseCase;

    @MockBean
    private SellerDTOMapper sellerEntityMapper;

    @InjectMocks
    private SellerController sellerController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Test POST /sellers - Create Seller")
    void testCreateSeller() throws Exception {
        Seller seller = new Seller("code123", List.of(new Payment("teste1", BigDecimal.valueOf(4.4),
                PaymentStatus.EXCESS)));
        CreateSellerResponse createSellerResponse = new CreateSellerResponse("code123");

        when(sellerEntityMapper.toSeller(any(CreateSellerRequest.class))).thenReturn(seller);
        when(sellerUseCase.create(seller)).thenReturn(seller);
        when(sellerEntityMapper.toCreateResponse(seller)).thenReturn(createSellerResponse);

        MockHttpServletRequestBuilder content = post("/api/v1/sellers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"code123\", \"payments\": []}");

        ResultActions resultActions = mockMvc.perform(content)
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("code123"));
    }

    @Test
    @DisplayName("Test GET /sellers/{sellerCode} - Find Seller by ID")
    void testFindSellerById() throws Exception {
        String sellerCode = "code123";
        Seller seller = new Seller(sellerCode, List.of(new Payment("teste1", BigDecimal.valueOf(4.4),
                PaymentStatus.EXCESS)));
        SellerResponse sellerResponse = new SellerResponse(sellerCode, List.of(new Payment("teste1",
                BigDecimal.valueOf(4.4), PaymentStatus.EXCESS)));

        when(sellerUseCase.findById(sellerCode)).thenReturn(seller);
        when(sellerEntityMapper.toResponse(seller)).thenReturn(sellerResponse);

        MockHttpServletRequestBuilder requestBuilder = get("/api/v1/sellers/{sellerCode}", sellerCode);

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(sellerCode));
    }

    @Test
    @DisplayName("Test PUT /sellers/{sellerCode} - Update Seller")
    void testUpdateSeller() throws Exception {
        String sellerCode = "code123";
        Seller seller = new Seller(sellerCode, List.of(new Payment("teste1", BigDecimal.valueOf(4.5),
                PaymentStatus.EXCESS)));
        UpdateSellerResponse updateSellerResponse = new UpdateSellerResponse("code123", List.of());

        when(sellerEntityMapper.toSeller(any(UpdateSellerRequest.class))).thenReturn(seller);
        when(sellerUseCase.update(eq(sellerCode), any(Seller.class))).thenReturn(seller);
        when(sellerEntityMapper.toUpdateResponse(seller)).thenReturn(updateSellerResponse);

        MockHttpServletRequestBuilder content = put("/api/v1/sellers/{sellerCode}", sellerCode)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"code123\", \"payments\": []}");

        ResultActions resultActions = mockMvc.perform(content)
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("code123"));
    }

    @Test
    @DisplayName("Test PATCH /sellers/update-status/{sellerCode} - Update Seller Status")
    void testUpdateSellerStatus() throws Exception {
        String sellerCode = "code123";

        doNothing().when(sellerUseCase).updateStatus(eq(sellerCode), any(Seller.class));

        MockHttpServletRequestBuilder content = patch("/api/v1/sellers/update-status/{sellerCode}", sellerCode)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"status\": \"ACTIVE\"}");

        ResultActions resultActions = mockMvc.perform(content)
                .andDo(print());

        resultActions
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Test DELETE /sellers/{sellerCode} - Delete Seller")
    void testDeleteSeller() throws Exception {
        String sellerCode = "code123";

        doNothing().when(sellerUseCase).delete(sellerCode);

        MockHttpServletRequestBuilder delete = delete("/api/v1/sellers/{sellerCode}", sellerCode);

        ResultActions resultActions = mockMvc.perform(delete)
                .andDo(print());

        resultActions
                .andExpect(status().isNoContent());
    }
}

