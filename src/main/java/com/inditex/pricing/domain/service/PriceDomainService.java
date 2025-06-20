package com.inditex.pricing.domain.service;


import com.inditex.pricing.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Domain service responsible for applying business rules related to prices.
 */
public class PriceDomainService {

    /**
     * Filters the list of prices to find the applicable one for a given date and time.
     * The applicable price is the one whose start and end dates cover the requested date,
     * and among them, the one with the highest priority.
     *
     * @param prices           the list of candidate prices.
     * @param applicationDate  the date to filter prices by.
     * @return an {@link Optional} containing the applicable {@link Price}, if found.
     */
    public Optional<Price> filterPricesByDateAndPriority(List<Price> prices, LocalDateTime applicationDate) {
        return prices.stream()
                .filter(price -> isDateWithinRange(applicationDate, price))
                .max(Comparator.comparingInt(Price::getPriority));
    }

    /**
     * Checks if the given date is within the validity period of the price.
     *
     * @param date  the date to check.
     * @param price the price to check against.
     * @return true if the date is within startDate and endDate (inclusive), false otherwise.
     */
    private boolean isDateWithinRange(LocalDateTime date, Price price) {
        return (date.isEqual(price.getStartDate()) || date.isAfter(price.getStartDate()))
                && (date.isBefore(price.getEndDate()) || date.isEqual(price.getEndDate()));
    }
}