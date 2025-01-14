package br.com.orderflow.domain.entity;

import br.com.orderflow.domain.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    private String id;
    private String customerId;
    private List<Product> products;
    private BigDecimal totalValue;
    private OrderStatus status;

    public Order(List<Product> products) {
        this.id = UUID.randomUUID().toString();
        this.products = products;
        calculateTotalValue();
    }

    public static Order buildOrder(String id, String customerId, List<Product> products, OrderStatus status) {
        Order order = Order.builder()
                .id(id)
                .customerId(customerId)
                .products(products)
                .status(status)
                .build();
        order.calculateTotalValue();
        return order;
    }

    public void calculateTotalValue() {
        this.totalValue = products.stream()
                .map(Product::calculateTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}