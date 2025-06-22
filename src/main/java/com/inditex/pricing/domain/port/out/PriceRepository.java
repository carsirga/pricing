package com.inditex.pricing.domain.port.out;


import com.inditex.pricing.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Output port for accessing price data from a data source (e.g., database).
 * Used by application services to retrieve price information.
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    /**
     * Retrieves a list of prices for a specific brand and product.
     *
     * @param brandId   the ID of the brand.
     * @param productId the ID of the product.
     * @return a list of {@link Price} objects that match the criteria.
     */

        List<Price> findByBrandIdAndProductId(Long brandId, Long productId);
    }