package br.com.orderflow.shared.config;

import br.com.orderflow.application.port.input.dto.ProductDto;
import br.com.orderflow.application.port.input.dto.request.OrderRequestDto;
import br.com.orderflow.application.port.input.dto.response.OrderResponseDto;
import br.com.orderflow.domain.entity.Order;
import br.com.orderflow.domain.entity.Product;
import br.com.orderflow.domain.enums.OrderStatus;
import br.com.orderflow.infrastructure.persistence.model.OrderEntity;
import br.com.orderflow.infrastructure.persistence.model.ProductEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();

		mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT)
				.setSkipNullEnabled(true);

		// ✅ Converter para OrderRequestDto -> Order
		mapper.addConverter(new Converter<OrderRequestDto, Order>() {
			@Override
			public Order convert(MappingContext<OrderRequestDto, Order> context) {
				OrderRequestDto source = context.getSource();

				// Convertendo a lista de ProductDto para Product
				List<Product> products = source.products().stream()
						.map(productDto -> new Product(
								productDto.productId(),
								productDto.productName(),
								productDto.price(),
								productDto.quantity()))
						.toList();

				// Criando a instância de Order
				return new Order(
						UUID.randomUUID().toString(),
						source.customerId(),
						products,
						BigDecimal.ZERO, // Total será calculado pelo método interno
						OrderStatus.PENDING // Status inicial
				);
			}
		});

		// ✅ Converter para Order -> OrderResponseDto
		mapper.addConverter(new Converter<Order, OrderResponseDto>() {
			@Override
			public OrderResponseDto convert(MappingContext<Order, OrderResponseDto> context) {
				Order source = context.getSource();

				// Convertendo a lista de Product para ProductDto
				List<ProductDto> products = source.getProducts().stream()
						.map(product -> new ProductDto(
								product.getProductId(),
								product.getProductName(),
								product.getPrice(),
								product.getQuantity()))
						.toList();

				// Criando a instância de OrderResponseDto
				return new OrderResponseDto(
						source.getId(),
						source.getCustomerId(),
						products,
						source.getTotalValue(),
						source.getStatus().getDescription()
				);
			}
		});

		// ✅ Converter para Order -> OrderEntity
		mapper.addConverter(new Converter<Order, OrderEntity>() {
			@Override
			public OrderEntity convert(MappingContext<Order, OrderEntity> context) {
				Order source = context.getSource();

				// Convertendo a lista de Product para ProductEntity
				List<ProductEntity> products = source.getProducts().stream()
						.map(product -> new ProductEntity(
								product.getProductId(),
								product.getProductName(),
								product.getPrice(),
								product.getQuantity()))
						.toList();

				// Criando a instância de OrderEntity
				return new OrderEntity(
						source.getId(),
						source.getCustomerId(),
						products,
						source.getTotalValue(),
						source.getStatus().name()
				);
			}
		});

		// ✅ Converter para OrderEntity -> Order
		mapper.addConverter(new Converter<OrderEntity, Order>() {
			@Override
			public Order convert(MappingContext<OrderEntity, Order> context) {
				OrderEntity source = context.getSource();

				// Convertendo a lista de ProductEntity para Product
				List<Product> products = source.getProducts().stream()
						.map(productEntity -> new Product(
								productEntity.getId(),
								productEntity.getName(),
								productEntity.getPrice(),
								productEntity.getQuantity()))
						.toList();

				// Criando a instância de Order
				return new Order(
						source.getId(),
						source.getCustomerId(),
						products,
						source.getTotalValue(),
						OrderStatus.valueOf(source.getStatus())
				);
			}
		});

		return mapper;
	}
}