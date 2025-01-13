package br.com.orderflow.domain.enums;

public enum OrderStatus {
    PENDING("Pedido pendente"),
    COMPLETED("Pedido concluído"),
    CANCELED("Pedido cancelado");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
