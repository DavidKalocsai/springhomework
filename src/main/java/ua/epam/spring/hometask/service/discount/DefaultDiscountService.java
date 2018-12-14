package ua.epam.spring.hometask.service.discount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ua.epam.spring.hometask.domain.DiscountProperties;

public class DefaultDiscountService implements DiscountService {
	final Set<DiscountStrategy> discountStrategies = new HashSet<>();

	public DefaultDiscountService(final DiscountStrategy... discountStrategies) {
		for (final DiscountStrategy discountStrategy : discountStrategies) {
			saveStrategy(discountStrategy);
		}
	}
	
	private void saveStrategy(final DiscountStrategy strategy) {
		if (strategy != null) {
			this.discountStrategies.add(strategy);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public byte getDiscount(final DiscountProperties discountProperties) {
		final List<Byte> discounts = new ArrayList<>();
		for (final DiscountStrategy discountStrategy :  discountStrategies) {
			discounts.add(discountStrategy.getDiscount(discountProperties));
		}
		return Collections.max(discounts);
	}
}
