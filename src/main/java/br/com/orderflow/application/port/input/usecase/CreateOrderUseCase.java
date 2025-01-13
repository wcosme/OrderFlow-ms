package br.com.orderflow.application.port.input.usecase;

import br.com.orderflow.application.port.input.dto.request.OrderRequestDto;
import br.com.orderflow.application.port.input.dto.response.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CreateOrderUseCase {
    OrderResponseDto createOrder(OrderRequestDto orderRequest);
    Page<OrderResponseDto> getAllOrders(Pageable pageable);

    OrderResponseDto getOrderById(String id);
}
