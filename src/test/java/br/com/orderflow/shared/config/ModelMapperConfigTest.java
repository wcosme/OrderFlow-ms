package br.com.orderflow.shared.config;

import br.com.orderflow.application.port.input.dto.ProductDto;
import br.com.orderflow.application.port.input.dto.request.OrderRequestDto;
import br.com.orderflow.application.port.input.dto.response.OrderResponseDto;
import br.com.orderflow.domain.entity.Order;
import br.com.orderflow.domain.entity.Product;
import br.com.orderflow.domain.enums.OrderStatus;
import br.com.orderflow.infrastructure.persistence.model.OrderEntity;
import br.com.orderflow.infrastructure.persistence.model.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ModelMapperConfigTest {

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        ModelMapperConfig config = new ModelMapperConfig();
        this.modelMapper = config.modelMapper();
    }

    @Test
    void shouldMapOrderRequestDtoToOrder() {
        // Arrange
        OrderRequestDto requestDto = new OrderRequestDto(
                "customer-456",
                List.of(
                        new ProductDto("product-001", "Notebook Dell Inspiron", BigDecimal.valueOf(3500), 1),
                        new ProductDto("product-002", "Mouse Gamer Logitech", BigDecimal.valueOf(250), 2)
                )
        );

        // Act
        Order order = modelMapper.map(requestDto, Order.class);

        // Assert
        assertNotNull(order);
        assertEquals("customer-456", order.getCustomerId());
        assertEquals(2, order.getProducts().size());
        assertEquals(OrderStatus.PENDING, order.getStatus());
    }

    @Test
    void shouldMapOrderToOrderResponseDto() {
        // Arrange
        Order order = new Order(
                UUID.randomUUID().toString(),
                "customer-456",
                List.of(
                        new Product("product-001", "Notebook Dell Inspiron", BigDecimal.valueOf(3500), 1),
                        new Product("product-002", "Mouse Gamer Logitech", BigDecimal.valueOf(250), 2)
                ),
                BigDecimal.valueOf(4000),
                OrderStatus.PENDING
        );

        // Act
        OrderResponseDto responseDto = modelMapper.map(order, OrderResponseDto.class);

        // Assert
        assertNotNull(responseDto);
        assertEquals(order.getId(), responseDto.id());
        assertEquals(order.getCustomerId(), responseDto.customerId());
        assertEquals(2, responseDto.products().size());
        assertEquals(order.getTotalValue(), responseDto.totalValue());
        assertEquals(order.getStatus().getDescription(), responseDto.status());
    }

    @Test
    void shouldMapOrderToOrderEntity() {
        // Arrange
        Order order = new Order(
                UUID.randomUUID().toString(),
                "customer-456",
                List.of(
                        new Product("product-001", "Notebook Dell Inspiron", BigDecimal.valueOf(3500), 1),
                        new Product("product-002", "Mouse Gamer Logitech", BigDecimal.valueOf(250), 2)
                ),
                BigDecimal.valueOf(4000),
                OrderStatus.PENDING
        );

        // Act
        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);

        // Assert
        assertNotNull(orderEntity);
        assertEquals(order.getId(), orderEntity.getId());
        assertEquals(order.getCustomerId(), orderEntity.getCustomerId());
        assertEquals(2, orderEntity.getProducts().size());
        assertEquals(order.getTotalValue(), orderEntity.getTotalValue());
        assertEquals(order.getStatus().name(), orderEntity.getStatus());
    }

    @Test
    void shouldMapOrderEntityToOrder() {
        // Arrange
        OrderEntity orderEntity = new OrderEntity(
                UUID.randomUUID().toString(),
                "customer-456",
                List.of(
                        new ProductEntity("product-001", "Notebook Dell Inspiron", BigDecimal.valueOf(3500), 1),
                        new ProductEntity("product-002", "Mouse Gamer Logitech", BigDecimal.valueOf(250), 2)
                ),
                BigDecimal.valueOf(4000),
                "PENDING"
        );

        // Act
        Order order = modelMapper.map(orderEntity, Order.class);

        // Assert
        assertNotNull(order);
        assertEquals(orderEntity.getId(), order.getId());
        assertEquals(orderEntity.getCustomerId(), order.getCustomerId());
        assertEquals(2, order.getProducts().size());
        assertEquals(orderEntity.getTotalValue(), order.getTotalValue());
        assertEquals(OrderStatus.PENDING, order.getStatus());
    }
}