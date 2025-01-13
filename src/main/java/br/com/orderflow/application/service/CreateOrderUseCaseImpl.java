package br.com.orderflow.application.service;

import br.com.orderflow.application.port.input.dto.request.OrderRequestDto;
import br.com.orderflow.application.port.input.dto.response.OrderResponseDto;
import br.com.orderflow.application.port.input.usecase.CreateOrderUseCase;
import br.com.orderflow.application.port.output.OrderRepositoryPort;
import br.com.orderflow.domain.entity.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private OrderRepositoryPort orderRepositoryPort;
    private ModelMapper modelMapper;

    public CreateOrderUseCaseImpl(OrderRepositoryPort orderRepositoryPort, ModelMapper modelMapper) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderResponseDto execute(OrderRequestDto orderRequest) {
        Order order = modelMapper.map(orderRequest, Order.class);

        orderRepositoryPort.save(order);

        return modelMapper.map(order, OrderResponseDto.class);
    }
}
