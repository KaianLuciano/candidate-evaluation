package com.uol.candidate_evaluation_project.infrastructure.seller;

import com.uol.candidate_evaluation_project.application.seller.SellerUseCase;
import com.uol.candidate_evaluation_project.domain.seller.Seller;
import com.uol.candidate_evaluation_project.infrastructure.seller.payload.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("SellerController")
@RequestMapping("${api.candidate.evaluation.project.base.path}/${api.candidate.evaluation.project.base.version}/sellers")
@Tag(name = "Seller")
public class SellerController {
    private final SellerUseCase sellerUseCase;
    private final SellerDTOMapper sellerEntityMapper;

    public SellerController(SellerUseCase sellerUseCase, SellerDTOMapper sellerEntityMapper) {
        this.sellerUseCase = sellerUseCase;
        this.sellerEntityMapper = sellerEntityMapper;
    }

    @PostMapping
    public ResponseEntity<CreateSellerResponse> create(@RequestBody CreateSellerRequest createSellerRequest) {
        Seller seller = sellerUseCase.create(sellerEntityMapper.toSeller(createSellerRequest),
                createSellerRequest.paymentsCodes());
        return ResponseEntity.ok().body(sellerEntityMapper.toCreateResponse(seller));
    }

    @GetMapping("/{sellerCode}")
    public ResponseEntity<SellerResponse> findById(@PathVariable("sellerCode") String sellerCode) {
        Seller seller = sellerUseCase.findById(sellerCode);
        return ResponseEntity.ok(sellerEntityMapper.toResponse(seller));
    }

    @PutMapping("/{sellerCode}")
    public ResponseEntity<UpdateSellerResponse> update(@PathVariable("sellerCode") String sellerCode,
                                                       @RequestBody UpdateSellerRequest updateSellerRequest) {
        Seller seller = sellerUseCase.update(sellerCode, sellerEntityMapper.toSeller(updateSellerRequest));
        return ResponseEntity.ok(sellerEntityMapper.toUpdateResponse(seller));
    }

    @PatchMapping("/update-status/{sellerCode}")
    public ResponseEntity<Void> updateStatus(@PathVariable("sellerCode") String sellerCode,
                                             @RequestBody UpdateSellerStatusRequest updateSellerRequest) {
        sellerUseCase.updateStatus(sellerCode, sellerEntityMapper.toSeller(updateSellerRequest));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{sellerCode}")
    public ResponseEntity<Void> update(@PathVariable("sellerCode") String sellerCode) {
        sellerUseCase.delete(sellerCode);
        return ResponseEntity.noContent().build();
    }
}
