package ua.epam.spring.hometask.service.booking.rate;

import ua.epam.spring.hometask.domain.EventRating;

/**
 * Strategy interface of to get price multiplier for low rated events. 
 */
public class LowRatedEventMultiplierStrategy implements EventRateMultiplierStrategy {
	private double multiplier;
	
	public LowRatedEventMultiplierStrategy(final double multiplier) {
		this.multiplier = multiplier;
	}

	@Override
	public double getMultiplier() {
		return this.multiplier;
	}

	@Override
	public EventRating getSupportedEventRate() {
		return EventRating.LOW;
	}
}
