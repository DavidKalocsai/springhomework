package ua.epam.spring.hometask.service.booking.vip;

import javax.annotation.Nonnull;

/**
 * Service interface to get price multiplier for a VIP tickets.
 */
public interface VipTicketPriceSerivce {

	/**
	 * Service interface to get price multiplier for VIP tickets.
	 * 
	 * @return price multiplier
	 */
	@Nonnull double getMultiplier();
}
