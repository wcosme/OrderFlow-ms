package br.com.orderflow.application.port.input.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record OrderDto(
        String id,

        @NotNull(message = "A lista de itens não pode ser nula.")
        @Size(min = 1, message = "O pedido deve conter pelo menos um item.")
        List<ProductDto> items,

        @NotNull(message = "O valor total não pode ser nulo.")
        BigDecimal totalValue
) {
}
