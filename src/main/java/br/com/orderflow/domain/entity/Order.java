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
public class Order {

    private String id;
    private String customerId;
    private List<Product> products;
    private BigDecimal totalValue;
    private OrderStatus status;

    // Construtor customizado
    public Order(List<Product> products) {
        this.id = UUID.randomUUID().toString();
        this.products = products;
        calculateTotalValue();
    }

    // Método para calcular o valor total do pedido
    private void calculateTotalValue() {
        this.totalValue = products.stream()
                .map(Product::calculateTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Sobrescrevendo equals() e hashCode() para evitar conflitos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}