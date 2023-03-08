package com.codestates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Section3Week3TemplateTestingApplicationTests {

	@DisplayName("Hello JUnit Test")
	@Test
	public void assertionTest() {
		String expected = "Hello, JUnit";
		String actual = "Hello, JUnit";

		assertEquals(expected, actual); // JUnit에서 사용하는 두 값이 같은지 비교하는 메서드
	}

	@DisplayName("Hello JUnit Test2")
	@Test
	public void assertionTest2() {
		String expected = "Hello, JUnit";
		String actual = "Hello, World";

		assertEquals(expected, actual); // 두 값이 같은지 테스트
	}

	@DisplayName("assertEqionNull() Test")
	@Test
	public void assertNotNullTest() {
		String currencyName = getCryptoCurrency("ETH"); // Null이 아닌지 테스트

		assertNotNull(currencyName, "should be not null");
	}

	private String getCryptoCurrency(String unit) {
		return CryptoCurrency.map.get(unit);
	}

	@DisplayName("throws NullPointerException when map.get()")
	@Test
	public void assertionThrowExceptionTest() {
		assertThrows(NullPointerException.class, () -> getCryptoCurrency2("XRP")); // 예외가 발생하는지 테스트
	}

	private String getCryptoCurrency2(String unit) {
		return CryptoCurrency.map.get(unit).toUpperCase();
	}
}
