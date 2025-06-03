package com.inditex.pricing.shared.dto;

import com.inditex.pricing.domain.model.Price;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object representing the response for a price query.
 *
 * Encapsulates the pricing information to be returned from the application service
 * to the client layer (e.g., REST API).
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponseDTO {

    private Integer productId;
    private Integer brandId;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;

    /**
     * Method to map a domain Price entity to a DTO.
     *
     * @param price the domain Price entity
     * @return a PriceResponseDTO containing the mapped fields
     */
    public static PriceResponseDTO fromEntity(Price price) {
        return new PriceResponseDTO(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice()
        );
    }
}
