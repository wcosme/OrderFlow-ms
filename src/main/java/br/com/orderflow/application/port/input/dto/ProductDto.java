package br.com.orderflow.application.port.input.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductDto(
        @NotBlank(message = "O ID do produto não pode estar vazio.")
        String productId,

        @NotBlank(message = "O nome do produto não pode estar vazio.")
        String productName,

        @NotNull(message = "O preço não pode ser nulo.")
        @Positive(message = "O preço deve ser um valor positivo.")
        BigDecimal price,

        @NotBlank(message = "A quantidade do produto não pode estar vazio.")
        Integer quantity
) {
}
