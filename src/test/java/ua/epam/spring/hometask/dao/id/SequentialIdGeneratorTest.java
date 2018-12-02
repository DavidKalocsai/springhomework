package ua.epam.spring.hometask.dao.id;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class SequentialIdGeneratorTest {
	final static long INITIAL_ID = 1L;
	final static long MAX_ID = 100L;
	
	@Test
	public void shouldReturnInitialIdWhenMapIsEmpty() {
		// GIVEN
		final Set<Long> emptySet = new HashSet<>();
		
		// WHEN
		final Long actualId = new SequentialIdGenerator().generateId(emptySet);
		
		// THEN;
		Assert.assertEquals(INITIAL_ID, actualId.longValue());
	}
	
	@Test
	public void shouldReturnCalculatedIdWhendWhenMapIsFilled() {
		// GIVEN
		
		// WHEN
		final Long actualId = new SequentialIdGenerator().generateId(createKeySet());
		
		// THEN;
		Assert.assertEquals(MAX_ID + 1, actualId.longValue());
	}
	
	
	private Set<Long> createKeySet() {
		final Set<Long> keys = new HashSet<>();
		keys.add(1L);
		keys.add(2L);
		keys.add(MAX_ID);
		return keys;
	}

}
