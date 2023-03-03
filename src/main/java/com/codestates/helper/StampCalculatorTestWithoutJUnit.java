package com.codestates.helper;

import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;

import java.util.List;

public class StampCalculatorTestWithoutJUnit { // JUnit을 사용하지 않은 단위 테스트
    public static void main(String[] args) {
        calculateStampCountTest();
        calculateEarnedStampCountTest();
    }

    private static void calculateStampCountTest() {
        int nowCount = 5;
        int earned = 3;


        int actual = StampCalculator.calculateStampCount(nowCount, earned);

        int expected = 7;

        System.out.println(expected == actual);
    }

    private static void calculateEarnedStampCountTest() {
        Order order = new Order();
        OrderCoffee orderCoffee1 = new OrderCoffee();
        orderCoffee1.setQuantity(3);

        OrderCoffee orderCoffee2 = new OrderCoffee();
        orderCoffee2.setQuantity(5);

        order.setOrderCoffees(List.of(orderCoffee1, orderCoffee2));

        int expected = orderCoffee1.getQuantity() + orderCoffee2.getQuantity();

        int actual = StampCalculator.calculateEarnedStampCount(order);

        System.out.println(expected == actual);
    }
}