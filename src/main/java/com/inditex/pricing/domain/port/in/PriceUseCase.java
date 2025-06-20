package com.inditex.pricing.domain.port.in;

import com.inditex.pricing.shared.dto.PriceResponseDTO;

import java.time.LocalDateTime;

/**
 * Use case interface for retrieving applicable prices.
 * Acts as the input port in a hexagonal (or clean) architecture.
 */
public interface PriceUseCase {

    /**
     * Finds the applicable price for a given product and brand at a specific date.
     *
     * @param productId       the ID of the product.
     * @param brandId         the ID of the brand.
     * @param applicationDate the date and time when the price should be applied.
     * @return the applicable price in a {@link PriceResponseDTO}.
     */
    PriceResponseDTO findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}