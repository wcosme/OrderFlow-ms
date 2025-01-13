package br.com.orderflow.application.port.output;

import br.com.orderflow.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
    void save(Order order);
    Optional<Order> findById(String id);
    List<Order> findAll();
}
