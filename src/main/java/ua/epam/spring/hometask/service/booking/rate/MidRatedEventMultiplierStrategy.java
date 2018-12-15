package ua.epam.spring.hometask.service.booking.rate;

import ua.epam.spring.hometask.domain.EventRating;

/**
 * Strategy interface of to get price multiplier for medium rated events. 
 */
public class MidRatedEventMultiplierStrategy implements EventRateMultiplierStrategy {
	private double multiplier;
	
	public MidRatedEventMultiplierStrategy(final double multiplier) {
		this.multiplier = multiplier;
	}

	@Override
	public double getMultiplier() {
		return this.multiplier;
	}

	@Override
	public EventRating getSupportedEventRate() {
		return EventRating.MID;
	}
}
