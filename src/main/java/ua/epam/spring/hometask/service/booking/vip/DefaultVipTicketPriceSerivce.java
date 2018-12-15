package ua.epam.spring.hometask.service.booking.vip;

/**
 * Service interface to get price multiplier for a VIP tickets.
 */
public class DefaultVipTicketPriceSerivce implements VipTicketPriceSerivce{
	final private double priceMultiplier;
	
	public DefaultVipTicketPriceSerivce(final double priceMultiplier) {
		this.priceMultiplier = priceMultiplier;
	}	

	@Override
	public double getMultiplier() {
		return priceMultiplier;
	}
}
