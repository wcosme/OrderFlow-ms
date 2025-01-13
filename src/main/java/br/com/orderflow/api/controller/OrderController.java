package br.com.orderflow.api.controller;

import br.com.orderflow.application.port.input.dto.request.OrderRequestDto;
import br.com.orderflow.application.port.input.dto.response.OrderResponseDto;
import br.com.orderflow.shared.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "order", description = "Gestão de pedidos")
public interface OrderController {

    @Operation(summary = "Consulta todos os pedidos", tags = "order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.class)))
    })
    @GetMapping(value = "/orders")
    ResponseEntity<List<OrderResponseDto>> getOrders();

    @Operation(summary = "Cria um novo pedido", tags = "order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.class)))
    })
    @PostMapping(value = "/orders")
    ResponseEntity<OrderResponseDto> createOrder(@RequestBody @Valid OrderRequestDto orderRequest);

    @Operation(summary = "Consulta um pedido por ID", tags = "order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.class)))
    })
    @GetMapping(value = "/orders/{id}")
    ResponseEntity<OrderResponseDto> getOrderById(@PathVariable("id") String id);
}
