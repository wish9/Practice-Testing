package com.codestates;

import com.codestates.exception.BusinessLogicException;
import com.codestates.order.entity.Order;
import com.codestates.order.repository.OrderRepository;
import com.codestates.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class OrderServiceHomeworkTest {

    @Mock
    private OrderRepository orderRepository; // 테스트용 OrderRepository객체 생성
    @InjectMocks // 위에서 만든 객체 주입
    private OrderService orderService;

    @Test
    public void cancelOrderTest() {
        Order order = new Order();
        order.setOrderStatus(Order.OrderStatus.ORDER_COMPLETE);
        order.setOrderId(1L);

        given(orderRepository.findById(Mockito.anyLong()))
                .willReturn(Optional.of(order));

        assertThrows(BusinessLogicException.class, ()->orderService.cancelOrder(order.getOrderId()));
    }
}
