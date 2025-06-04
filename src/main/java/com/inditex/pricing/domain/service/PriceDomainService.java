package com.inditex.pricing.domain.service;

import com.inditex.pricing.domain.model.Price;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Domain service responsible for encapsulating the business logic
 * of selecting the applicable price from a list of valid prices.
 *
 * This service ensures that domain rules (such as prioritizing prices)
 * are centralized and independent of application or infrastructure layers.
 *
 * In the current implementation, it simply returns the first price in the list,
 * assuming the list is pre-sorted by priority. If no prices are available, it returns null.
 */
@Component
public class PriceDomainService {

    /**
     * Selects the applicable price from a list of valid prices.
     *
     * The list is expected to be sorted by priority (e.g., descending),
     * so this method will return the first valid price or null if the list is empty.
     *
     * @param prices a list of valid price candidates (pre-filtered and sorted)
     * @return the selected applicable {@link Price}, or null if none are available
     */

    public Price selectHighestPriorityPrice(List<Price> prices) {
        return prices.stream()
                .findFirst()
                .orElse(null);
    }
}
