package com.inditex.pricing.application.service;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.port.in.PriceUseCase;
import com.inditex.pricing.domain.port.out.PriceRepository;
import com.inditex.pricing.domain.service.PriceDomainService;
import com.inditex.pricing.shared.dto.PriceResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of the {@link PriceUseCase} interface.
 * Provides the logic to find the applicable price for a product and brand at a specific time.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PriceServiceImpl implements PriceUseCase {

    private final PriceRepository priceRepository;
    private final PriceDomainService priceDomainService;

    /**
     * Finds the applicable price for a product and brand at the given date.
     *
     * @param productId the product ID.
     * @param brandId the brand ID.
     * @param applicationDate the date and time for which the price is needed.
     * @return the applicable price as a {@link PriceResponseDTO}.
     * @throws PriceNotFoundException if no applicable price is found.
     */
    @Override
    public PriceResponseDTO findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        log.debug("Searching prices for productId={}, brandId={}, applicationDate={}", productId, brandId, applicationDate);

        List<Price> prices = priceRepository.findByBrandIdAndProductId(brandId, productId);

        Price applicablePrice = priceDomainService.filterPricesByDateAndPriority(prices, applicationDate)
                .orElseThrow(() -> {
                    log.warn("No applicable price found for productId={}, brandId={}, applicationDate={}", productId, brandId, applicationDate);
                    return new PriceNotFoundException("No applicable price found");
                });

        log.info("Applicable price found: {}", applicablePrice);

        return new PriceResponseDTO(
                applicablePrice.getProductId(),
                applicablePrice.getBrandId(),
                applicablePrice.getPriceList(),
                applicablePrice.getStartDate(),
                applicablePrice.getEndDate(),
                applicablePrice.getPrice()
        );
    }
}
