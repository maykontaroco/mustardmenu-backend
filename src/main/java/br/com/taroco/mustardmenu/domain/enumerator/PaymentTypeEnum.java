package br.com.taroco.mustardmenu.domain.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum PaymentTypeEnum {

    MONEY(1L, "Dinheiro"),
    PIX(2L, "PIX"),
    CREDIT_CARD(3L, "Cartão de Crédito"),
    DEBIT_CARD(4L, "Cartão de Débito");

    private Long code;
    private String description;

    PaymentTypeEnum(Long code, String description) {
        this.code = code;
        this.description = description;
    }

    public PaymentTypeEnum findByCode(Long code) {
        return Arrays.stream(PaymentTypeEnum.values()).filter(p -> p.code.equals(code)).findFirst().orElse(null);
    }

    @JsonCreator
    public static PaymentTypeEnum fromString(String value) {
        for (PaymentTypeEnum type : PaymentTypeEnum.values()) {
            if (type.name().equals(value)) {
                return type;
            }
        }
        return null;
    }
    @JsonValue
    public String getDescription() {
        return description;
    }
}
