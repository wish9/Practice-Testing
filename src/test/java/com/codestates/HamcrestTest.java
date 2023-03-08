package com.codestates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat; // 자동가져오기 안됨... 왜일까
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class HamcrestTest {
    @DisplayName("Hello Junit Test using hamcrest")
    @Test
    public void assertionTest1() {
        String expected = "Hello, JUnit";
        String actual = "Hello, JUnit";

        assertThat(actual, is(equalTo(expected)));

    }

    @DisplayName("Hello Junit Test using hamcrest")
    @Test
    public void assertionTest() {
        String expected = "Hello, World";
        String actual = "Hello, JUnit";

        assertThat(actual, is(equalTo(expected)));
    }

    @DisplayName("AssertionNull() Test")
    @Test
    public void assertNotNullTest() {
        String currencyName = getCryptoCurrency("ETH");

        assertThat(currencyName, is(notNullValue()));
//        assertThat(currencyName, is(nullValue()));
    }

    @DisplayName("throws NullPointerException when map.get()")
    @Test
    public void assertionThrowExceptionTest() {
        Throwable actualException = assertThrows(NullPointerException.class,
                () -> getCryptoCurrency2("XRP"));

        assertThat(actualException.getClass(), is(NullPointerException.class));
    }

    private String getCryptoCurrency(String unit) {
        return CryptoCurrency.map.get(unit);
    }

    private String getCryptoCurrency2(String unit) {
        return CryptoCurrency.map.get(unit).toUpperCase();
    }
}
