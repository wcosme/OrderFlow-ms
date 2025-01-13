package br.com.orderflow.api.controller.impl;

import br.com.orderflow.api.controller.OrderController;
import br.com.orderflow.application.port.input.dto.request.OrderRequestDto;
import br.com.orderflow.application.port.input.dto.response.OrderResponseDto;
import br.com.orderflow.application.port.input.usecase.CreateOrderUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class OrderControllerImpl implements OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    @Override
    public ResponseEntity<Page<OrderResponseDto>> getOrders(Pageable pageable) {
        Page<OrderResponseDto> response = createOrderUseCase.getAllOrders(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<OrderResponseDto> createOrder(OrderRequestDto orderRequest) {
        OrderResponseDto response = createOrderUseCase.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<OrderResponseDto> getOrderById(String id) {
        OrderResponseDto response = createOrderUseCase.getOrderById(id);
        return ResponseEntity.ok(response);
    }
}

