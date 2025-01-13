package br.com.orderflow.application.port.input.dto.request;

import br.com.orderflow.application.port.input.dto.ProductDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequestDto(
        @NotEmpty(message = "O ID do cliente não pode estar vazio.")
        String customerId,

        @NotNull(message = "A lista de produtos não pode ser nula.")
        List<ProductDto> products
) {
}
