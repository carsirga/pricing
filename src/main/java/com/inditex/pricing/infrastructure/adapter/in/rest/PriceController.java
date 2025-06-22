package com.inditex.pricing.infrastructure.adapter.in.rest;

import com.inditex.pricing.domain.port.in.PriceUseCase;
import com.inditex.pricing.shared.dto.PriceResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

    /*** REST controller responsible for handling requests related to product pricing.
     * Exposes an endpoint to retrieve the applicable price for a given product,
     * brand, and date.
     */
@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
@Slf4j
public class PriceController {

    private final PriceUseCase priceUseCase;

    /**
     * Retrieves the applicable price for a product and brand at a specific date and time.
     *
     * @param productId the ID of the product.
     * @param brandId   the ID of the brand.
     * @param date      the application date and time.
     * @return a {@link PriceResponseDTO} with the applicable pricing information.
     */
    @Operation(
            summary = "Get applicable price for a product and brand at a specific date",
            description = "Returns the applicable price based on product ID, brand ID, and application date.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Price found"),
                    @ApiResponse(responseCode = "404", description = "No applicable price found"),
                    @ApiResponse(responseCode = "400", description = "Invalid request parameters")
            }
    )
    @GetMapping
    public ResponseEntity<PriceResponseDTO> getApplicablePrice(
            @RequestParam(name = "productId") Long productId,
            @RequestParam(name = "brandId") Long brandId,
            @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            LocalDateTime date) {

        log.info("Request received to find applicable price. ProductId: {}, BrandId: {}, Date: {}", productId, brandId, date);

        PriceResponseDTO response = priceUseCase.findApplicablePrice(productId, brandId, date);

        return ResponseEntity.ok(response);
    }
}
