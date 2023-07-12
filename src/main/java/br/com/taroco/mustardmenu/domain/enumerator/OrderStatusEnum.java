package br.com.taroco.mustardmenu.domain.enumerator;

public enum OrderStatusEnum {
    OPEN("Aberto"),
    FINISHED("Finalizado"),
    CANCELED("Cancelado");

    private String description;

    OrderStatusEnum(String description) {
        this.description = description;
    }
}
