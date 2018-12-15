package ua.epam.spring.hometask.service.booking.rate;

import java.util.HashSet;
import java.util.Set;

import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.service.booking.rate.exception.EventRateMultiplierNotFoundException;

public class DefaultEventRateMultiplierService implements EventRateMultiplierService {
	final Set<EventRateMultiplierStrategy> strategies = new HashSet<>();

	public DefaultEventRateMultiplierService(final EventRateMultiplierStrategy... strategies) {
		for (final EventRateMultiplierStrategy strategy : strategies) {
			saveStrategy(strategy);
		}
	}
	
	private void saveStrategy(final EventRateMultiplierStrategy strategy) {
		if (strategy != null) {
			this.strategies.add(strategy);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getMultiplier(final EventRating eventRating) throws EventRateMultiplierNotFoundException {
		final EventRateMultiplierStrategy searchedStrategy = findStrategy(eventRating);
		throwExceptionIfStrategyNull(searchedStrategy, eventRating);
		return searchedStrategy.getMultiplier();
	}
	
	private EventRateMultiplierStrategy findStrategy(final EventRating eventRating) {
		EventRateMultiplierStrategy searchedStrategy = null;
		for (final EventRateMultiplierStrategy strategy :  strategies) {
			if (strategy.getSupportedEventRate() == eventRating) {
				searchedStrategy = strategy;
			}
		}
		return searchedStrategy;
	}

	private void throwExceptionIfStrategyNull(final EventRateMultiplierStrategy searchedStrategy, final EventRating eventRating) {
		if (searchedStrategy == null) {
			throw new EventRateMultiplierNotFoundException(eventRating);
		}
	}

	
}
