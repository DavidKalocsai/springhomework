package ua.epam.spring.hometask.service.discount;

import ua.epam.spring.hometask.domain.DiscountProperties;

public class TicketCountDiscountStrategy implements DiscountStrategy {
	private final byte discount;
	private final int numberOfTicketsToActivateDiscount;

	TicketCountDiscountStrategy(final byte discount, final int numberOfTicketsToActivateDiscount) {
		this.discount = discount;
		this.numberOfTicketsToActivateDiscount = numberOfTicketsToActivateDiscount;
	}

	@Override
	public byte getDiscount(DiscountProperties discountProperties) {
		return isRegisteredUser(discountProperties) ? calculateDiscountForRegisteredUser(discountProperties)
				: calculateDiscountForNotRegisteredUser(discountProperties);
	}

	private boolean isRegisteredUser(final DiscountProperties discountProperties) {
		return discountProperties.getUser() != null;
	}

	private byte calculateDiscountForRegisteredUser(final DiscountProperties discountProperties) {
		final int numberOfPreviouslyPurchasedTickets = getNumberOfPreviouslyPurchasedTickets(discountProperties);
		final int numberOfPreviouslyPurchasedTicketsNotUsedForDiscount = numberOfPreviouslyPurchasedTickets
				% numberOfTicketsToActivateDiscount; // TODO dividing by null

		final int numberOfPurchasedTickets = discountProperties.getNumberOfTickets();
		final int numberOfDiscountedTickets = (numberOfPurchasedTickets
				+ numberOfPreviouslyPurchasedTicketsNotUsedForDiscount) / numberOfTicketsToActivateDiscount;
		final int numberOfFullPrizedTickets = numberOfPurchasedTickets - numberOfDiscountedTickets;
		return calculateDiscount(numberOfDiscountedTickets, numberOfFullPrizedTickets);
	}

	private byte calculateDiscountForNotRegisteredUser(final DiscountProperties discountProperties) {
		final int numberOfPurchasedTickets = discountProperties.getNumberOfTickets();
		final int numberOfDiscountedTickets = numberOfPurchasedTickets / numberOfTicketsToActivateDiscount;
		final int numberOfFullPrizedTickets = numberOfPurchasedTickets - numberOfDiscountedTickets;
		return calculateDiscount(numberOfDiscountedTickets, numberOfFullPrizedTickets);
	}

	private byte calculateDiscount(final int numberOfDiscountedTickets, final int numberOfFullPrizedTickets) {
		final int numberOfTickets = numberOfDiscountedTickets + numberOfFullPrizedTickets;
		final int currentDiscount = 100
				- (numberOfDiscountedTickets * discount + numberOfDiscountedTickets * 100) / numberOfTickets;
		return (byte) currentDiscount;
	}

	private int getNumberOfPreviouslyPurchasedTickets(final DiscountProperties discountProperties) {
		int numberOfPreviouslyPurchasedTickets = 0;
		if (isPreviousPurchasedTicketsExists(discountProperties)) {
			numberOfPreviouslyPurchasedTickets = discountProperties.getUser().getTickets().size();
		}
		return numberOfPreviouslyPurchasedTickets;
	}

	private boolean isPreviousPurchasedTicketsExists(final DiscountProperties discountProperties) {
		return discountProperties.getUser() != null && discountProperties.getUser().getTickets() != null;
	}

}
