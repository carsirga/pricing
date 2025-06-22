package com.inditex.pricing.domain.exception;

import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * Exception thrown when no applicable price is found for the specified product, brand, and date.
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PriceNotFoundException extends RuntimeException {

    private final LocalDateTime date;
    private final Integer productId;
    private final Integer brandId;

    public PriceNotFoundException(LocalDateTime date, int productId, int brandId) {
        super(String.format("No applicable price found for productId [%d], brandId [%d] on date [%s]",
                productId, brandId, date));
        this.date = date;
        this.productId = productId;
        this.brandId = brandId;
    }

    public PriceNotFoundException(String message) {
        super(message);
        this.date = null;
        this.productId = null;
        this.brandId = null;
    }
}
