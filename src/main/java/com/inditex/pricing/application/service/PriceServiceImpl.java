package com.inditex.pricing.application.service;

import com.inditex.pricing.dto.PriceResponseDTO;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.infrastructure.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Application service for the use case of retrieving applicable price.
 *
 * This class orchestrates the domain logic for price retrieval without applying business rules itself.
 */
@Service
public class PriceServiceImpl implements com.inditex.pricing.application.service.PriceService {

    private final PriceRepository repository;

    public PriceServiceImpl(PriceRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves the highest-priority valid price for a given product, brand, and date.
     *
     * @param date      the application date to check
     * @param productId the product identifier
     * @param brandId   the brand identifier
     * @return the applicable price as a DTO
     * @throws PriceNotFoundException if no valid price is found
     */
    @Override
    public PriceResponseDTO getApplicablePrice(LocalDateTime date, int productId, int brandId) {
        return repository.findValidPrices(date, productId, brandId).stream()
                .findFirst()
                .map(PriceResponseDTO::fromEntity)
                .orElseThrow(() -> new PriceNotFoundException(date, productId, brandId));
    }
}
