package com.inditex.pricing.domain.exception;

import java.time.LocalDateTime;

/**
 * Exception thrown when no valid price is found for the given product, brand, and date.
 *
 * This exception belongs to the domain layer and reflects a business rule violation.
 */
public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(LocalDateTime date, int productId, int brandId) {
        super(String.format("No applicable price found for productId [%d], brandId [%d] on date [%s]",
                productId, brandId, date));
    }
}
