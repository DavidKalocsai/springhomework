package ua.epam.spring.hometask.service.booking.rate;

import javax.annotation.Nonnull;

import ua.epam.spring.hometask.domain.EventRating;

/**
 * Service interface to get price multiplier for a given event rate.
 */
public interface EventRateMultiplierService {

	/**
	 * Service interface to get price multiplier for a given event rate.
	 * 
	 * @param evetRating {@link EventRating}
	 * 
	 * @return price multiplier
	 */
	double getMultiplier(@Nonnull EventRating evetRating);
}
