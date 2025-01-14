package br.com.orderflow.shared.exception;

public record ErrorResponse(int status, String message, long timestamp) {
}