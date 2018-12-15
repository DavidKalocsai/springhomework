package ua.epam.spring.hometask.service.booking.rate;

import ua.epam.spring.hometask.domain.EventRating;

/**
 * Strategy interface of to get price multiplier for high rated events. 
 */
public class HighRatedEventMultiplierStrategy implements EventRateMultiplierStrategy {
	private double multiplier;
	
	public HighRatedEventMultiplierStrategy(final double multiplier) {
		this.multiplier = multiplier;
	}

	@Override
	public double getMultiplier() {
		return this.multiplier;
	}

	@Override
	public EventRating getSupportedEventRate() {
		return EventRating.HIGH;
	}
}
