package com.uol.candidate_evaluation_project.infrastructure.seller;

import com.uol.candidate_evaluation_project.domain.seller.Seller;
import com.uol.candidate_evaluation_project.main.error.Errors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SellerRepositoryGatewayTest {

    private SellerRepository sellerRepository;
    private SellerEntityMapper sellerEntityMapper;
    private SellerRepositoryGateway sellerRepositoryGateway;

    @BeforeEach
    void setUp() {
        sellerRepository = mock(SellerRepository.class);
        sellerEntityMapper = mock(SellerEntityMapper.class);
        sellerRepositoryGateway = new SellerRepositoryGateway(sellerRepository, sellerEntityMapper);
    }

    @Test
    void shouldCreateSeller() {
        Seller seller = new Seller("SELLER123", null);
        SellerEntity sellerEntity = new SellerEntity("SELLER123");

        when(sellerEntityMapper.toEntity(seller)).thenReturn(sellerEntity);
        when(sellerRepository.save(sellerEntity)).thenReturn(sellerEntity);
        when(sellerEntityMapper.toDomainObj(sellerEntity)).thenReturn(seller);

        Seller createdSeller = sellerRepositoryGateway.create(seller);

        assertEquals(seller, createdSeller);
        verify(sellerEntityMapper).toEntity(seller);
        verify(sellerRepository).save(sellerEntity);
        verify(sellerEntityMapper).toDomainObj(sellerEntity);
    }

    @Test
    void shouldFindSellerById() {
        String sellerCode = "SELLER123";
        SellerEntity sellerEntity = new SellerEntity(sellerCode);
        Seller seller = new Seller(sellerCode, null);

        when(sellerRepository.findById(sellerCode)).thenReturn(Optional.of(sellerEntity));
        when(sellerEntityMapper.toDomainObj(sellerEntity)).thenReturn(seller);

        Seller foundSeller = sellerRepositoryGateway.findById(sellerCode);

        assertEquals(seller, foundSeller);
        verify(sellerRepository).findById(sellerCode);
        verify(sellerEntityMapper).toDomainObj(sellerEntity);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenSellerNotFound() {
        String sellerCode = "SELLER123";

        when(sellerRepository.findById(sellerCode)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Errors.ResourceNotFoundException.class, () -> {
            sellerRepositoryGateway.findById(sellerCode);
        });

        assertEquals("Seller not found with code: " + sellerCode, exception.getMessage());
    }

    @Test
    void shouldCheckIfSellerExists() {
        String sellerCode = "SELLER123";

        when(sellerRepository.existsById(sellerCode)).thenReturn(true);

        boolean exists = sellerRepositoryGateway.existsById(sellerCode);

        assertTrue(exists);
        verify(sellerRepository).existsById(sellerCode);
    }

    @Test
    void shouldUpdateSeller() {
        Seller seller = new Seller("SELLER123", null);
        SellerEntity sellerEntity = new SellerEntity("SELLER123");

        when(sellerEntityMapper.toEntity(seller)).thenReturn(sellerEntity);
        when(sellerRepository.save(sellerEntity)).thenReturn(sellerEntity);
        when(sellerEntityMapper.toDomainObj(sellerEntity)).thenReturn(seller);

        Seller updatedSeller = sellerRepositoryGateway.update(seller);

        assertEquals(seller, updatedSeller);
        verify(sellerEntityMapper).toEntity(seller);
        verify(sellerRepository).save(sellerEntity);
        verify(sellerEntityMapper).toDomainObj(sellerEntity);
    }

    @Test
    void shouldDeleteSeller() {
        String sellerCode = "SELLER123";
        SellerEntity sellerEntity = new SellerEntity(sellerCode);

        when(sellerRepository.findById(sellerCode)).thenReturn(Optional.of(sellerEntity));

        sellerRepositoryGateway.delete(sellerCode);

        verify(sellerRepository).delete(sellerEntity);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenDeletingNonExistentSeller() {
        String sellerCode = "SELLER123";

        when(sellerRepository.findById(sellerCode)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Errors.ResourceNotFoundException.class, () -> {
            sellerRepositoryGateway.delete(sellerCode);
        });

        assertEquals("Seller not found with code: " + sellerCode, exception.getMessage());
    }
}
