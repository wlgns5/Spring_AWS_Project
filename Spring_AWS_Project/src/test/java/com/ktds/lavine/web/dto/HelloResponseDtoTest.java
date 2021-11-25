package com.ktds.lavine.web.dto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

public class HelloResponseDtoTest {
	
	@Test
	public void rombok_test() {
		String name = "test";
		int amount = 1000;
		
		HelloResponseDto dto = new HelloResponseDto(name, amount);
		
		assertThat(name, equalTo(dto.getName()));
		assertThat(amount, equalTo(dto.getAmount()));
	}
}
