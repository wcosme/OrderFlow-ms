package br.com.orderflow.infrastructure.persistence.repository;

import br.com.orderflow.infrastructure.persistence.model.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    List<OrderEntity> findByCustomerId(String customerId);
}