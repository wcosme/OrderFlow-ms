package br.com.orderflow.infrastructure.persistence.repository;

import br.com.orderflow.domain.entity.Order;
import br.com.orderflow.infrastructure.persistence.model.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderPersistence {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderPersistence(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    public List<Order> findByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId)
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, Order.class))
                .toList();
    }

    public void save(Order order) {
        orderRepository.save(modelMapper.map(order, OrderEntity.class));
    }

    public Optional<Order> findById(String id) {
        return orderRepository.findById(id)
                .map(entity -> modelMapper.map(entity, Order.class));
    }

    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(entity -> modelMapper.map(entity, Order.class));
    }
}


