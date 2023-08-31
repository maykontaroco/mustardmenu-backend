package br.com.taroco.mustardmenu.domain.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatusEnum {
    OPEN("Aberta"),
    FINISHED("Finalizada"),
    CANCELED("Cancelada");

    private String description;

    OrderStatusEnum(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
