package br.com.orderflow.application.port.input.dto.response;

import br.com.orderflow.application.port.input.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponseDto(
        String id,
        String customerId,
        List<ProductDto> products,
        BigDecimal totalValue,
        String status
) {
}
