package br.com.orderflow.application.port.input.usecase;

import br.com.orderflow.application.port.input.dto.request.OrderRequestDto;
import br.com.orderflow.application.port.input.dto.response.OrderResponseDto;

public interface CreateOrderUseCase {
    OrderResponseDto execute(OrderRequestDto orderRequest);
}
