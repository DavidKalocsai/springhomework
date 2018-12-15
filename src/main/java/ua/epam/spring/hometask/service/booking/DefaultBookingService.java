package ua.epam.spring.hometask.service.booking;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;

import ua.epam.spring.hometask.domain.DiscountProperties;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.booking.rate.EventRateMultiplierService;
import ua.epam.spring.hometask.service.booking.vip.VipTicketPriceSerivce;
import ua.epam.spring.hometask.service.discount.DiscountService;

/**
 * @author Yuriy_Tkach
 */
public class DefaultBookingService implements BookingService {
	private final Set<Ticket> purchasedTickets = new HashSet<>();
	
	private final EventRateMultiplierService eventRateMultiplierService;
	private final VipTicketPriceSerivce vipTicketPriceSerivce;
	private final DiscountService discountService;
	

	public DefaultBookingService(final DiscountService discountService,
			final EventRateMultiplierService eventRateMultiplierService, VipTicketPriceSerivce vipTicketPriceSerivce) {
		this.discountService = discountService;
		this.eventRateMultiplierService = eventRateMultiplierService;
		this.vipTicketPriceSerivce = vipTicketPriceSerivce;
	}

	/**
	 * {@inheritDoc}
	 */
	public double getTicketsPrice(final Event event, final LocalDateTime dateTime, final User user,
			final Set<Long> seats) {
		final double basePrice = event.getBasePrice();
		final double rateBasedPrice = basePrice * eventRateMultiplierService.getMultiplier(event.getRating());
		final long numberOfTickets = seats.size();
		final long numberOfVipTickets = event.getEventAuditorium(dateTime).countVipSeats(seats);
		final long numberOfNormalTickets = numberOfTickets - numberOfVipTickets;
		final double vipTicketsSum = rateBasedPrice * numberOfVipTickets * vipTicketPriceSerivce.getMultiplier();
		final double normalTicketsSum = rateBasedPrice * numberOfNormalTickets;
		final double sum = vipTicketsSum + normalTicketsSum;
		DiscountProperties discountProperties = new DiscountProperties.DiscountPropertiesBuilder().buildWithEvent(event)
				.buildWithUser(user).buildWithTickets(numberOfTickets).buildWithAirDateTime(dateTime).build();
		final double discountRate = discountService.getDiscount(discountProperties);
		final double discountSum = discountRate/100.0 * sum;
		return sum - discountSum;
	}

	/**
	 * Books tickets in internal system. If user is not <code>null</code> in a
	 * ticket then booked tickets are saved with it
	 * 
	 * @param tickets Set of tickets
	 */
	public void bookTickets(@Nonnull Set<Ticket> tickets) {
		for (final Ticket ticket : tickets) {
			final User user = ticket.getUser();
			if (user != null) {
				user.getTickets().add(ticket);
			}
		}
		purchasedTickets.addAll(tickets);
	}

	/**
	 * Getting all purchased tickets for event on specific air date and time
	 * 
	 * @param event    Event to get tickets for
	 * @param dateTime Date and time of airing of event
	 * @return set of all purchased tickets
	 */
	public @Nonnull Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
		final Set<Ticket> purchasedTicketsForEvent = new HashSet<>();
		for (final Ticket ticket : purchasedTickets) {
			if (ticket.getEvent() == event && ticket.getDateTime() == dateTime) {
				purchasedTicketsForEvent.add(ticket);
			}
		}
		return purchasedTicketsForEvent;
	}
}
