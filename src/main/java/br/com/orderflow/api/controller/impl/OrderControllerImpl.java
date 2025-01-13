package br.com.orderflow.api.controller.impl;

import br.com.orderflow.api.controller.OrderController;
import br.com.orderflow.application.port.input.dto.request.OrderRequestDto;
import br.com.orderflow.application.port.input.dto.response.OrderResponseDto;
import br.com.orderflow.application.service.CreateOrderUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderControllerImpl implements OrderController {

    private CreateOrderUseCaseImpl createOrderUseCase;

    public OrderControllerImpl(CreateOrderUseCaseImpl createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @Override
    public ResponseEntity<List<OrderResponseDto>> getOrders() {
        return null;
    }

    @Override
    public ResponseEntity<OrderResponseDto> createOrder(OrderRequestDto orderRequest) {
        OrderResponseDto response = createOrderUseCase.execute(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<OrderResponseDto> getOrderById(String id) {
        return null;
    }
}
