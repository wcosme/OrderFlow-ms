package br.com.orderflow.application.service;

import br.com.orderflow.application.port.input.dto.request.OrderRequestDto;
import br.com.orderflow.application.port.input.dto.response.OrderResponseDto;
import br.com.orderflow.application.port.input.usecase.CreateOrderUseCase;
import br.com.orderflow.application.port.output.OrderRepositoryPort;
import br.com.orderflow.domain.entity.Order;
import br.com.orderflow.infrastructure.persistence.repository.OrderPersistence;
import br.com.orderflow.shared.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderPersistence orderPersistence;
    private final ModelMapper modelMapper;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequest) {
        Order order = modelMapper.map(orderRequest, Order.class);
        orderPersistence.save(order);
        return modelMapper.map(order, OrderResponseDto.class);
    }

    @Override
    public Page<OrderResponseDto> getAllOrders(Pageable pageable) {
        return orderPersistence.findAll(pageable)
                .map(order -> modelMapper.map(order, OrderResponseDto.class));
    }

    @Override
    public OrderResponseDto getOrderById(String id) {
        Order order = orderPersistence.findById(id)
                .orElseThrow(() -> new BusinessException("Pedido não encontrado"));
        return modelMapper.map(order, OrderResponseDto.class);
    }
}


