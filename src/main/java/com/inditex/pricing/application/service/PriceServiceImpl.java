package com.inditex.pricing.application.service;

import com.inditex.pricing.domain.port.in.PriceUseCase;
import com.inditex.pricing.domain.service.PriceDomainService;
import com.inditex.pricing.shared.dto.PriceResponseDTO;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.domain.port.out.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Application service for the use case of retrieving applicable price.
 *
 * This class orchestrates the domain logic for price retrieval without applying business rules itself.
 */
@Service
public class PriceServiceImpl implements PriceUseCase {

    private final PriceRepository repository;

    private final PriceDomainService domainService;

    public PriceServiceImpl(PriceRepository repository, PriceDomainService domainService) {
        this.repository = repository;
        this.domainService = domainService;
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
        var prices = repository.findValidPrices(date, productId, brandId);
        var selected = domainService.selectHighestPriorityPrice(prices);
        if (selected == null) {
            throw new PriceNotFoundException(date, productId, brandId);
        }
        return PriceResponseDTO.fromEntity(selected);
    }
}
