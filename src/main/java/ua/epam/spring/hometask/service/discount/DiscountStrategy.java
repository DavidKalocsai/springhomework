package ua.epam.spring.hometask.service.discount;

import ua.epam.spring.hometask.domain.DiscountProperties;

public interface DiscountStrategy {
	/**
	 * Calculate amount of discount based on the discount properties
	 * @param discountProperties {@link DiscountProperties}
	 * @return percentage of discount 0..100
	 */
	byte getDiscount(DiscountProperties discountProperties);
}
