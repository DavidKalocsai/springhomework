package ua.epam.spring.hometask.domain;

import java.time.LocalDateTime;

public class DiscountProperties {
	final User user;
	final Event event;
	final LocalDateTime airDateTime;
	final long numberOfTickets;
	
	private DiscountProperties(final DiscountPropertiesBuilder builder) {
		user = builder.user;
		event = builder.event;
		airDateTime = builder.airDateTime;
		numberOfTickets = builder.numberOfTickets;
	}
	
	public User getUser() {
		return user;
	}

	public Event getEvent() {
		return event;
	}

	public LocalDateTime getAirDateTime() {
		return airDateTime;
	}

	public long getNumberOfTickets() {
		return numberOfTickets;
	}

	public static class DiscountPropertiesBuilder {
		private User user;
		private Event event;
		private LocalDateTime airDateTime;
		private long numberOfTickets;
		
		public DiscountPropertiesBuilder buildWithUser(final User user) {
			this.user = user;
			return this;
		}
		
		public DiscountPropertiesBuilder buildWithEvent(final Event event) {
			this.event = event;
			return this;
		}
		
		public DiscountPropertiesBuilder buildWithAirDateTime(final LocalDateTime airDateTime) {
			this.airDateTime = airDateTime;
			return this;
		}
		
		public DiscountPropertiesBuilder buildWithTickets(final long numberOfTickets) {
			this.numberOfTickets = numberOfTickets;
			return this;
		}
		
		public DiscountProperties build() {
			return new DiscountProperties(this);
		}
	}
}
