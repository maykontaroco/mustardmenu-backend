package br.com.taroco.mustardmenu.domain.enumerator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatusEnum {
    OPEN("Aberta"),
    FINISHED("Finalizada"),
    CANCELED("Cancelada");

    private String description;

    OrderStatusEnum(String description) {
        this.description = description;
    }

    @JsonCreator
    public static OrderStatusEnum fromString(String value) {
        for (OrderStatusEnum status : OrderStatusEnum.values()) {
            if (status.name().equals(value)) {
                return status;
            }
        }
        return null;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
