package ua.epam.spring.hometask.service.booking.rate.exception;

import ua.epam.spring.hometask.domain.EventRating;

public class EventRateMultiplierNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4142336798180861201L;
	
	public EventRateMultiplierNotFoundException(final EventRating eventRating) {
		super(String.format("EventRateMultiplierStrategy is not implemented for {}", eventRating));
	}
}
