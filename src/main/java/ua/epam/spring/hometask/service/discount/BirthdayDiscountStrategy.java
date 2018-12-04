package ua.epam.spring.hometask.service.discount;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import ua.epam.spring.hometask.domain.DiscountProperties;

public class BirthdayDiscountStrategy implements DiscountStrategy {
	private final byte discount;
	private final int discountActivePeriodInDays;

	BirthdayDiscountStrategy(final byte discount, final int discountActiveDays) {
		this.discount = discount;
		this.discountActivePeriodInDays = discountActiveDays;
	}

	@Override
	public byte getDiscount(DiscountProperties discountProperties) {
		final LocalDate userBirthDay = getBirthDay(discountProperties);
		final LocalDateTime airDate = discountProperties.getAirDateTime();
		return isBirthDayDiscountActive(userBirthDay, airDate) ? discount : 0;
	}
	
	private boolean isBirthDayDiscountActive(final LocalDate birthDay, final LocalDateTime airDate) {
		boolean discountActive = false;
		if (birthDay != null && airDate != null) {
			long days =  Period.between(getUserBirthDayInCurrentYear(birthDay, airDate), airDate.toLocalDate()).getDays();
			discountActive = days <= discountActivePeriodInDays;
		}
		return discountActive;		
	} 
	
	private LocalDate getUserBirthDayInCurrentYear(final LocalDate userBirthDay, final LocalDateTime airDate) {
		return userBirthDay.withYear(airDate.getYear()); // TODO if user does not have birthday in everyday, like 02.29, then this will fail
	}

	private LocalDate getBirthDay(final DiscountProperties discountProperties) {
		LocalDate birthDay = null;
		if (isBirthDayExists(discountProperties)) {
			birthDay = discountProperties.getUser().getBirthDay();
		}
		return birthDay;
	}

	private boolean isBirthDayExists(final DiscountProperties discountProperties) {
		return discountProperties.getUser() != null && discountProperties.getUser().getBirthDay() != null;
	}

}
