package com.inditex.pricing.domain.port.in;

import com.inditex.pricing.shared.dto.PriceResponseDTO;

import java.time.LocalDateTime;

/**
 * Application service interface for retrieving the applicable price
 * based on the date, product ID, and brand ID.
 * This interface defines the contract for the main use case of price selection.
 */
public interface PriceUseCase {

    /**
     * Returns the applicable price for the given date, product, and brand.
     *
     * @param date      the date and time of the price request
     * @param productId the ID of the product
     * @param brandId   the ID of the brand
     * @return the applicable price as a response DTO
     */
    PriceResponseDTO getApplicablePrice(LocalDateTime date, int productId, int brandId);
}
