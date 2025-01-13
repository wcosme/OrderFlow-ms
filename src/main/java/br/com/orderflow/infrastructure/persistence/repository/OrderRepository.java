package br.com.orderflow.infrastructure.persistence.repository;

import br.com.orderflow.infrastructure.persistence.model.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {
}