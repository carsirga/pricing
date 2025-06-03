package com.inditex.pricing.infrastructure.adapter.in.rest;

import com.inditex.pricing.domain.port.in.PriceUseCase;
import com.inditex.pricing.shared.dto.PriceResponseDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * REST controller for handling price queries.
 *
 * Exposes an endpoint to retrieve the applicable price for a product
 * based on a specific date, product ID, and brand ID.
 */
@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceUseCase priceService;

    public PriceController(PriceUseCase priceService) {
        this.priceService = priceService;
    }

    /**
     * Returns the applicable price based on date, productId and brandId.
     *
     * Example request: GET /api/prices?date=2020-06-14T16:00:00&productId=35455&brandId=1
     *
     * @param date the date and time of the request in ISO format
     * @param productId the ID of the product
     * @param brandId the ID of the brand
     * @return a ResponseEntity with the applicable price information
     */
    @GetMapping
    public ResponseEntity<PriceResponseDTO> getPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("productId") int productId,
            @RequestParam("brandId") int brandId) {

        PriceResponseDTO price = priceService.getApplicablePrice(date, productId, brandId);
        return ResponseEntity.ok(price);
    }
}
