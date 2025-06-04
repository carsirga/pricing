package com.inditex.pricing.domain.port.out;

import com.inditex.pricing.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository for accessing Price entities from the database.
 *
 * Provides a custom query to retrieve valid prices based on brand, product, and a specific date.
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    /**
     * Finds applicable prices for a given product, brand and date.
     * Results are ordered by priority descending to allow picking the highest priority one.
     *
     * @param date the date and time for which the price is being queried
     * @param productId the ID of the product
     * @param brandId the ID of the brand
     * @return a list of valid prices ordered by priority descending
     */
    @Query("""
       SELECT p FROM Price p 
       WHERE p.brandId = :brandId 
         AND p.productId = :productId 
         AND :date BETWEEN p.startDate AND p.endDate 
       ORDER BY p.priority DESC
       """)
    Optional<Price> findTopValidPrice(
            @Param("date") LocalDateTime date,
            @Param("productId") int productId,
            @Param("brandId") int brandId
    );
}
