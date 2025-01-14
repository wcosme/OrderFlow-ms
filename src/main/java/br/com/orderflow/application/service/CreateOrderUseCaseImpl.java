package br.com.orderflow.application.service;

import br.com.orderflow.application.port.input.dto.ProductDto;
import br.com.orderflow.application.port.input.dto.request.OrderRequestDto;
import br.com.orderflow.application.port.input.dto.response.OrderResponseDto;
import br.com.orderflow.application.port.input.usecase.CreateOrderUseCase;
import br.com.orderflow.domain.entity.Order;
import br.com.orderflow.domain.entity.Product;
import br.com.orderflow.infrastructure.persistence.repository.OrderPersistence;
import br.com.orderflow.shared.exception.BusinessException;
import br.com.orderflow.shared.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderPersistence orderPersistence;
    private final ModelMapper modelMapper;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequest) {
        if (orderRequest.products() == null || orderRequest.products().isEmpty()) {
            throw new BusinessException("O pedido deve conter pelo menos um produto.");
        }

        List<Order> existingOrders = orderPersistence.findByCustomerId(orderRequest.customerId());


        for (Order existingOrder : existingOrders) {
            if (hasSameProducts(existingOrder.getProducts(), orderRequest.products())) {
                throw new BusinessException("Pedido duplicado: já existe um pedido com o mesmo cliente e produtos.");
            }
        }

        Order order = modelMapper.map(orderRequest, Order.class);
        order.calculateTotalValue();
        orderPersistence.save(order);

        return modelMapper.map(order, OrderResponseDto.class);
    }

    @Override
    public Page<OrderResponseDto> getAllOrders(Pageable pageable) {
        return orderPersistence.findAll(pageable)
                .map(order -> modelMapper.map(order, OrderResponseDto.class));
    }

    @Cacheable(value = "orders", key = "#id")
    @Override
    public OrderResponseDto getOrderById(String id) {
        Order order = orderPersistence.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + id + " não encontrado."));

        return modelMapper.map(order, OrderResponseDto.class);
    }


    private boolean hasSameProducts(List<Product> existingProducts, List<ProductDto> newProducts) {
        return existingProducts.size() == newProducts.size() &&
                existingProducts.stream()
                        .allMatch(product -> newProducts.stream()
                                .anyMatch(dto -> product.getProductId().equals(dto.productId()) &&
                                        product.getPrice().compareTo(dto.price()) == 0 &&
                                        product.getQuantity() == dto.quantity()));
    }

}