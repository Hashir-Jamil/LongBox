package org.longbox.unit.domainobjects.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.StarRatingDto;

class StarRatingTest {

	    @Test
	    public void testGettersAndSetters() {
	        // Create a StarRating object
	    	StarRatingDto starRatingDto = new StarRatingDto();

	        // Set value using setters
	    	starRatingDto.setRating(4);

	        // Verify that getters return the correct values
	        assertEquals(4, starRatingDto.getRating());
	    }
}
