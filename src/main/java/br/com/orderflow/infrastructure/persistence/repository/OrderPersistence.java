package br.com.orderflow.infrastructure.persistence.repository;

import br.com.orderflow.application.port.output.OrderRepositoryPort;
import br.com.orderflow.domain.entity.Order;
import br.com.orderflow.infrastructure.persistence.model.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderPersistence implements OrderRepositoryPort {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderPersistence(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void save(Order order) {
        orderRepository.save(modelMapper.map(order, OrderEntity.class));
    }

    @Override
    public Optional<Order> findById(String id) {
        return orderRepository.findById(id)
                .map(entity -> modelMapper.map(entity, Order.class));
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Order.class))
                .collect(Collectors.toList());
    }
}
