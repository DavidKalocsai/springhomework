package ua.epam.spring.hometask.service;

import javax.annotation.Nullable;

import ua.epam.spring.hometask.domain.DiscountProperties;

/**
 * @author Yuriy_Tkach
 */
public interface DiscountService {

	/**
	 * Getting discount based on some rules for user that buys some number of
	 * tickets for the specific date time of the event
	 * 
	 * @param DiscountProperties {@link DiscountProperties}
	 * 
	 * @return discount value from 0 to 100
	 */
	byte getDiscount(@Nullable DiscountProperties discountProperties);
}
