package br.com.orderflow.application.service;

import br.com.orderflow.application.port.input.dto.ProductDto;
import br.com.orderflow.application.port.input.dto.request.OrderRequestDto;
import br.com.orderflow.application.port.input.dto.response.OrderResponseDto;
import br.com.orderflow.domain.entity.Order;
import br.com.orderflow.domain.entity.Product;
import br.com.orderflow.domain.enums.OrderStatus;
import br.com.orderflow.infrastructure.persistence.repository.OrderPersistence;
import br.com.orderflow.shared.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateOrderUseCaseImplTest {

    @Mock
    private OrderPersistence orderPersistence;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CreateOrderUseCaseImpl createOrderUseCase;

    private Order testOrder;
    private OrderRequestDto testOrderRequestDto;
    private OrderResponseDto testOrderResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testOrder = new Order();
        testOrder.setId("order-123");
        testOrder.setCustomerId("customer-456");
        testOrder.setProducts(List.of(
                new Product("product-001", "Notebook Dell Inspiron", BigDecimal.valueOf(3500), 1),
                new Product("product-002", "Mouse Gamer Logitech", BigDecimal.valueOf(250), 2)
        ));
        testOrder.setTotalValue(BigDecimal.valueOf(4000));
        testOrder.setStatus(OrderStatus.PENDING);

        testOrderRequestDto = new OrderRequestDto(
                "customer-456",
                List.of(
                        new ProductDto("product-001", "Notebook Dell Inspiron", BigDecimal.valueOf(3500), 1),
                        new ProductDto("product-002", "Mouse Gamer Logitech", BigDecimal.valueOf(250), 2)
                )
        );

        testOrderResponseDto = new OrderResponseDto(
                "order-123",
                "customer-456",
                List.of(
                        new ProductDto("product-001", "Notebook Dell Inspiron", BigDecimal.valueOf(3500), 1),
                        new ProductDto("product-002", "Mouse Gamer Logitech", BigDecimal.valueOf(250), 2)
                ),
                BigDecimal.valueOf(4000),
                "Pedido pendente"
        );
    }

    @Test
    void shouldCreateOrderSuccessfully() {
        // Arrange
        when(modelMapper.map(testOrderRequestDto, Order.class)).thenReturn(testOrder);
        doNothing().when(orderPersistence).save(testOrder);
        when(modelMapper.map(testOrder, OrderResponseDto.class)).thenReturn(testOrderResponseDto);

        // Act
        OrderResponseDto result = createOrderUseCase.createOrder(testOrderRequestDto);

        // Assert
        assertNotNull(result);
        assertEquals("order-123", result.id());
        assertEquals(BigDecimal.valueOf(4000), result.totalValue());
        verify(orderPersistence, times(1)).save(testOrder);
    }

    @Test
    void shouldReturnOrderByIdSuccessfully() {
        // Arrange
        String orderId = "order-123";
        when(orderPersistence.findById(orderId)).thenReturn(Optional.of(testOrder));
        when(modelMapper.map(testOrder, OrderResponseDto.class)).thenReturn(testOrderResponseDto);

        // Act
        OrderResponseDto result = createOrderUseCase.getOrderById(orderId);

        // Assert
        assertNotNull(result);
        assertEquals("order-123", result.id());
        verify(orderPersistence, times(1)).findById(orderId);
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {
        // Arrange
        String orderId = "order-123";
        when(orderPersistence.findById(orderId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BusinessException.class, () -> createOrderUseCase.getOrderById(orderId));
        verify(orderPersistence, times(1)).findById(orderId);
    }

    @Test
    void shouldReturnAllOrdersSuccessfully() {
        // Arrange
        Pageable pageable = Pageable.unpaged();
        Page<Order> ordersPage = new PageImpl<>(List.of(testOrder));
        when(orderPersistence.findAll(pageable)).thenReturn(ordersPage);
        when(modelMapper.map(any(Order.class), eq(OrderResponseDto.class))).thenReturn(testOrderResponseDto);

        // Act
        Page<OrderResponseDto> result = createOrderUseCase.getAllOrders(pageable);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(orderPersistence, times(1)).findAll(pageable);
    }
}