package ua.epam.spring.hometask.domain;

import java.time.LocalDateTime;

public class DiscountProperties {
	final User user;
	final Event event;
	final LocalDateTime airDateTime;
	final int numberOfTickets;
	
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

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public class DiscountPropertiesBuilder {
		private User user;
		private Event event;
		private LocalDateTime airDateTime;
		private int numberOfTickets;
		
		public void buildWithUser(final User user) {
			this.user = user;
		}
		
		public void buildWithEvent(final Event event) {
			this.event = event;
		}
		
		public void buildWithAirDateTime(final LocalDateTime airDateTime) {
			this.airDateTime = airDateTime;
		}
		
		public void buildWithUser(final int numberOfTickets) {
			this.numberOfTickets = numberOfTickets;
		}
		
		public DiscountProperties build() {
			return new DiscountProperties(this);
		}
	}
}
