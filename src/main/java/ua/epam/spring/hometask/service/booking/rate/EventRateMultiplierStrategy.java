package ua.epam.spring.hometask.service.booking.rate;

import ua.epam.spring.hometask.domain.EventRating;

/**
 * Strategy interface of to get price multiplier for rate of event. 
 */
public interface EventRateMultiplierStrategy {
	
	/**
	 * Calculate the multiplier of price on given event rate.
	 * @return multiplier
	 */
	double getMultiplier();
	
	/**
     * Get supported event rate.
	 * @return {@link EventRating}
	 */
	EventRating getSupportedEventRate();
}
